package Controller;

import Enums.Status;
import Enums.TaskStatus;
import Model.*;
import Repository.ProductLineRepository;
import Repository.ProductRepository;
import Repository.TaskRepository;
import View.AddTask;
import View.BaseFrame;
import View.ProductLineDisplayViewTasks;
import View.ProductLineView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductLineController {
    private final ProductLineRepository productLineRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final ProductLineManager productLineManager;
    private final ProductLineView view;
    private final ProductRepository productRepository;
    private final BaseFrame baseFrame;

    public ProductLineController(ProductLineRepository productLineRepository,
                                 TaskRepository taskRepository,
                                 TaskService taskService,
                                 ProductLineManager productLineManager ,
                                 ProductRepository productRepository,
                                 BaseFrame baseFrame) {
        this.productRepository=productRepository;
        this.baseFrame = baseFrame;
        view = new ProductLineView(productLineRepository.getList() );
        view.renderProductLines(productLineRepository.getList(),this::onSelect);
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
        ProductLineDisplayViewTasks displayViewTasks = new ProductLineDisplayViewTasks(pl.getTaskLine() , this::onTaskCancel);
        baseFrame.switchContent(displayViewTasks,"Tasks");
        displayViewTasks.getAddTaskButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTask addTask = new AddTask(getProductsOptions());
                baseFrame.switchContent(addTask , "Add Tasks");

            }
        });


    }

    public String[] getProductsOptions() {
        ArrayList<Product> products = productRepository.getList();


        String[] options = new String[products.size() ];



        int i = 0;
        for (Product product : products) {
            options[i++] = String.valueOf(product.getName());
        }

        return options;
    }

    public void onTaskCancel(Task task){
        if(task.getStatus() == TaskStatus.Cancelled){
            baseFrame.showError("The task is already cancelled");
        }else if(task.getStatus() == TaskStatus.Completed){
            baseFrame.showError("The task is already completed");

        }else{
            taskService.cancelTask(task);
        }
    }

}
