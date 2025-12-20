
import java.util.ArrayList;


public class ProductLine {
    private int Id;
    private String name;
    private Status status ;
    private ArrayList<Task> taskLine = new ArrayList<>();

    public ProductLine(int id, String name, Status status, ArrayList<Task> taskLine) {
        Id = id;
        this.name = name;
        this.status = status;
        this.taskLine = taskLine;
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
