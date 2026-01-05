package Model;

import java.util.HashMap;

public class Product {
    private int id;
    private int amount;
    private String name;
    private HashMap <String , Integer > recipe = new HashMap<>();
    private String imagePath;
    private double price;



    static int count =0;
    public Product(String name, int amount,double price, HashMap<String, Integer> recipe , String imagePath) {
        this.id = ++count;
        this.name = name;
        this.amount = amount;
        this.recipe = recipe;
        this.price = price;
        this.imagePath = imagePath;
    }

    public Product(String name, int amount) {
        this.id = ++count;
        this.name = name;
        this.amount = amount;
//        this.recipe = recipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Integer> getRecipe() {
        return recipe;
    }

    public void setRecipe(HashMap<String, Integer> recipe) {
        this.recipe = recipe;
    }

    public void incrementAmount(int amount){
        this.amount += amount;
    }

    public String getImage(){
        return this.imagePath;
    }

    public void setImage(String imagePath) {
        this.imagePath = imagePath;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
