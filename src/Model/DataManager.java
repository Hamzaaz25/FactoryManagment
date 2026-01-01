package Model;

import Enums.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import IO.*;
import Exceptions.*;

public class DataManager {
    static private volatile DataManager instance;
    ArrayList<Item> listOfItems = new ArrayList<>();
    ArrayList<Product> listOfProducts = new ArrayList<>();
    ArrayList<ProductLine> listOfProductLines = new ArrayList<>();
    ArrayList<Task> listOfTasks = new ArrayList<>();
    HashMap <String , Product > productsNames = new HashMap<>();
    HashMap <String , Item > itemsNames = new HashMap<>();
    HashMap <Integer , Task> tasksNumbered = new HashMap<>();
    HashMap <Integer ,ProductLine> productLineHashMap = new HashMap<>();

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
        DataWriter.writeItems("./Files/Items.csv" , this.listOfItems);
        for(ProductLine pl :this.listOfProductLines){
            for(Integer i : pl.listOfTaskNumbers){
                //get the task by its number from the map of tasks
                pl.getTaskLine().add(this.tasksNumbered.get(i));
            }
            productLineHashMap.put(pl.getId() , pl);
        }

        for(Product p: listOfProducts){
            this.productsNames.put(p.getName() , p);
        }
        for(Item item : this.listOfItems){
            this.itemsNames.put(item.getName() ,item);
        }
        }

    public void addItem(String name ,MaterialType mt  ,int quantity ,double price) {

        if(itemsNames.containsKey(name)){
            //Increment the available quantity if the Model.Item's name exist
            itemsNames.get(name).incrementAvailableQuantity(quantity);
        }
        else{
            Item item = new Item(name , mt ,price , quantity );
            listOfItems.add(item);
            this.itemsNames.put(item.getName().trim() , item);
        }
        DataWriter.writeItems("./Files/Items.csv" , this.listOfItems );

    }

    public void removeItem(String name){
        name.trim();
        if(!itemsNames.containsKey(name)){
            return;
        }
        this.listOfItems.remove(itemsNames.get(name));
        itemsNames.remove(name);
        DataWriter.writeItems("src/h.csv" , this.listOfItems );

    }

    public void editItem(String name , int quantity , double price , int minimum ){
        if(itemsNames.containsKey(name)) {
            Item item = itemsNames.get(name.trim());
            item.setAvailableQuantity(quantity);
            item.setPrice(price);
            item.setMinimumAllowedQuantity(minimum);
        }
        DataWriter.writeItems("src/h.csv" , this.listOfItems );
    }

    public void addProduct(String name , int amount , HashMap<String , Integer> recipe){
        if(productsNames.containsKey(name.trim())){
            //Increment the available amount if the Model.Product's name exist
            productsNames.get(name.trim()).incrementAmount(amount);
        }
        else{
            Product product = new Product(name , amount ,recipe );
            listOfProducts.add(product);
            this.productsNames.put(product.getName().trim() , product);
        }
        DataWriter.writeItems("./Files/Items.csv" , this.listOfItems );
    }

    public void removeProduct(String name){
        if(productsNames.containsKey(name.trim())){
            listOfProducts.remove(productsNames.get(name));
            productsNames.remove(name);
        }
        DataWriter.writeProducts("./Files/Products.csv" , this.listOfProducts);
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

    public ProductLine getProductLineByNumber(int n){
        return productLineHashMap.get(n);
    }

    public TaskValidation isTaskValid(Task t) {


                // Get the requested product and its required quantity
                Product p = productsNames.get(t.getRequestedProduct().trim());
                ProductLine pl = this.getProductLineByNumber(t.getProductLine());
                int req = t.getRequestedQuantity();
                if(pl.getStatus() == Status.Maintenance){
                    t.setValid(false);
                    return TaskValidation.ProductLineMaintenance;

                }
                // Check each ingredient in the product's recipe
                for (String key : p.getRecipe().keySet()) {
                    Item item = itemsNames.get(key);
                    int usage = p.getRecipe().get(key) * req;
                    if (item == null || !item.isStockAvailable() || item.getAvailableQuantity() <= usage) {
                        t.setValid(false);
                        return TaskValidation.InsufficientStock; // immediately return if any item fails
                    }
                }
                t.setValid(true);
                return TaskValidation.Valid;
    }

    public Task getTaskByNumber(int taskNumber){
        return tasksNumbered.get(taskNumber);
    }

    public TaskValidation registerTask(Task t){
        ProductLine pl = getProductLineByNumber(t.getProductLine());
        System.out.println(this.isTaskValid(t));
        TaskValidation validation = isTaskValid(t);
        if(validation == TaskValidation.Valid){
            this.listOfTasks.add(t);
            this.tasksNumbered.put(t.getTaskNumber() , t);

        }
        else if(validation == TaskValidation.InsufficientStock){
            System.out.println("Sorry the task numbered "+t.getTaskNumber()+" cannot be executed because the items stock is insufficient "   );
            DataWriter.writeErrors("Sorry the task numbered "+t.getTaskNumber()+" cannot be executed because the items stock is insufficient " + LocalDate.now());

        }
        else if(validation == TaskValidation.ProductLineMaintenance){
            System.out.print("Sorry the task numbered "+ t.getTaskNumber() + " cannot be executed because the product line "+pl.getId()+" is currently on maintenance");
            DataWriter.writeErrors("Sorry the task numbered "+ t.getTaskNumber() + " cannot be executed because the product line "+pl.getId()+" is currently on maintenance" + LocalDate.now());

        }
        DataWriter.writeTasks("src/t.csv", this.listOfTasks );


   return validation;
    }

    public void runTask(Task t){
        System.out.println(this.isTaskValid(t));
            Product p = productsNames.get(t.getRequestedProduct().trim());
            int req = t.getRequestedQuantity();

            ProductLine productLine = getProductLineByNumber(t.getTaskNumber()) ;
            ArrayList<Item> temp = new ArrayList<>();
            if(isTaskValid(t) ==TaskValidation.Valid ) {
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
                        t.setEndDate(LocalDate.now());
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
            if(!item.isStockSufficient()){
                notifyIfBelowMinimum(item.getName());
                item.setStatus(ItemStatus.BelowMinimum);
            }
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
                System.out.println("Model.Task in not valid");
            }
        System.out.println(t.getTaskNumber() + "Completed");
        DataWriter.writeProducts("src/p.csv" , this.listOfProducts);
        DataWriter.writeTasks("src/t.csv" , this.listOfTasks);
        DataWriter.writeItems("src/h.csv" , this.listOfItems);
    }

    public void cancelTask(Task t){
        t.setStatus(TaskStatus.Cancelled);
        t.setWorking(false);
    }

    public void notifyIfBelowMinimum(String message){
        System.out.println( message + " is below minimum ");
    }

    public ArrayList<Task> showTasksForProductLine(int pln ){
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task t : this.listOfTasks){
            if(t.getProductLine() == pln)
                tasks.add(t);
        }
        return tasks;
    }

    public ArrayList<Task> showTasksForProduct(String productName ){
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task t : this.listOfTasks){
            if(t.getRequestedProduct().equalsIgnoreCase(productName.trim()))
                tasks.add(t);
        }
        return tasks;
    }


}
