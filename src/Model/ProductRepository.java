package Model;

import IO.DataReader;
import IO.DataWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ProductRepository {
    ArrayList<Product> listOfProducts = new ArrayList<>();
    ConcurrentHashMap<String , Product> productNames = new ConcurrentHashMap<>();

    public ProductRepository(){

    }

    public void load(){
        this.listOfProducts = DataReader.readProducts("./Files/Products.csv");
        for(Product product :listOfProducts)
            productNames.put(product.getName() , product);
    }

    public void save(){
        DataWriter.writeProducts("./Files/Products.csv", this.listOfProducts);
    }

    public Product getByName(String name){
        name = name.trim();
        return productNames.get(name);
    }

    public void deleteByName(String name){
        name = name.trim();
        this.listOfProducts.remove(getByName(name));
        this.productNames.remove(name);
    }

    public void insert(Product product){
        this.listOfProducts.add(product);
        this.productNames.put(product.getName().trim(), product);
    }

    public boolean containsName(String name){
        name = name.trim();
        return this.productNames.containsKey(name);
    }
}
