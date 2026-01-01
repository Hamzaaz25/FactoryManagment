import Controller.LoginController;
import Model.DataManager;
import View.ItemFrame;
import View.LoginView;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
//import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        new LoginView();
//        FlatLightLaf.setup();
//        FlatDarkLaf.setup();

new LoginController();
//        new LoginView().setVisible(true);
//new LoginController();

//        new View.ItemFrame("Hamza");
//        new View.ProductFrame();

//        SwingUtilities.invokeLater(() -> {
//            new Loading().setVisible(true);
//        });
        DataManager.getInstance();

// new ProductFrame();

//        System.out.println(Model.DataManager.getInstance().isTaskValid(102));

//   Model.ProductLine pl = new Model.ProductLine("meow");
//Model.Task t = new Model.Task(107);

//pl.addTask(t);
//   Model.ProductLine.Worker worker =  new Model.ProductLine.Worker(pl);
//   worker.t.start();
//Task te = new Task("sofa" , 19 ,"s3eed" ,1 , TaskStatus.InProgress  );
//Model.Task se = new Model.Task("carpet" , 1 ,"s3eed" ,1 , TaskStatus.InProgress  );
////Model.Task he = new Model.Task("chair" , 1 ,"s3eed" ,1 , TaskStatus.InProgress  );
////
////
////Model.Task fe = new Model.Task("carpet" , 4 ,"Bilal" ,2 , TaskStatus.InProgress );
//////Model.DataManager.getInstance().editProductLineStatus(2 , Status.Maintenance);
//        ProductLine p = DataManager.getInstance().getProductLineByNumber(1);
////
//       p.addTask(te);
//       p.RunWorker();

//       new Controller.LoginController();
////        p.addTask(se);
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
////
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
////  Thread.sleep(1000);
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
//   for(String p :i.productsAmount.keySet()){
//       System.out.println();
//   }
//if(i.isTaskValid(101)) System.out.println("Thread run");

//        Model.ProductLine pl = new Model.ProductLine("vanity" );
//        Model.ProductLine pl1 = new Model.ProductLine("carpet" );
//        i.editProductLineStatus(1, Status.Maintenance);
//        i.displayProductLinePerformance(1);
//
//
//
//        i.addProductLine(pl);
//        i.addProductLine(pl1);


//
//        i.removeItem("pizza");
//        i.removeItem("wood");

//        ArrayList<Model.Task > t = Model.DataManager.getInstance().showTasksForProductLine(3);
//        t.forEach( task -> {
//            System.out.println(task.getTaskNumber());
//        });

    }
}