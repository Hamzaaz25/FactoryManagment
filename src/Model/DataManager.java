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
        this.listOfProducts = DataReader.readProducts("./Files/Products.csv");
        this.listOfProductLines = DataReader.readProductLines("./Files/ProductLines.csv");
        for(Task t : this.listOfTasks){
            tasksNumbered.put(t.getTaskNumber() ,t);
        }
        DataWriter.writeItems("./Files/Items.csv" , this.listOfItems);
        for(ProductLine pl :this.listOfProductLines){
            for(Integer i : pl.getListOfTaskNumbers()){
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
