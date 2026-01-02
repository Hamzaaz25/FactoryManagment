package Model;

import Enums.ItemStatus;
import Enums.Status;
import Enums.TaskStatus;
import Enums.TaskValidation;
import IO.DataWriter;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskService {
   private final ItemRepository itemRepository ;
   private final ProductRepository productRepository;
   private final TaskRepository taskRepository;
   private final ProductLineRepository productLineRepository;

    public TaskService(ItemRepository itemRepository, ProductRepository productRepository, TaskRepository taskRepository , ProductLineRepository productLineRepository) {
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
        this.taskRepository = taskRepository;
        this.productLineRepository = productLineRepository;

    }

    public synchronized void saveAll(){
        this.itemRepository.save();
        this.taskRepository.save();
        this.productLineRepository.save();
        this.productRepository.save();
    }

    public void logTaskErrors(String message){
        DataWriter.writeErrors(message);
    }

    public TaskValidation isTaskValid(Task t) {

        // Get the requested product and its required quantity
        Product p = this.productRepository.getByName(t.getRequestedProduct());
        ProductLine pl = this.productLineRepository.getProductLineByNumber(t.getProductLine());
        int req = t.getRequestedQuantity();
        if(pl.getStatus() == Status.Maintenance){
            t.setValid(false);
            return TaskValidation.ProductLineMaintenance;

        }
        // Check each ingredient in the product's recipe
        for (String name : p.getRecipe().keySet()) {
            Item item = this.itemRepository.getByName(name);
            int usage = p.getRecipe().get(name) * req;
            if (item == null || !item.isStockAvailable() || item.getAvailableQuantity() <= usage) {
                t.setValid(false);
                return TaskValidation.InsufficientStock; // immediately return if any item fails
            }
        }
        t.setValid(true);
        return TaskValidation.Valid;
    }

    public TaskValidation registerTask(Task t){
        ProductLine pl = this.productLineRepository.getProductLineByNumber(t.getProductLine());
        TaskValidation validation = isTaskValid(t);
        System.out.println(validation);
        if(validation == TaskValidation.Valid){
            this.taskRepository.insert(t);

        }
        else if(validation == TaskValidation.InsufficientStock){
            logTaskErrors("Sorry the task numbered "+t.getTaskNumber()+" cannot be executed because the items stock is insufficient " + LocalDate.now());
        }
        else if(validation == TaskValidation.ProductLineMaintenance){
            logTaskErrors("Sorry the task numbered "+ t.getTaskNumber() + " cannot be executed because the product line "+pl.getId()+" is currently on maintenance" + LocalDate.now());
        }
        this.taskRepository.save();

        return validation;
    }

    public void runTask(Task t){
        System.out.println(this.isTaskValid(t));
        Product product = this.productRepository.getByName(t.getRequestedProduct().trim());
        int req = t.getRequestedQuantity();

        ProductLine productLine = this.productLineRepository.getProductLineByNumber(t.getTaskNumber()) ;
        ArrayList<Item> temp = new ArrayList<>();
        if(isTaskValid(t) ==TaskValidation.Valid ) {
            outer:
            for (String name : product.getRecipe().keySet()) {
                Item item = this.itemRepository.getByName(name);
                temp.add(item);
                synchronized(item){
                    int usage = product.getRecipe().get(name) * req;
                    item.setAvailableQuantity( item.getAvailableQuantity() - usage);
                }
            }
            if (t.getStatus() != TaskStatus.Cancelled) {
                t.setWorking(true);
                for (int i = 1; i <= t.getRequestedQuantity(); i++) {
                    if (t.getStatus() == TaskStatus.Cancelled) {
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    t.setEndDate(LocalDate.now());
                    t.setEndDate(t.getStartDate().plusDays(i));
                    t.setProgressPercentage(i * 100 / t.getRequestedQuantity());

                }

            }


            float percent = (float)t.getProgressPercentage()/100;
            synchronized (product) {
                product.setAmount((int) (product.getAmount() + (req * percent)));
            }
            if(t.getProgressPercentage() == 100 ){
                t.setStatus(TaskStatus.Completed);
            }else t.setStatus(TaskStatus.Cancelled);
            for(Item item : temp){
                if(!item.isStockSufficient()){
                    notifyIfBelowMinimum(item.getName());
                    item.setStatus(ItemStatus.BelowMinimum);
                }
                int produced = (int) (percent * req);
                int notpProduced = req -produced;
                int usagePerProduct = product.getRecipe().get(item.getName().trim());
                item.setAvailableQuantity(item.getAvailableQuantity() + (usagePerProduct * notpProduced));

            }

        }
        else{
            t.setStatus(TaskStatus.Cancelled);
            t.setEndDate(LocalDate.now());
            System.out.println("Task in not valid");
        }
        this.saveAll();
    }

    public void notifyIfBelowMinimum(String message){
        System.out.println( message + " is below minimum ");
    }

    public void cancelTask(Task t){
        t.setStatus(TaskStatus.Cancelled);
        t.setWorking(false);
    }

}
