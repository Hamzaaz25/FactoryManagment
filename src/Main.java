import Enums.Status;
import Enums.TaskStatus;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        DataManager.getInstance();
//        System.out.println(DataManager.getInstance().isTaskValid(102));

//   ProductLine pl = new ProductLine("meow");
//Task t = new Task(107);

//pl.addTask(t);
//   ProductLine.Worker worker =  new ProductLine.Worker(pl);
//   worker.t.start();
Task te = new Task(104 ,"sofa" , 2 ,"s3eed" ,1 , TaskStatus.InProgress ,LocalDate.now() );
DataManager.getInstance().addTask(1,te);
Thread t = new Thread(new Runnable() {
    @Override
    public void run() {
        DataManager.getInstance().runTask(te);


    }
});
t.start();
Thread.sleep(100);
        DataManager.getInstance().cancelTask(te);
        System.out.println(te.isWorking());


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