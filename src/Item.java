public class Item {
    private int id;
    private String name;
    private double price ;
    private int availableQuantity;
    static int count = 0 ;
public Item(){
    this.id = ++count;

}

}
