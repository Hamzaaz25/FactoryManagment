package Model;

import Enums.Status;
import Enums.TaskValidation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import IO.*;


public class ProductLine  {
    private int Id;
    private String name;
    private Status status ;
    List<Task> taskLine = Collections.synchronizedList(new ArrayList<>());
    ArrayList<Integer> listOfTaskNumbers = new ArrayList<>();
    private static int count =0;
    BlockingDeque <Task> taskQueue = new LinkedBlockingDeque<>();
    public Thread consumer = new Thread(new Runnable() {
    @Override
    public void run() {

            while(true){
                Task task = null;
                try {
                    task = taskQueue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                DataManager.getInstance().runTask(task);

        }
}

    }
);

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
        TaskValidation validation = DataManager.getInstance().registerTask(t);
        if(validation == TaskValidation.Valid){
            try {
                this.taskQueue.put(t);
                this.taskLine.add(t);
            } catch (InterruptedException e) {
                DataWriter.writeErrors(e.getMessage());
                throw new RuntimeException(e);

            }
        }
       else{
            System.out.println("Sorry");
        }

    }
    public void load(){
        for(Task t:this.taskLine){
            this.taskQueue.push(t);
        }
    }
    public void RunWorker(){
      consumer.start();
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

//    public void setTaskLine(ArrayList<Model.Task> taskLine) {
//        this.taskLine = taskLine;
//    }



}
