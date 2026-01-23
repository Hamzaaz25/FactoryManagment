package Controller;

import Enums.Role;
import Model.*;
import Repository.ItemRepository;
import Repository.ProductLineRepository;
import Repository.ProductRepository;
import Repository.TaskRepository;
import View.BaseFrame;
import View.ManagerBaseFrame;
import View.ProfileView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    BaseFrame frame  ;
    ProductRepository productRepository = new ProductRepository();
    ItemRepository itemRepository = new ItemRepository();
    TaskRepository taskRepository = new TaskRepository();
    LoginController loginController ;
    InventoryService inventoryService = new InventoryService(itemRepository  ,productRepository);
    ProductLineRepository productLineRepository = new ProductLineRepository(taskRepository);
    TaskService taskService = new TaskService(itemRepository,productRepository,taskRepository,productLineRepository);
    ProductLineManager productLineManager = new ProductLineManager(productLineRepository,taskService);
    private static boolean loaded = false;
    public MainController(){

    loginController= new LoginController();
    }
public void onLoginSuccess(User user){
    this.loadAll();
        if(user.getRole()== Role.Manager){
            ManagerBaseFrame baseFrame = new ManagerBaseFrame(user.getUsername() , "Product Lines");
            new ManagerController(productLineRepository,productLineManager,baseFrame);

            baseFrame.getProfileButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ProfileView profileView = new ProfileView(user);
                    baseFrame.switchContent(profileView , "Profile");
                    profileView.getLogoutButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            baseFrame.setVisible(false);
                            loginController.view.setVisible(true);
                        }
                    });
                }
            });
        }

        if(user.getRole()== Role.Supervisor){
            frame = new BaseFrame(user.getUsername() , user.getRole().toString());
            new ProductController(productRepository , inventoryService,frame ,itemRepository,taskRepository ,productLineRepository);




            frame.getItemsButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ItemController(itemRepository,inventoryService,frame );
                }
            });

            frame.getProductLinesButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductLineController(productLineRepository , taskRepository ,taskService,productLineManager,productRepository,frame );
                }
            });

            frame.getProductsButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductController(productRepository , inventoryService,frame , itemRepository ,taskRepository ,productLineRepository);
                }
            });

            frame.getTasksButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new TaskController(taskRepository , frame);
                }
            });
            frame.getProfileButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ProfileView profileView = new ProfileView(user);
                    frame.switchContent(profileView , "Profile");
                    profileView.getLogoutButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.setVisible(false);
                            loginController.view.setVisible(true);
                        }
                    });
                }
            });

        }


}


public void loadAll(){
        if(loaded) return;

        this.itemRepository.load();
        this.taskRepository.load();
        this.productRepository.load();
        this.productLineRepository.load();

        for(ProductLine pl : productLineRepository.getList()){
            productLineManager.register(pl);
        }
loaded = true;
}

}


