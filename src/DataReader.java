import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

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
                String pass = row[1].trim();
                Role role = Role.valueOf(row[2].trim());
                User u = new User(username,pass, role);
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

}

