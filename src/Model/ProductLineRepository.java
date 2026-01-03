package Model;

import IO.DataReader;
import IO.DataWriter;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductLineRepository {
    ArrayList<ProductLine> listOfProductLines = new ArrayList<>();
    HashMap<Integer , ProductLine> productLineNumbers = new HashMap<>();
    private final TaskRepository taskRepository;
    public ProductLineRepository(TaskRepository tr){
       this.taskRepository = tr;
    }

    public void load(String path){
        this.listOfProductLines = DataReader.readProductLines(path);
        for(ProductLine pl : this.listOfProductLines){
            productLineNumbers.put(pl.getId() , pl);
            for(Integer i : pl.listOfTaskNumbers){
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

}
