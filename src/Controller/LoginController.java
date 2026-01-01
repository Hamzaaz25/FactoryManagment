package Controller;

import Enums.Role;
import View.ItemFrame;
import View.ProductFrame;

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
                    if(model.getLoggedUser().getRole() == Role.Manager){new ProductFrame(model.getLoggedUser().getUsername());
                    }
                    else{new ItemFrame(model.getLoggedUser().getUsername());
                    }

                }
                else if (p == Enums.LoginResult.UserNotFound)
                    System.out.println("Model.User not found");
                else if (p == Enums.LoginResult.IncorrectPassword)
                    System.out.println("Incorrect Password");
                else if (p == Enums.LoginResult.Empty)
                    System.out.println("Email Or Password cannot be empty");
                else if(p==Enums.LoginResult.InvalidEmail)
                    System.out.println("Email is Invalid");

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
