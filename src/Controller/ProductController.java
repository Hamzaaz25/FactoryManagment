package Controller;

import Enums.ItemStatus;
import Enums.MaterialType;
import Model.InventoryService;
import Model.Item;
import Model.Product;
import Repository.ProductRepository;
import Model.User;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductController {
    ProductsView view;
    private final ProductRepository productRepository;
    BaseFrame baseFrame;
    private final InventoryService inventoryService;



    public ProductController(ProductRepository productRepository, InventoryService inventoryService , BaseFrame bf ) {
        this.inventoryService = inventoryService;
        this.productRepository = productRepository;
        this.baseFrame = bf;
        view = new ProductsView(productRepository.getList() ,this::onProductSelect , this::onProductDelete , this::onProductEdit);
        this.baseFrame.setVisible(true);
        bf.switchContent(view , "Products");

        view.getSearchText().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (view.getSearchText().getText().equals(" Search ...")) {
                    view.getSearchText().setText("");
                    view.getSearchText().setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (view.getSearchText().getText().isEmpty()) {
                    view.getSearchText().putClientProperty("JTextField.placeholderText", "Search...");
                    view.getSearchText().setForeground(Color.GRAY);
                    applyFilters();
                }
            }
        });

        view.getSearchButton().addActionListener(e -> {
            applyFilters();
        });

        view.getSearchText().addActionListener(e -> {
            applyFilters();
        });

        view.getSearchText().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                applyFilters();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                applyFilters();
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                applyFilters();
            }
        });
        applyFilters();


    }


    private String getQuery() {
        String q = view.getSearchText().getText().trim().toLowerCase();
        return q.equals("search ...") ? "" : q;
    }

    private void applyFilters() {
        String query = getQuery();


        ArrayList<Product> filtered = productRepository.getList().stream()
                // search
                .filter(product ->
                        query.isEmpty() ||
                                product.getName().toLowerCase().contains(query)
                )


                .collect(Collectors.toCollection(ArrayList::new));


        view.renderProducts(filtered, this::onProductSelect, this::onProductDelete , this::onProductEdit);
    }






    public void onProductEdit(Product product){
        EditThingDetails editThingDetails = new EditThingDetails(product.getName() , String.valueOf(product.getPrice()), "Descreption" , new ImageIcon(product.getImage()) , product.getAmount());
        baseFrame.switchContent(editThingDetails, "Product Edit");
        editThingDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateEdit(editThingDetails.getPrice() , editThingDetails.getAmount())){
                inventoryService.editProduct(product.getName() , Integer.parseInt(editThingDetails.getAmount().trim()) , Double.parseDouble(editThingDetails.getPrice().trim()));
                applyFilters();
                baseFrame.switchContent(view , "Products");
                }
                else{
                    editThingDetails.showError("Price or amount cannot contain letters");
                }
            }
        });
    }


    public void onProductSelect(Product product) {

        ThingDetails details = new ThingDetails(
                product.getName(),
                String.valueOf(product.getPrice()),
                "Description...",
                new ImageIcon(product.getImage()),
                product.getAmount()
        );
        this.baseFrame.switchContent(details, "Details");
        details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.switchContent(view, "Products");
            }
        });

    }

    public void onProductDelete(Product product) {
        System.out.println(product.getName() + " Delete ");
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {

            productRepository.deleteByName(product.getName());
            view.renderProducts(productRepository.getList() , this::onProductSelect , this::onProductDelete , this::onProductEdit);

            JOptionPane.showMessageDialog(null, "Item deleted successfully!");
        }
    }


    private boolean validateEdit(String price , String amount ){
        try {
            Double.parseDouble(price);
            Integer.parseInt(amount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
