package Controller;

import Enums.Role;
import Model.ProductRepository;
import Model.User;
import View.BaseFrame;
import View.ProductsView;

public class MainController {
    BaseFrame frame ;
    ProductRepository productRepository = new ProductRepository();
    public MainController(){
        productRepository.load();
    new LoginController();

    }
public void onLoginSuccess(User user){
        if(user.getRole()== Role.Manager){
          frame = new BaseFrame(user.getUsername() , user.getRole().toString());

        }
        if(user.getRole()== Role.Supervisor){
            frame = new BaseFrame(user.getUsername() , user.getRole().toString());
            new ProductController(productRepository , frame ,user);


        }
}

}
