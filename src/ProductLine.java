
import Enums.Status;

import java.util.ArrayList;


public class ProductLine {
    private int Id;
    private String name;
    private Status status ;
    private ArrayList<Task> taskLine = new ArrayList<>();
    ArrayList<Integer> listOfTaskNumbers = new ArrayList<>();
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

    public void setTaskLine(ArrayList<Task> taskLine) {
        this.taskLine = taskLine;
    }
}
