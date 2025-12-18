import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class ProductLine {
    private int Id;
    private String name;
    private String status ;
    private Queue<Task> lineTask = new ArrayDeque<>();

}
