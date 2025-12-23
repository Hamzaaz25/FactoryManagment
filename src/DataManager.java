import Enums.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    ArrayList<Item> listOfItems = new ArrayList<>();
    ArrayList<Product> listOfProducts = new ArrayList<>();
    ArrayList<ProductLine> listOfProductLines = new ArrayList<>();
    ArrayList<Task> listOfTasks = new ArrayList<>();
    HashMap <String , Product > productsNames = new HashMap<>();
    HashMap <String , Item > itemsNames = new HashMap<>();
    HashMap <Integer , Task> tasksNumbered = new HashMap<>();

    public DataManager(){
        this.init();
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
        DataWriter.writeProductLines("./src/h.csv" , this.listOfProductLines);

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

    public boolean isTaskValid(int number) {


            for (Task t : this.listOfTasks) {
                if (t.getTaskNumber() == number) {

                    // Get the requested product and its required quantity
                    Product p = productsNames.get(t.getRequestedProduct().trim());
                    int req = t.getRequestedQuantity();

                    // Check each ingredient in the product's recipe
                    for (String key : p.getRecipe().keySet()) {
                        Item item = itemsNames.get(key);
                        int usage = p.getRecipe().get(key) * req;
                        if (item == null || !item.isStockSufficient() || item.getAvailableQuantity() <= usage) {
                            return false; // immediately return if any item fails
                        }
                    }
                    return true;

                }
            }

     return false;
    }



}
