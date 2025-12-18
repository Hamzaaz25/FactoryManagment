import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    Scanner in = new Scanner(System.in);
    ArrayList<User> listOfUsers = new ArrayList<>();
    public Login(){
       this.listOfUsers= DataReader.readUsers("./Files/Users.csv");
    }

    public void login(){
        String tempUser = in.nextLine();

        String tempPass = in.nextLine();
        User temp = new User(tempUser,tempPass);
        for(User u : this.listOfUsers){

            if(u.isEqual(temp)){
                System.out.println("Login success");
            }


        }

    }



}
