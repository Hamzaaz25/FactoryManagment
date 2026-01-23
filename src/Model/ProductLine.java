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
    ArrayList<Task> taskLine = new ArrayList<>();
    private ArrayList<Integer> listOfTaskNumbers = new ArrayList<>();
    private static int count =0;



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

    public ArrayList<Task> getTaskLine() {
        return taskLine;
    }

    public void addToTaskLine(Task t){
        taskLine.add(t);
    }

//    public void setTaskLine(ArrayList<Model.Task> taskLine) {
//        this.taskLine = taskLine;
//    }

    public int getProgress() {
        int sum = 0;
        int count = this.getTaskLine().size();

        if (count == 0) return 0;

        for (Task task : this.getTaskLine()) {
            sum += task.getProgressPercentage();
        }

        double avg =  sum / count;

        // guarantee 0–100
        return (int) Math.max(0, Math.min(100, avg));
    }

    public String getNotes(){
        return "Notes";
    }




    public ArrayList<Integer> getListOfTaskNumbers() {
        return listOfTaskNumbers;
    }
}
