package Model;

import Enums.Status;
import Enums.TaskValidation;
import IO.DataWriter;

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

    public void addTask(Task t) {
        TaskValidation validation = taskService.validateAndReserve(t);
        System.out.println(validation);
        if (validation == TaskValidation.Valid) {
                productLine.taskLine.add(t);
                executor.submit(() -> taskService.runTask(t));

            }
        }


    public void addProductLine(String name ){
       ProductLine pl = new ProductLine(name);
       this.productLineRepository.insert(pl);

    }

    public void editProductLineStatus(int id , Status s){
       productLineRepository.getProductLineByNumber(id).setStatus(s);
       productLineRepository.save();
        }

    }
