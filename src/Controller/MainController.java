package Controller;

import Enums.Role;
import Enums.TaskStatus;
import Model.*;
import Repository.ItemRepository;
import Repository.ProductLineRepository;
import Repository.ProductRepository;
import Repository.TaskRepository;
import View.BaseFrame;
import View.Loading;
import View.ManagerBaseFrame;
import View.ProfileView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    static private volatile MainController instance;
    BaseFrame frame  ;
    ProductRepository productRepository = new ProductRepository();
    ItemRepository itemRepository = new ItemRepository();
    TaskRepository taskRepository = new TaskRepository();
    LoginController loginController ;
    InventoryService inventoryService = new InventoryService(itemRepository  ,productRepository);
    ProductLineRepository productLineRepository = new ProductLineRepository(taskRepository);
    TaskService taskService = new TaskService(itemRepository,productRepository,taskRepository,productLineRepository ,this::notifyIfBelowMinimum);
    ProductLineManager productLineManager = new ProductLineManager(productLineRepository,taskService );
    private static boolean loaded = false;




    public static MainController getInstance(){
        MainController result = instance;
        if(result == null){
            synchronized(MainController.class) {
                result = instance;
                if(result == null)
                    instance = new MainController();

            }
        }
        return result;
    }

    private MainController(){
        loadAll();
//        new Loading().setVisible(true);
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        loginController= new LoginController();
    }


public void onLoginSuccess(User user){
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
                            loginController.reset();
                            baseFrame.setVisible(false);
                            loginController.view.setVisible(true);
                        }
                    });
                }
            });


            baseFrame.getCloseBtn()  .addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(baseFrame,
                        "Do you want to save ?", "Exit",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    saveAll();
                    System.exit(0);
                }
                else{
                    System.exit(0);
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
                            loginController.reset();
                            frame.setVisible(false);
                            loginController.view.setVisible(true);
                        }
                    });
                }

            });
            frame.getCloseBtn()  .addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(frame,
                        "Do you want to save ?", "Exit",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    saveAll();
                    System.exit(0);
                }
                else{
                    System.exit(0);
                }
            });

        }

}


public void loadAll(){
        this.itemRepository.load();
        this.taskRepository.load();
        this.productRepository.load();
        this.productLineRepository.load();

        for(ProductLine pl : productLineRepository.getList()){
            productLineManager.register(pl);
        }

        for(Task task :taskRepository.getListOfTasks()) {
            if(task.getStatus() == TaskStatus.InProgress ){
            productLineManager.getService(
                    productLineRepository.
                            getProductLineByNumber(task.getProductLine())).
                                                runExisting(task);
            }
        }

    for(Task task :taskRepository.getListOfTasks()) {
        if(task.getStatus()==TaskStatus.Pending){
            productLineManager.getService(
                            productLineRepository.
                                    getProductLineByNumber(task.getProductLine())).
                    runExisting(task);
        }
    }


}

private void notifyIfBelowMinimum(String message){
        this.frame.showError(message + " is below minimum ,You should get some");
}

public synchronized void saveAll(){
        itemRepository.save();
        taskRepository.save();
        productRepository.save();
        productLineRepository.save();

}

}


