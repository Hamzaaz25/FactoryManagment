package Model;

import Enums.ItemStatus;
import Enums.Status;
import Enums.TaskStatus;
import Enums.TaskValidation;
import IO.DataWriter;
import Repository.ItemRepository;
import Repository.ProductLineRepository;
import Repository.ProductRepository;
import Repository.TaskRepository;

import java.time.LocalDate;

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


    public TaskValidation validateAndReserve(Task t) {
        Product p = productRepository.getByName(t.getRequestedProduct());
        int req = t.getRequestedQuantity();

        // 1. Validate
        for (String name : p.getRecipe().keySet()) {

            Item item = itemRepository.getByName(name);
            int needed = p.getRecipe().get(name) * req;

            if (item.getAvailableQuantity() < needed) {
                logTaskErrors("Sorry the task numbered "+t.getTaskNumber()+" cannot be executed because the items stock is insufficient " + LocalDate.now());
                return TaskValidation.InsufficientStock;
            }
        }
        ProductLine pl = this.productLineRepository.getProductLineByNumber(t.getProductLine());
        synchronized (pl){
        if(pl.getStatus() == Status.Maintenance ){
            logTaskErrors("Sorry the task numbered "+ t.getTaskNumber() + " cannot be executed because the product line "+pl.getId()+" is currently on maintenance" + LocalDate.now());
            return TaskValidation.ProductLineMaintenance;
        }
}
        // 2. Reserve (CRITICAL PART)
        for (String name : p.getRecipe().keySet()) {

            Item item = itemRepository.getByName(name);
            synchronized (item){
            int needed = p.getRecipe().get(name) * req;
            item.setAvailableQuantity(item.getAvailableQuantity() - needed);
            }
        }
        this.taskRepository.insert(t);
        return TaskValidation.Valid;
    }

    public void runTask(Task t){

        Product product = this.productRepository.getByName(t.getRequestedProduct().trim());
        int req = t.getRequestedQuantity();

        ProductLine productLine = this.productLineRepository.getProductLineByNumber(t.getProductLine()) ;

            if (t.getStatus() != TaskStatus.Cancelled) {
                t.setWorking(true);
                for (int i = 1; i <= t.getRequestedQuantity(); i++) {
                    if (t.getStatus() == TaskStatus.Cancelled || productLine.getStatus() == Status.Maintenance) {
                        break;
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        DataWriter.writeErrors(e);
                    }
                    t.setEndDate(t.getStartDate().plusDays(i));
                    t.setProgressPercentage(i * 100 / t.getRequestedQuantity());

                }

            }


            float percent = (float)t.getProgressPercentage()/100;
            product.setAmount((int) (product.getAmount() + (req * percent)));
            if(t.getProgressPercentage() == 100 ){
                t.setStatus(TaskStatus.Completed);
            }else {
                t.setStatus(TaskStatus.Cancelled);
                this.returnUnusedResources(t, percent);
            }

        this.saveAll();
}


    private void returnUnusedResources(Task t, float percent) {
        Product product = productRepository.getByName(t.getRequestedProduct());
        int produced = (int) (percent * t.getRequestedQuantity());
        int notProduced = t.getRequestedQuantity() - produced;

        for (String name : product.getRecipe().keySet()) {
            Item item = itemRepository.getByName(name);
            int usage = product.getRecipe().get(name);
            item.setAvailableQuantity(
                    item.getAvailableQuantity() + usage * notProduced
            );

            if (!item.isStockSufficient()) {
                notifyIfBelowMinimum(item.getName());
                item.setStatus(ItemStatus.BelowMinimum);
            }
        }
    }

    public void notifyIfBelowMinimum(String message){
        System.out.println( message + " is below minimum ");
    }

    public void cancelTask(Task t){
        t.setStatus(TaskStatus.Cancelled);
        t.setWorking(false);
    }



}
