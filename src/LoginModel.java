import java.util.ArrayList;
import java.util.Scanner;

public class LoginModel {
    Scanner in = new Scanner(System.in);
    ArrayList<User> listOfUsers = new ArrayList<>();
    User LoggedUser;

    public LoginModel(){
       this.listOfUsers= DataReader.readUsers("./Files/Users.csv");
    }

    public LoginResult authenticate(String tempUser , String tempPass) {
        if (tempUser.isEmpty() || tempPass.isEmpty())
            return LoginResult.Empty;
        if(!tempUser.contains("@"))
            return LoginResult.InvalidEmail;
        User temp = new User(tempUser, tempPass);
        for (User u : this.listOfUsers) {

            if (u.isEmailEquals(temp)) {
                if (u.isPassEqual(temp)) {
                    this.LoggedUser = u;
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


}
