package Model;

import Enums.LoginResult;
import java.util.ArrayList;
import java.util.Scanner;
import IO.*;
public class LoginModel {
    Scanner in = new Scanner(System.in);
    ArrayList<User> listOfUsers = new ArrayList<>();
    User LoggedUser;

    public LoginModel(){
       this.listOfUsers= DataReader.readUsers("./Files/Users.csv");
    }

    public LoginResult authenticate(String tempEmail , String tempPass) {
        if (tempEmail.isEmpty() || tempPass.isEmpty())
            return LoginResult.Empty;
        if(!this.emailValid(tempEmail))
            return LoginResult.InvalidEmail;
        User temp = new User(tempEmail, tempPass);
        for (User u : this.listOfUsers) {

            if (u.isEmailEquals(temp)) {
                if (u.isPassEqual(temp)) {
                    this.LoggedUser = u;
                    System.out.println("Welcome " + u.getUsername());
                    return LoginResult.Success;
                } else {
                    return LoginResult.IncorrectPassword;
                }
            }


        }
        return LoginResult.UserNotFound;
    }

    public User getLoggedUser(){
        if(this.LoggedUser != null)
           return LoggedUser;
        return null;
    }

    private boolean emailValid(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{3,6}$");
    }


}
