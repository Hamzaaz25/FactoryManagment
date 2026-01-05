package IO;

import Enums.*;
import Model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class DataReader {

    public  ArrayList<GenericArrayType> readCSV(String path ){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            boolean skipFirst = true;

            while ((line = br.readLine()) != null) {

                if (skipFirst) {
                    skipFirst = false;
                    continue;
                }

                String[] row = line.split(",");

                String username = row[0].trim();
                String pass = row[1].trim();
                Role role = Role.valueOf(row[2].trim());

            }
            return new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static ArrayList<User> readUsers(String path) {
        ArrayList<User> listOfUsers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            boolean skipFirst = true;

            while ((line = br.readLine()) != null) {

                if (skipFirst) {
                    skipFirst = false;
                    continue;
                }

                String[] row = line.split(",");
                String username = row[0].trim();
                String email = row[1].trim();
                String pass = row[2].trim();
                Role role = Role.valueOf(row[3].trim());
                User u = new User(username,email,pass, role);
                listOfUsers.add(u);


            }
            return listOfUsers;
        }
        catch (IOException e) {

            System.out.println("h");
            return listOfUsers;
        }catch (IllegalArgumentException il){
            System.out.println("Not valid file content");
            return new ArrayList<>();
        }
    }

    public static ArrayList<Item> readItems(String path) {
        ArrayList<Item> listOfItems = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            boolean skipFirst = true;

            while ((line = br.readLine()) != null) {

                if (skipFirst) {
                    skipFirst = false;
                    continue;
                }

                String[] row = line.split(",");


                String name = row[1].trim();
                MaterialType type = MaterialType.valueOf(row[2].trim());
                double price = Double.parseDouble(row[3].trim());
                int available = Integer.parseInt(row[4].trim());
                int miniAllowed = Integer.parseInt(row[5].trim());


                Item item = new Item(name ,price , available,type ,miniAllowed);
                listOfItems.add(item);


            }
            return listOfItems;
        }
        catch (IOException e) {
            System.out.println("h");
            return listOfItems;
        }catch (IllegalArgumentException il){
            System.out.println("Not valid file content Items");
            return new ArrayList<>();
        }
    }
public static ArrayList<Product> readProducts(String path){

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        ArrayList<Product> listOfProducts = new ArrayList<>();
        String line;
        boolean skipFirst = true;

        while ((line = br.readLine()) != null) {

            if (skipFirst) {
                skipFirst = false;
                continue;
            }
            HashMap<String , Integer> recipe = new HashMap<>();
            String[] row = line.split(",");


            String name = row[1].trim();

            int amount = Integer.parseInt(row[2].trim());

            for(int i =3; i+1<row.length;i+=2){
                String key = row[i].trim();
                int value =Integer.parseInt(row[i+1].trim());

                recipe.put(key , value);

            }
            Product product = new Product(name,amount ,recipe);
            listOfProducts.add(product);



        }
        return listOfProducts;
    }
    catch (IOException e) {
        e.printStackTrace();
        System.out.println("h");
        return new ArrayList<>();
    }catch (IllegalArgumentException il){
        System.out.println("Not valid file content Products");
        return new ArrayList<>();
    }
}

    public static ArrayList<ProductLine> readProductLines(String path) {
        ArrayList<ProductLine> listOfProductLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            boolean skipFirst = true;

            while ((line = br.readLine()) != null) {

                if (skipFirst) {
                    skipFirst = false;
                    continue;
                }

                String[] row = line.split(",");
//               int productLineNumber = Integer.parseInt(row[0].trim());

                String name = row[1].trim();
                Status s = Status.valueOf(row[2].trim());
                ArrayList<Integer> tasksNumbers = new ArrayList<>();
                for(int i=4 ; i< row.length ;i++){
                    tasksNumbers.add(Integer.parseInt(row[i].trim()));

                }
                ProductLine pl = new ProductLine(name,s,tasksNumbers);
                listOfProductLines.add(pl);


            }
            return listOfProductLines;
        }
        catch (IOException e) {

            System.out.println(e.getCause());
            return listOfProductLines;
        }catch (IllegalArgumentException il){
            System.out.println("Not valid file content Pl");
            return new ArrayList<>();
        }
    }

    public static ArrayList<Task> readTasks(String path) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            boolean skipFirst = true;

            while ((line = br.readLine()) != null) {

                if (skipFirst) {
                    skipFirst = false;
                    continue;
                }

                String[] row = line.split(",");
                int taskNumber = Integer.parseInt(row[0].trim());
                String requestedProduct = row[1].trim();
                int quantity = Integer.parseInt(row[2].trim());
                String client = row[3].trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

                LocalDate start = LocalDate.parse(row[4].trim(), formatter);


                LocalDate end = LocalDate.parse(row[5].trim(), formatter);

                TaskStatus tstatus = TaskStatus.valueOf(row[6].trim());

                int productLineNumber = Integer.parseInt(row[7].trim());

                int progress = Integer.parseInt(row[8].trim());

                Task task = new Task(taskNumber ,requestedProduct,quantity,client,start,end,tstatus,productLineNumber,progress );
                listOfTasks.add(task);
            }
            return listOfTasks;
        }
        catch (IOException e) {

            System.out.println(e.toString());
            return listOfTasks;
        }catch (IllegalArgumentException il){
            System.out.println("Not valid file content Model.Task");
            return new ArrayList<>();
        }
    }


}













