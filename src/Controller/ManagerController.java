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
             addframe.getTextField().setText("");
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

    private void onEdit(ProductLine pl) {

        StatusChooserFrame frame = new StatusChooserFrame();
        frame.setSelectedStatus(pl.getStatus());

        frame.setOnConfirm(status -> {
            if (status == null) {
                baseFrame.showError("Please select a status");
                return;
            }
            editProductLineStatus(pl, status);
            update();
        });

        frame.setVisible(true);
    }



    public void update(){
        view.updateAllCards(productLineRepository.getList() , this::onEdit);
}




}
