package Controller;

import Model.TaskRepository;
import View.BaseFrame;
import View.TaskView;

public class TaskController {
    private final TaskRepository model;

    private BaseFrame view ;


    public TaskController(TaskRepository model , BaseFrame view) {
        this.model = model;
        this.view = view;
        view.showAPanel(new TaskView(model.getListOfTasks()));


    }
}

