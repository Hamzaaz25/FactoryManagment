import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Map;

public class DataWriter {

    public static void writeItems(String path,ArrayList<Item> list ){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path ))) {
            bw.write("id , name ,price , available ");
            bw.newLine();
            for(Item b : list){
                bw.write(b.getId() + " , " + b.getName() +" , " +b.getType()+" , "+b.getPrice() +" , " + b.getAvailableQuantity());
                bw.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeProducts(String path,ArrayList<Product> list ){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path ))) {
            bw.write("Name , Amount , Item , Items Quantity ");
            bw.newLine();
            for(Product b : list){
                bw.write(b.getName() +" , " +b.getAmount()+" , ");
                for(Map.Entry entry : b.getRecipe().entrySet()){
                    bw.write(entry.getKey() + " , " +entry.getValue() +" , ");


                }
                bw.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void writeUsers(String path,ArrayList<User> list ){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path ))) {
            bw.write("User , Password , Role");
            bw.newLine();
            for(User b : list){
                bw.write(b.getUsername() + " , " + b.getPassword() +" , " +b.getRole());
                bw.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
