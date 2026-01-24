package Controller;

import Enums.Status;
import Model.*;
import Repository.ProductLineRepository;
import View.*;
import View.Util.SimpleInputFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerController {

    private final ProductLineManager productLineManager;
    private final ManagerLineView view;
    private final ManagerBaseFrame baseFrame;
    private final ProductLineRepository productLineRepository;

    public ManagerController(ProductLineRepository productLineRepository,
                             ProductLineManager productLineManager,
                             ManagerBaseFrame baseFrame) {

        this.baseFrame = baseFrame;
        this.productLineRepository = productLineRepository;
        this.productLineManager = productLineManager;

        view = new ManagerLineView(productLineRepository.getList(), this::onEdit);
        baseFrame.switchContent(view, "Product Lines");
        baseFrame.setVisible(true);

        SimpleInputFrame addFrame = new SimpleInputFrame();

        // ✅ REGISTER ONCE
        addFrame.getAddButton().addActionListener(e -> {
            String name = addFrame.getTextField().getText().trim();

            if (name.isBlank()) {
                baseFrame.showError("ENTER A NAME");
                return;
            }

            addProductLine(name);
            update();

            addFrame.setVisible(false);
            addFrame.getTextField().setText("");
        });

        // ✅ OPEN FRAME ONLY
        view.getAdd().addActionListener(e -> {
            addFrame.setLocationRelativeTo(baseFrame);
            addFrame.setVisible(true);
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


