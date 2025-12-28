import Enums.Status;
import Enums.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class DataManager {
    static private volatile DataManager instance;
    ArrayList<Item> listOfItems = new ArrayList<>();
    ArrayList<Product> listOfProducts = new ArrayList<>();
    ArrayList<ProductLine> listOfProductLines = new ArrayList<>();
    ArrayList<Task> listOfTasks = new ArrayList<>();
    HashMap <String , Product > productsNames = new HashMap<>();
    HashMap <String , Item > itemsNames = new HashMap<>();
    HashMap <Integer , Task> tasksNumbered = new HashMap<>();

    private DataManager(){
        this.init();
    }


    public static DataManager getInstance(){
        DataManager result = instance;
        if(result == null){
            synchronized(DataManager.class) {
                result = instance;
                if(result == null)
                    instance = new DataManager();
            }
        }
        return result;
    }

    public void init(){
        this.listOfItems = DataReader.readItems("./Files/Items.csv");
        this.listOfTasks = DataReader.readTasks("./Files/Tasks.csv");
        this.listOfProducts = DataReader.loadProducts("./Files/Products.csv");
        this.listOfProductLines = DataReader.readProductLines("./Files/ProductLines.csv");
        for(Task t : this.listOfTasks){
            tasksNumbered.put(t.getTaskNumber() ,t);
        }
        for(ProductLine pl :this.listOfProductLines){
            for(Integer i : pl.listOfTaskNumbers){
                //get the task by its number from the map of tasks
                pl.getTaskLine().add(this.tasksNumbered.get(i));
            }

        }

        for(Product p: listOfProducts){
            this.productsNames.put(p.getName() , p);
        }
        for(Item item : this.listOfItems){
            this.itemsNames.put(item.getName() ,item);
        }
        }

    public void addItem(Item item) {

        if(itemsNames.containsKey(item.getName())){
            //Increment the available quantity if the Item's name exist
            itemsNames.get(item.getName()).incrementAvailableQuantity(item.getAvailableQuantity());
        }
        else{
            listOfItems.add(item);
            this.itemsNames.put(item.getName().trim() , item);
        }
        DataWriter.writeItems("./Files/Items.csv" , this.listOfItems );

    }

    public void removeItem(String name){
        name.trim();
        this.listOfItems.remove(itemsNames.get(name));
        itemsNames.remove(name);
        int count =0;
        for(Item i :listOfItems){

            i.setId(++count);
        }
        DataWriter.writeItems("src/h.csv" , this.listOfItems );

    }

    public void editItem(Item item){


    }

    public void addProductLine(ProductLine pl){
        listOfProductLines.add(pl);
        DataWriter.writeProductLines("./Files/ProductLines.csv" , this.listOfProductLines);

    }

    public void editProductLineStatus(int id , Status s){
        for(ProductLine pl : this.listOfProductLines){
            if(pl.getId() == id){
                pl.setStatus(s);

            }
            DataWriter.writeProductLines("./src/h.csv" , this.listOfProductLines);
        }

    }

    public void displayProductLinePerformance(int id){
        for(ProductLine pl : this.listOfProductLines){

            if(pl.getId() == id){
                for (Task t : this.listOfTasks){
                    System.out.println(t.getProgressPercentage() + "  ");

                }
            }
        }

    }

    public void isTaskValid(int taskNumber) {

               Task t = getTaskByNumber(taskNumber);
                // Get the requested product and its required quantity
                Product p = productsNames.get(t.getRequestedProduct().trim());
                int req = t.getRequestedQuantity();

                // Check each ingredient in the product's recipe
                for (String key : p.getRecipe().keySet()) {
                    Item item = itemsNames.get(key);
                    int usage = p.getRecipe().get(key) * req;
                    if (item == null || !item.isStockSufficient() || item.getAvailableQuantity() <= usage) {
                        t.setValid(false);
                        return; // immediately return if any item fails
                    }
                }
                t.setValid(true);
    }

    public Task getTaskByNumber(int taskNumber){
        return tasksNumbered.get(taskNumber);
    }

    public void addTask(int pln , Task t){
        ProductLine pl = null;
        for(ProductLine p :this.listOfProductLines ){
            if(p.getId() ==pln ){
                pl = p;

            }
        }
        this.listOfTasks.add(t);
        this.tasksNumbered.put(t.getTaskNumber() , t);
//        DataWriter.writeTasks("./Files/Tasks.csv",this.listOfTasks);
        this.isTaskValid(t.getTaskNumber());
        if(t.isValid() )
            pl.taskQueue.push(t);
        else
            System.out.println("Sorry this task cannot be executed because ");


    }

    public void runTask(Task t){
           this.isTaskValid(t.getTaskNumber());
            Product p = productsNames.get(t.getRequestedProduct().trim());
            int req = t.getRequestedQuantity();

            ProductLine productLine = null ;
            ArrayList<Item> temp = new ArrayList<>();
            for(ProductLine pl : this.listOfProductLines) {
                if (pl.getId() == t.getProductLine()) {
                    productLine = pl;

                }
            }
            if(t.isValid() && Objects.requireNonNull(productLine).getStatus() != Status.Maintenance) {
                outer:
                for (String key : p.getRecipe().keySet()) {
                    temp.add(itemsNames.get(key));
                    int usage = p.getRecipe().get(key) * req;
                    itemsNames.get(key).setAvailableQuantity( itemsNames.get(key).getAvailableQuantity() - usage);
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
                        t.setEndDate(t.getStartDate().plusDays(i));
                        t.setProgressPercentage(i * 100 / t.getRequestedQuantity());

                    }

                }




        float percent = (float)t.getProgressPercentage()/100;
        p.setAmount((int) (p.getAmount() + (req * percent)));
        if(t.getProgressPercentage() == 100 ){
            t.setStatus(TaskStatus.Completed);
        }else t.setStatus(TaskStatus.Cancelled);
        for(Item item : temp){
            int produced = (int) (percent * req);
            int notpProduced = req -produced;
            int usagePerProduct = p.getRecipe().get(item.getName().trim());
//          System.out.println(item.getName() + " "+item.getAvailableQuantity() +" " +usagePerProduct+" "+produced +"not" + notpProduced);
            item.setAvailableQuantity(item.getAvailableQuantity() + (usagePerProduct * notpProduced));
//          System.out.println(item.getName() + " "+item.getAvailableQuantity() +" " +usagePerProduct+" "+produced +"not" + notpProduced);

                }

            }
     else{
                t.setStatus(TaskStatus.Cancelled);
                t.setEndDate(LocalDate.now());
                System.out.println("MEow");
            }
        DataWriter.writeProducts("src/p.csv" , this.listOfProducts);
        DataWriter.writeTasks("src/t.csv" , this.listOfTasks);
        DataWriter.writeItems("src/h.csv" , this.listOfItems);
    }

    public void cancelTask(Task t){
        t.setStatus(TaskStatus.Cancelled);
        t.setWorking(false);
    }
}
