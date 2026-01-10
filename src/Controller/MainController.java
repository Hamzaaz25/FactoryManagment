package Controller;

import Enums.Role;
import Repository.ItemRepository;
import Repository.ProductRepository;
import Repository.TaskRepository;
import Model.User;
import View.BaseFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    BaseFrame frame  ;
    ProductRepository productRepository = new ProductRepository();
    ItemRepository itemRepository = new ItemRepository();
    TaskRepository taskRepository = new TaskRepository();
    public MainController(){
    new LoginController();

    }
public void onLoginSuccess(User user){
        this.loadAll();
        if(user.getRole()== Role.Manager){
          frame = new BaseFrame(user.getUsername() , user.getRole().toString());
          new ItemController(itemRepository , frame );
        }
        if(user.getRole()== Role.Supervisor){
            frame = new BaseFrame(user.getUsername() , user.getRole().toString());
            new ProductController(productRepository , frame ,user);
            frame.getItemsButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ItemController(itemRepository,frame );
                }
            });

            frame.getProductsButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductController(productRepository , frame ,user);
                }
            });

            frame.getTasksButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new TaskController(taskRepository , frame);
                }
            });

        }
}


public void loadAll(){
        this.itemRepository.load();
        this.productRepository.load();
        this.taskRepository.load();
}

}
