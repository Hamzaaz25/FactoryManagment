package Model;

import Enums.MaterialType;
import IO.DataWriter;

import java.util.HashMap;

public class InventoryService {
    private final ItemRepository itemRepository ;
    private final ProductRepository productRepository ;

    public InventoryService(ItemRepository itemRepository, ProductRepository productRepository) {
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
    }

    public void addItem(String name , MaterialType mt  , int quantity , double price) {

        if(itemRepository.containsName(name)){
            //Increment the available quantity if the Model.Item's name exist
            itemRepository.getByName(name).incrementAvailableQuantity(quantity);
        }
        else{
            Item item = new Item(name , mt ,price , quantity );
            itemRepository.insert(item);

        }
        itemRepository.save();

    }

    public void removeItem(String name){
        name = name.trim();
        if(!itemRepository.containsName(name)){
            return;
        }
        itemRepository.removeByName(name);
        this.itemRepository.save( );

    }

    public void editItem(String name , int quantity , double price , int minimum ){
        name = name.trim();
        if(this.itemRepository.containsName(name)) {
            Item item = this.itemRepository.getByName(name);
            item.setAvailableQuantity(quantity);
            item.setPrice(price);
            item.setMinimumAllowedQuantity(minimum);
            this.itemRepository.save();
        }

    }

    public void addProduct(String name , int amount , HashMap<String , Integer> recipe,String imagePath){
        if(this.productRepository.containsName(name)){
            //Increment the available amount if the Model.Product's name exist
            this.productRepository.getByName(name).incrementAmount(amount);
        }
        else{
            Product product = new Product(name , amount ,recipe ,imagePath );
            this.productRepository.insert(product);
        }
        this.productRepository.save();
    }


    public void removeProduct(String name){
        if(this.productRepository.containsName(name)){
            this.productRepository.deleteByName(name);
        }
        this.productRepository.save();
    }



}
