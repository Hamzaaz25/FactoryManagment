import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private int id;
    private int amount;
    private String name;
    private HashMap <String , Integer> products = new HashMap<>();
    private HashMap <String , Integer > recipe = new HashMap<>();
    static int count =0;
    public Product(String name, int amount, HashMap<String, Integer> recipe) {
        this.id = ++count;
        this.name = name;
        this.amount = amount;
        this.recipe = recipe;
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
}
