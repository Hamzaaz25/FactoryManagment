package Model;

import Enums.Status;
import Enums.TaskStatus;
import Enums.TaskValidation;
import Repository.ProductLineRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductLineService {
    private final ProductLineRepository productLineRepository;
    private final TaskService taskService;
    private final ExecutorService executor;
    ProductLine productLine;


    public ProductLineService(ProductLineRepository pr,ProductLine pl ,TaskService taskService ) {
        this.productLineRepository =pr;
        this.productLine = pl;
        this.taskService = taskService;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public TaskValidation addTask(Task t) {
        TaskValidation validation = taskService.validateAndReserve(t);
        System.out.println(validation);
        if (validation == TaskValidation.Valid) {
                t.setStatus(TaskStatus.Pending);
                productLine.taskLine.add(t);
                executor.submit(() -> taskService.runTask(t));
            }
        return validation;
        }


    public void addProductLine(String name ){
       ProductLine pl = new ProductLine(name);
       this.productLineRepository.insert(pl);

    }

    public void editProductLineStatus(Status s){
       productLine.setStatus(s);

        }

    }
