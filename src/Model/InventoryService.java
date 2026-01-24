package Model;

import Enums.MaterialType;
import Repository.ItemRepository;
import Repository.ProductRepository;

import java.util.HashMap;

public class InventoryService {
    private final ItemRepository itemRepository ;
    private final ProductRepository productRepository ;

    public InventoryService(ItemRepository itemRepository, ProductRepository productRepository) {
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
    }

    public void addItem(String name , MaterialType mt  , int quantity , double price ,String path) {

        if(itemRepository.containsName(name)){
            //Increment the available quantity if the Model.Item's name exist
            itemRepository.getByName(name).incrementAvailableQuantity(quantity);
        }
        else{
            Item item = new Item(name , mt ,price , quantity ,path);
            itemRepository.insert(item);

        }


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
//            item.setMinimumAllowedQuantity(minimum);

        }

    }

    public void addProduct(String name , int amount ,double price, HashMap<String , Integer> recipe,String imagePath){
        if(this.productRepository.containsName(name)){
            //Increment the available amount if the Model.Product's name exist
            this.productRepository.getByName(name).incrementAmount(amount);
        }
        else{
            Product product = new Product(name , amount,price ,recipe ,imagePath );
            this.productRepository.insert(product);
        }

    }


    public void removeProduct(String name){
        if(this.productRepository.containsName(name)){
            this.productRepository.deleteByName(name);
        }

    }

    public void editProduct(String name , int quantity , double price  ){
        name = name.trim();
        if(this.productRepository.containsName(name)) {
            Product product = this.productRepository.getByName(name);
            product.setAmount(quantity);
            product.setPrice(price);

        }

    }


}
