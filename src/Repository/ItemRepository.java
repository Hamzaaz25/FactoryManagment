package Repository;

import IO.DataReader;
import IO.DataWriter;
import Model.Item;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ItemRepository {

    ArrayList<Item> listOfItems = new ArrayList<>();
    ConcurrentHashMap<String  , Item> itemNames = new ConcurrentHashMap<>();

    public ItemRepository(){

    }

    public void load(){
        this.listOfItems = DataReader.readItems("./Files/Items.csv");
        for(Item item : this.listOfItems){
            itemNames.put(item.getName(), item);

        }
    }

    public void save( ){
        DataWriter.writeItems("./Files/Items.csv" , this.listOfItems);
    }
    public Item getByName(String name){
        name = name.trim();
        return itemNames.get(name);
    }

    public void removeByName(String name){
        name = name.trim();
        this.listOfItems.remove(getByName(name));
        this.itemNames.remove(name);
    }

    public void insert(Item item){
        this.listOfItems.add(item);
        this.itemNames.put(item.getName().trim(), item);
    }

    public boolean containsName(String name){
        name = name.trim();
        return this.itemNames.containsKey(name);
    }

    public ArrayList<Item> getList() {
        return listOfItems;
    }
}
