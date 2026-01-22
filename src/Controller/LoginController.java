package Controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.*;
import Model.LoginModel;

public class LoginController {
    LoginModel model ;
    LoginView view ;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(model != null){
                Enums.LoginResult p =  model.authenticate(view.getUser(), view.getPass());
                if (p == Enums.LoginResult.Success){
                    System.out.println("Success");
                    view.setVisible(false);
                    view.dispose();
                    new MainController().onLoginSuccess(model.getLoggedUser());
                }
                else if (p == Enums.LoginResult.UserNotFound)
                    view.showError("User not found");
                else if (p == Enums.LoginResult.IncorrectPassword)
                    view.showError("Incorrect Password");
                else if (p == Enums.LoginResult.Empty)
                    view.showError("Email Or Password cannot be empty");
                else if(p==Enums.LoginResult.InvalidEmail)
                    view.showError("Email is Invalid");

            }
        }
    };
//TIP Amin
public LoginController() {
    this.model = new LoginModel();
    this.view = new LoginView();
    this.view.setVisible(true);
    view.addLoginListener(actionListener);
}

}
