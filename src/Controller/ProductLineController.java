package Controller;

import Enums.Status;
import Enums.TaskStatus;
import Enums.TaskValidation;
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



    private void onSelect(ProductLine pl){
        ProductLineDisplayViewTasks displayViewTasks = new ProductLineDisplayViewTasks(pl.getTaskLine() , this::onTaskCancel);
        baseFrame.switchContent(displayViewTasks,"Tasks");
        displayViewTasks.getAddTaskButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTask addTask = new AddTask(getProductsOptions());
                baseFrame.switchContent(addTask , "Add Task");
                addTask.getSaveButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!addTask.getClientField().getText().isBlank() && !addTask.getQuantityField().getText().isBlank()){
                            if(validateQuantity(addTask.getQuantityField().getText())){
                                Task task = new Task(addTask.getProductBox().trim() ,Integer.parseInt(addTask.getQuantityField().getText().trim()), addTask.getClientField().getText() ,pl.getId() ,TaskStatus.Pending );
                                TaskValidation validation =productLineManager.getService(pl).addTask(task);
                                if(validation != TaskValidation.Valid ){
                                    baseFrame.showError("Sorry , " + validation);
                                }
                                displayViewTasks.setTasks(pl.getTaskLine());
                                baseFrame.switchContent(displayViewTasks ,"Tasks");
                            }else baseFrame.showError("QUANTITY MUST NOT CONTAIN CHAR");
                        }else baseFrame.showError("FIELDS MUST NOT BE EMPTY");




                    }
                });

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

    private boolean validateQuantity(String quantity){
        try{
            Integer.parseInt(quantity);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
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
