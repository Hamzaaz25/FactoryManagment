import Enums.Status;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        DataManager.getInstance();
        System.out.println(DataManager.getInstance().isTaskValid(101));

//   ProductLine pl = new ProductLine("meow");
//Task t = new Task(107);
//t.setValid(true);
//pl.addTask(t);
//   ProductLine.Worker worker =  new ProductLine.Worker(pl);
//   worker.t.start();

//  for(ProductLine pl : i.getInstance().listOfProductLines){
//            pl.load();
//           pl.RunWorker();
//
//
//
//
//
////            pl.RunWorker();
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