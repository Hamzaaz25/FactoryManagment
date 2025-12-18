import java.util.ArrayList;

public class DataManager {
    ArrayList<Item> listOfItems = new ArrayList<>();
    ArrayList<Product> listOfProducts = new ArrayList<>();
    ArrayList<ProductLine> listOfProductLines = new ArrayList<>();
    ArrayList<User> listOfUsers = new ArrayList<>();


    public void addItem(Item item) {
        listOfItems.add(item);
    }

    public void removeItem(Item item){



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
