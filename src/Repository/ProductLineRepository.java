package Repository;

import IO.DataReader;
import IO.DataWriter;
import Model.ProductLine;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductLineRepository {
    ArrayList<ProductLine> listOfProductLines = new ArrayList<>();
    HashMap<Integer , ProductLine> productLineNumbers = new HashMap<>();
    private final TaskRepository taskRepository;
    public ProductLineRepository(TaskRepository tr){
       this.taskRepository = tr;
    }

    public void load(){
        this.listOfProductLines = DataReader.readProductLines("./Files/ProductLines.csv");
        for(ProductLine pl : this.listOfProductLines){
            productLineNumbers.put(pl.getId() , pl);
            for(Integer i : pl.getListOfTaskNumbers()){
                //get the task by its number from the map of tasks
                pl.getTaskLine().add(this.taskRepository.getTaskByNumber(i));
            }
        }
    }

    public void save(){
        DataWriter.writeProductLines("./Files/ProductLines.csv" , this.listOfProductLines);
    }

    public ProductLine getProductLineByNumber(int num){
        return productLineNumbers.get(num);
    }

    public void insert(ProductLine productLine){
        this.listOfProductLines.add(productLine);
        this.productLineNumbers.put(productLine.getId() , productLine);
    }

    public ArrayList<ProductLine> getList() {
        return listOfProductLines;
    }
}
