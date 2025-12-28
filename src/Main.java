import Enums.TaskStatus;
import View.*;
import View.ProductFrame;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        new View.ItemFrame();
////        new View.ProductFrame();
//
//        SwingUtilities.invokeLater(() -> {
//            new Loading().setVisible(true);
//        });
        DataManager.getInstance();

// new ProductFrame();

//        System.out.println(DataManager.getInstance().isTaskValid(102));

//   ProductLine pl = new ProductLine("meow");
//Task t = new Task(107);

//pl.addTask(t);
//   ProductLine.Worker worker =  new ProductLine.Worker(pl);
//   worker.t.start();
Task te = new Task(104 ,"sofa" , 10 ,"s3eed" ,1 , TaskStatus.InProgress ,LocalDate.now() );
//Task fe = new Task(104 ,"carpet" , 4 ,"Bilal" ,2 , TaskStatus.InProgress ,LocalDate.now() );
//
DataManager.getInstance().addTask(1,te);
//DataManager.getInstance().addTask(2,fe);
//
Thread t = new Thread(new Runnable() {
    @Override
    public void run() {
        DataManager.getInstance().runTask(te);


    }
});
//        Thread b = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                DataManager.getInstance().runTask(fe);
//
//
//            }
//        });
t.start();
//b.start();
//Thread.sleep(3000);
//        DataManager.getInstance().cancelTask(te);
//        System.out.println(te.isWorking());


//

//  for(ProductLine pl : DataManager.getInstance().listOfProductLines){
//            pl.load();
//           pl.RunWorker();
//           pl.addTask(te);
//  }
//  Task t = DataManager.getInstance().getTaskByNumber(102);
////  Thread.sleep(1000);
//  t.setRequestedQuantity(10);

//
//
//
//
//
//   pl.RunWorker();
//        }

//   ProductLine pl = new ProductLine("mango");
//   i.addProductLine(pl);z





//  for(ProductLine pl :i.listOfProductLines){
//      pl
//  }
//   for(String p :i.productsAmount.keySet()){
//       System.out.println();
//   }
//if(i.isTaskValid(101)) System.out.println("Thread run");

//        ProductLine pl = new ProductLine("vanity" );
//        ProductLine pl1 = new ProductLine("carpet" );
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

    }
}