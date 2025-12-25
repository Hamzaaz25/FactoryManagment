
import Enums.Status;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;


public class ProductLine  {
    private int Id;
    private String name;
    private Status status ;
    List<Task> taskLine = Collections.synchronizedList(new ArrayList<>());
    ArrayList<Integer> listOfTaskNumbers = new ArrayList<>();
    private static int count =0;
    BlockingDeque <Task> taskQueue = new LinkedBlockingDeque<>();
//    DataManager dm;



    static class Worker implements Runnable{
       Thread t;
       ProductLine pl;

       public Worker(ProductLine pl){
           this.pl = pl;
            t = new Thread(this);

       }
        @Override
        public void run() {

                while (!pl.taskQueue.isEmpty()) {
                    Task t;

                    try {
                        t = pl.taskQueue.take();
                        System.out.println(t.getTaskNumber());

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
//                    for(){
//                        if(pl.dm.isTaskValid(d.getTaskNumber())){
//                            System.out.println("Valid from list");
//                        }
//                        if(d.isValid()){
//                            System.out.println("Bool");
//                        }
//                    }

//
//                    if(t.isValid()) {
//                        System.out.println(t.getTaskNumber());
//                        System.out.println("Valid");
//                    }
//                        for (int i = 0; i < 5; i++) {
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//                            System.out.println(t.getTaskNumber() + " " + t.getClientName() + " for "+pl.getName());
//
//                    }

                }
            }





        public Thread getThread(){
           return this.t;
        }

    }

    public ProductLine( String name, Status status, ArrayList<Integer> list) {
        this.Id = ++count;
        this.name = name;
        this.status = status;
        this.listOfTaskNumbers = list;


    }

    public ProductLine( String name) {
        this.Id = ++count;
        this.name = name;
        this.status = Status.Idle;
    }

    public void addTask(Task t){
        this.taskQueue.push(t);
    }
    public void load(){
        for(Task t:this.taskLine){
            this.taskQueue.push(t);
        }
    }
    public void RunWorker(){
        Worker w = new Worker(this);
        w.getThread().start();
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Task> getTaskLine() {
        return taskLine;
    }

//    public void setTaskLine(ArrayList<Task> taskLine) {
//        this.taskLine = taskLine;
//    }



}
