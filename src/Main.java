import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {

        LoginModel l = new LoginModel();
    LoginResult p = l.authenticate("hamza@gmail.com", "hamza123");
        if (p == LoginResult.Success)
            System.out.println("Success");
        else if (p == LoginResult.UserNotFound)
            System.out.println("User not found");
        else if (p == LoginResult.IncorrectPassword)
            System.out.println("Incorrect Password");
        else if (p == LoginResult.Empty)
            System.out.println("Email Or Password cannot be empty");
        else if(p==LoginResult.InvalidEmail)
           System.out.println("Email is Invalid");



    }
}