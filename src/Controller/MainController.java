package Controller;

import Enums.Role;
import Model.ItemRepository;
import Model.ProductRepository;
import Model.User;
import View.BaseFrame;
import View.ProductsView;

public class MainController {
    BaseFrame frame  ;
    ProductRepository productRepository = new ProductRepository();
    ItemRepository itemRepository = new ItemRepository();
    public MainController(){
        productRepository.load();
        itemRepository.load();
    new LoginController();

    }
public void onLoginSuccess(User user){
        if(user.getRole()== Role.Manager){
          frame = new BaseFrame(user.getUsername() , user.getRole().toString());
          new ItemController(itemRepository , frame , user);
        }
        if(user.getRole()== Role.Supervisor){
            frame = new BaseFrame(user.getUsername() , user.getRole().toString());
            new ProductController(productRepository , frame ,user);


        }
}

}
