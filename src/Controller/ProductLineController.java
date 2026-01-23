package Controller;

import Enums.Status;
import Model.ProductLine;
import Model.ProductLineManager;
import Model.ProductLineService;
import Model.Task;
import Repository.ProductLineRepository;
import Repository.TaskRepository;
import Model.TaskService;
import View.BaseFrame;
import View.ProductLineView;

import java.util.List;

public class ProductLineController {
    private final ProductLineRepository productLineRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final ProductLineManager productLineManager;
    private final ProductLineView view;

    public ProductLineController(ProductLineRepository productLineRepository,
                                 TaskRepository taskRepository,
                                 TaskService taskService,
                                 ProductLineManager productLineManager ,
                                 BaseFrame baseFrame) {
        view = new ProductLineView(productLineRepository.getList() , this::onSelect);
        baseFrame.switchContent(view , "Product Lines");
        this.productLineRepository = productLineRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.productLineManager = productLineManager;
    }

    // Called from the View to add a new product line
    public void addProductLine(String name) {
        productLineManager.addProductLine(name);
    }

    // Called from the View to add a task to a line
    public void addTaskToLine(int id, Task task) {
        ProductLineService service = productLineManager.getService(productLineRepository.getProductLineByNumber(id));
        if (service != null) {
            service.addTask(task);
        }
    }

    // Called from the View to update a line's status
    public void editProductLineStatus(ProductLine pl , Status status) {
        ProductLineService service = productLineManager.getService(pl);
        if (service != null) {
            service.editProductLineStatus(pl.getId(),status);
        }
    }

    private void onSelect(ProductLine pl){

    }

}
