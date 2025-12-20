public class Item {
    private int id;
    private String name;
    private double price ;
    private int availableQuantity;
    MaterialType type;
    static int count = 0 ;
public Item(){


}

    public Item(String name,MaterialType type ,double price, int availableQuantity) {
        this.id = ++count;
        this.type = type;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public MaterialType getType() {
        return type;
    }

    public void setType(MaterialType type) {
        this.type = type;
    }

    public void incrementAvailableQuantity(int inc){
       this.availableQuantity +=inc;
    }
}
