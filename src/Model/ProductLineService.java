package Model;

import Enums.TaskValidation;
import IO.DataWriter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductLineService {
    private final TaskService taskService;
    private final ExecutorService executor;
    ProductLine productLine;

    public ProductLineService(ProductLine pl, TaskService taskService) {
        this.productLine = pl;
        this.taskService = taskService;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void addTask(Task t) {

        TaskValidation validation = taskService.registerTask(t);
        if (validation == TaskValidation.Valid) {
                executor.submit(() -> taskService.runTask(t));
                productLine.taskLine.add(t);

            }
        }
    }
