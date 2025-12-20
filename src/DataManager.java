import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    ArrayList<Item> listOfItems = new ArrayList<>();
    ArrayList<Product> listOfProducts = new ArrayList<>();
    ArrayList<ProductLine> listOfProductLines = new ArrayList<>();
    ArrayList<User> listOfUsers = new ArrayList<>();
    HashMap <String , Product > productsAmount = new HashMap<>();
    HashMap <String , Item > itemsNames = new HashMap<>();
    HashMap <Integer , Task> tasksNumbered = new HashMap<>();

    public DataManager(){
        this.init();
    }

    public void init(){
        this.listOfItems = DataReader.readItems("./Files/Items.csv");

        this.listOfProducts = DataReader.loadProducts("./Files/Products.csv");
        this.listOfProductLines = DataReader.readProductLines("./Files/ProductLines.csv");
        for(ProductLine p :this.listOfProductLines){
            System.out.println(p.getId() + " " +p.getName() + " "+p.getStatus() +" " );
            for(Task t : p.getTaskLine()){
                System.out.println(t.getStartDate() + t.getClientName() + t.getTaskNumber());
            }
        }
        for(Product p: listOfProducts){
            this.productsAmount.put(p.getName() , p);
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
            this.itemsNames.put(item.getName() , item);
        }
        DataWriter.writeItems("./src/h.csv" , this.listOfItems );

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
    }

    public void editProductLineStatus(){

    }

    public void displayProductLinePerformance(){

    }



}
