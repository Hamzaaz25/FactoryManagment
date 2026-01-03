package Model;

import IO.DataReader;
import IO.DataWriter;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskRepository {
    private ArrayList<Task> listOfTasks = new ArrayList<>();
    private HashMap<Integer , Task> tasksNumbers = new HashMap<>();

    public TaskRepository(){

    }

    public void load(){
        this.listOfTasks = DataReader.readTasks("./Files/Tasks.csv");
        for(Task task :this.listOfTasks) {
            tasksNumbers.put(task.getTaskNumber() , task);
        }
    }

    public void save(){
        DataWriter.writeTasks("./Files/Tasks.csv" , this.listOfTasks);
    }

    public Task getTaskByNumber(int number){
        return tasksNumbers.get(number);
    }

    public void insert(Task t){
        this.listOfTasks.add(t);
        this.tasksNumbers.put(t.getTaskNumber() , t);
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }
}
