package Controller;

import Enums.Status;
import Enums.TaskStatus;
import Enums.TaskValidation;
import Model.*;
import Repository.ProductLineRepository;
import Repository.ProductRepository;
import Repository.TaskRepository;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerController {

    private final ProductLineManager productLineManager;
    private final ManagerLineView view;
    private final ManagerBaseFrame baseFrame;
    private final ProductLineRepository productLineRepository;

    public ManagerController(ProductLineRepository productLineRepository ,
                                 ProductLineManager productLineManager ,
                                 ManagerBaseFrame baseFrame) {
        this.baseFrame = baseFrame;
        view = new ManagerLineView(productLineRepository.getList() , this::onEdit);
        baseFrame.switchContent(view , "Product Lines");
        baseFrame.setVisible(true);
        this.productLineRepository = productLineRepository;
        this.productLineManager = productLineManager;
        SimpleInputFrame addframe = new SimpleInputFrame();
        view.getAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");

             addframe.setVisible(true);
             addframe.getAddButton().addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     if(!addframe.getTextField().getText().isBlank()) {
                        addProductLine(addframe.getTextField().getText());
                        update();
                        addframe.setVisible(false);
                        addframe.dispose();
                     }else baseFrame.showError("ENTER A NAME");
                 }
             });

            }
        });
    }

    // Called from the View to add a new product line
    public void addProductLine(String name) {
        productLineManager.addProductLine(name);
    }



    // Called from the View to update a line's status
    public void editProductLineStatus(ProductLine pl , Status status) {
        ProductLineService service = productLineManager.getService(pl);
        if (service != null) {
            service.editProductLineStatus(status);
        }
    }

    private void onEdit(ProductLine pl){
     StatusChooserFrame statusChooserFrame= new StatusChooserFrame();
     statusChooserFrame.setVisible(true);
//     statusChooserFrame.setSelectedStatus(pl.getStatus());


        statusChooserFrame.getOkButton().addActionListener(e -> {
            Status selectedStatus =null;
            if(statusChooserFrame.getActiveBtn().isSelected()) selectedStatus = Status.Active;
            else if(statusChooserFrame.getIdleBtn().isSelected()) selectedStatus = Status.Idle;
            else if(statusChooserFrame.getMaintenanceBtn().isSelected()) selectedStatus = Status.Maintenance;
            statusChooserFrame.setVisible(false); // close frame
            if(selectedStatus != null){
                editProductLineStatus(pl , selectedStatus);
                update();

            }
            else baseFrame.showError("");
        });







    }


public void update(){
        view.updateAllCards(productLineRepository.getList() , this::onEdit);
}


    private double getLineProgress(ProductLine pl) {
        int sum = 0;
        int count = pl.getTaskLine().size();

        if (count == 0) return 0;

        for (Task task : pl.getTaskLine()) {
            sum += task.getProgressPercentage();
        }

        double avg = (double) sum / count;

        // guarantee 0–100
        return Math.max(0, Math.min(100, avg));
    }


}
