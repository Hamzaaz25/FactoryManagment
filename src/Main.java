


import Controller.MainController;
import Model.Item;
import Model.ProductLine;
import Model.Task;
import Repository.ItemRepository;
import Repository.ProductLineRepository;
import Repository.TaskRepository;
import View.*;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;



public class Main {
    public static void print(ProductLine pl){
        System.out.println(pl.getName());
    }
    public static void main(String[] args)  {

        FlatLightLaf.setup();
        UIManager.put("Button.arc", 12);
        UIManager.put("Component.arc", 12);
        UIManager.put("ProgressBar.arc", 12);
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Button.pressedBackground", new Color(210, 210, 210));
        UIManager.put("Component.focusWidth", 1);
        UIManager.put("Component.innerFocusWidth", 0);

        MainController.getInstance();
//        new Loading().setVisible(true);
//        System.out.println("Hello");

//
//        ManagerBaseFrame baseFrame = new ManagerBaseFrame("","");
//        TaskRepository taskRepository = new TaskRepository();
//        taskRepository.load();
//        ProductLineRepository productLineRepository= new ProductLineRepository(taskRepository);
//        productLineRepository.load();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello");
//            }
//        };
//        System.out.println(productLineRepository.getList().isEmpty());
//        baseFrame.switchContent(new ManagerLineView(productLineRepository.getList() ,Main::print) , "");



//        String[]a = new String[0] ;ama
//new PickItemsFrame(new AddProduct() ,a ).setVisible(true);
//        BaseFrame baseFrame = new BaseFrame("","");
//        baseFrame.switchContent(new ManagerLineView() , "");/
//        TaskRepository taskRepository = new TaskRepository();
//        taskRepository.load();
//        ProductLineRepository productLineRepository =  new ProductLineRepository(taskRepository);
//        productLineRepository.load();

//        baseFrame.switchContent(new ProductLineView(productLineRepository.getList() , Main::print) , "Manager");
//        TaskRepository taskRepository = new TaskRepository();
//        taskRepository.load();
//        baseFrame.switchContent(new ProductLineDisplayViewTasks(taskRepository.getListOfTasks() , Main::print) , "");


//        PickItemsFrame pickItemsFrame = new PickItemsFrame(new AddProduct() , a);
//        pickItemsFrame.getDone().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(pickItemsFrame.getItemsBox().getSelectedItem());
//                System.out.println(pickItemsFrame.getSlider().getValue());
//            }
//        });
//        AddItem add = new AddItem();
//        add.getSave().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(add.getName() + " "ama + add.getCategory().toString());
//            }
//        });
//        frame.switchContent( new AddProduct(), "");



//        System.out.println("test");
//        System.out.println("0.3".matches("^[0-9]+.[0-9]") && "54".matches("^[0-9] + [0-9]"));

//        ItemRepository itemRepository = new ItemRepository();
//        itemRepository.load();

//        frame.switchContent(new ItemsView(itemRepository.getList()) , "Items");
//        ImageFileChooser im = new ImageFileChooser(frame);
//        System.out.println(im.getPath());
//        frame.switchContent(new SupervisorView("amin" ) , "supervisor");
//        ItemRepository itemRepository= new ItemRepository();
//        itemRepository.load();
//        frame.switchContent(new ItemsView( "Hamza" , itemRepository.getList()) , "Items");
//        ArrayList <Item> filtered = itemRepository.getList().stream().filter(item -> item.getType() == MaterialType.Fabric).collect(Collectors.toCollection(ArrayList :: new));
//        frame.switchContent(new ItemsView( "Hamza" , filtered) , "Items");
//        TaskRepository taskRepository = new TaskRepository();
//        taskRepository.load();
//        ArrayList<Task> tasks = taskRepository.getListOfTasks().stream()
//                .filter(task -> task.getClientName()
//                .equals("s3eed"))
//                .collect(Collectors.toCollection(ArrayList::new));
//        frame.switchContent(new ProductLineDisplayView(tasks),"Hello");
//
//        System.out.println("Loaded");

//        frame.switchContent(new ProductLineDisplayView(taskRepository.getListOfTasks()),"Tasks");
  //        FlatDarkLaf.setup();
//        ItemRepository itemRepository = new ItemRepository();
//        itemRepository.load();
//        BaseFrame frame = new BaseFrame("Hamza" , "Base");
//       frame.switchContent( new ItemsView("Hamza" , itemRepository.getList()) , "Items");
//       frame.setVisible(true);

//        ProductRepository productRepository = new ProductRepository();
//        productRepository.load();
//        TaskRepository taskRepository = new TaskRepository();
//        taskRepository.load();
//        ProductLineRepository productLineRepository = new ProductLineRepository(taskRepository);
//        productLineRepository.load("./Files/ProductLines.csv");
//        TaskService taskService = new TaskService(itemRepository ,productRepository ,taskRepository ,productLineRepository);
//        ProductLine pl = productLineRepository.getProductLineByNumber(1);
//        ProductLine pl2 = productLineRepository.getProductLineByNumber(1);
//        ProductLineService productLineService= new ProductLineService(productLineRepository,pl , taskService );
//        ProductLineService productLineService= new ProductLineService(productLineRepository,pl2 , taskService );
//
//        Task te = new Task("carpet" , 5 ,"saeed" ,1 , TaskStatus.InProgress  );
//        Task se = new Task("chair" , 5 ,"saeed" ,1 , TaskStatus.InProgress  );
//        productLineService.addTask(te);
//        productLineService.addTask(se);
//

//        BaseFrame frame = new BaseFrame("hamza" ,"Tasks");
//        User user = new User("Hamza" , "Js" , Role.Manager);
//        frame.showAPanel(new ProfileView(user));
//        new LoginController();
//        new TaskController(taskRepository,frame);


//       try {
//            FlatLightLaf.setup();
//        } catch (Exception ex) {
//              System.err.println("Failed to initialize LaF");
//     }
//        new BaseFrame("gg","gg");
//        TaskRepository taskRepository = new TaskRepository();
//        taskRepository.load();

//        BaseFrame bf = new BaseFrame("Hamza", "Tasks");
//        bf.showAPanel(new TaskView(taskRepository.getListOfTasks()));

//         new LoginController();
        // new LoginView();
        //new ProductsView("aaa");
        // new ItemsView("Hamza");
        // new LoginController();
        // new LoginView();
        // new SupervisorView("amin");
        // SwingUtilities.invokeLater(() -> {
        // new Loading().setVisible(true);
        //});
//        DataManager.getInstance();



//        System.out.println(Model.DataManager.getInstance().isTaskValid(102));

//   Model.ProductLine pl = new Model.ProductLine("meow");
//Model.Task t = new Model.Task(107);

//pl.addTask(t);
//   Model.ProductLine.Worker worker =  new Model.ProductLine.Worker(pl);
//   worker.t.start();

//Model.Task se = new Model.Task("carpet" , 1 ,"saeed" ,1 , TaskStatus.InProgress  );
//Model.Task he = new Model.Task("chair" , 1 ,"saeed" ,1 , TaskStatus.InProgress  );
//
//
//Model.Task fe = new Model.Task("carpet" , 4 ,"Bilal" ,2 , TaskStatus.InProgress );
//Model.DataManager.getInstance().editProductLineStatus(2 , Status.Maintenance);
//        ProductLine p = DataManager.getInstance().getProductLineByNumber(1);
//
//       p.addTask(te);
//       p.RunWorker();

//       new Controller.LoginController();
//        p.addTask(se);
//        p.addTask(fe);
//
//for(Model.Task t : p.taskLine)
//    System.out.print(t.getTaskNumber()+" ");
//p.RunWorker();
//Thread.sleep(2000);
//        Model.DataManager.getInstance().cancelTask(te);
//
//Thread.sleep(10000);
//        p.addTask(he);
//
//

//        Thread b = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Model.DataManager.getInstance().runTask(fe);
//
//
//            }
//        });
//t.start();
//b.start();
//Thread.sleep(3000);
//        Model.DataManager.getInstance().cancelTask(te);
//        System.out.println(te.isWorking());


//

//  for(Model.ProductLine pl : Model.DataManager.getInstance().listOfProductLines){
//            pl.load();
//           pl.RunWorker();
//           pl.addTask(te);
//  }
//  Model.Task t = Model.DataManager.getInstance().getTaskByNumber(102);
//  Thread.sleep(1000);
//  t.setRequestedQuantity(10);

//
//
//
//
//
//   pl.RunWorker();
//        }

//   Model.ProductLine pl = new Model.ProductLine("mango");
//   i.addProductLine(pl);z





//  for(Model.ProductLine pl :i.listOfProductLines){
//      pl
//  }


    }
}