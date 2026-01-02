package Model;

import IO.DataReader;
import IO.DataWriter;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductLineRepository {
    ArrayList<ProductLine> listOfProductLines = new ArrayList<>();
    HashMap<Integer , ProductLine> productLineNumbers = new HashMap<>();

    public ProductLineRepository(){

    }

    public void load(String path){
        this.listOfProductLines = DataReader.readProductLines(path);
        for(ProductLine pl : this.listOfProductLines)
            productLineNumbers.put(pl.getId() , pl);
    }

    public void save(){
        DataWriter.writeProductLines("./Files/ProductLines.csv" , this.listOfProductLines);
    }

    public ProductLine getProductLineByNumber(int num){
        return productLineNumbers.get(num);
    }

}
