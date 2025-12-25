import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class DataWriter {

    public static void writeItems(String path,ArrayList<Item> list ){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path ))) {
            bw.write("id , name ,price , available ");
            bw.newLine();
            for(Item b : list){
                bw.write(b.getId() + " , " + b.getName() +" , " +b.getType()+" , "+b.getPrice() +" , " + b.getAvailableQuantity() + "," +b.getMinimumAllowedQuantity());
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
            bw.write("User , Password , Enums.Role");
            bw.newLine();
            for(User b : list){
                bw.write(b.getUsername() + " , " + b.getPassword() +" , " +b.getRole());
                bw.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeProductLines(String path,ArrayList<ProductLine> list ){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path ))) {
            bw.write("ProductLineNumber , Name ,Enums.Status , Tasks  ");
            bw.newLine();
            for(ProductLine pl : list){
                bw.write( pl.getId()+ " , " + pl.getName() +" , " +pl.getStatus() +", Assigned Tasks : ," );
                for(Task t : pl.getTaskLine())
                    bw.write(t.getTaskNumber() + ",");
                bw.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeTasks(String path,ArrayList<Task> list ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path ))) {
            bw.write("Task Number , Requested Products , Quantity , Client , Start Date , End Date , Status ,PL number ,Progress  ");
            bw.newLine();
            for(Task t : list){
                bw.write( t.getTaskNumber()+ " , " + t.getRequestedQuantity() +" , " + t.getRequestedQuantity() +"," +t.getClientName() +"," + t.getStartDate().format(formatter) + "," + t.getEndDate().format(formatter) + ", " + t.getStatus()+"," +t.getProductLine() +"," + t.getProgressPercentage());

                bw.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
