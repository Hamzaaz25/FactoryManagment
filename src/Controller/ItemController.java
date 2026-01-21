package Controller;

import Enums.ItemStatus;
import Enums.MaterialType;
import Model.InventoryService;
import Model.Item;
import Repository.ItemRepository;
import View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemController {
    private final ItemRepository itemRepository;
    private final BaseFrame baseFrame;
    private final ItemsView view;
    private final InventoryService inventoryService;

    public ItemController(ItemRepository itemRepository,InventoryService inventoryService, BaseFrame bf) {
        this.inventoryService = inventoryService;
        this.itemRepository = itemRepository;
        this.baseFrame = bf;
        view = new ItemsView(itemRepository.getList(), this::onItemSelect, this::onItemDelete , this::onItemEdit);
        this.baseFrame.setVisible(true);
        bf.switchContent(view, "Items");

        view.getCategory().addActionListener(_ -> applyFilters());
        view.getAvailable().addActionListener(_ -> applyFilters());


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


        view.getSearchBtn().addActionListener(_ -> applyFilters());

        view.getSearchText().addActionListener(_ -> applyFilters());

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
        addItemListener();

    }

    private String getQuery() {
        String q = view.getSearchText().getText().trim().toLowerCase();
        return q.equals("search ...") ? "" : q;
    }

    private void applyFilters() {
        String query = getQuery();

        String selectedCategory =
                Objects.requireNonNull(view.getCategory().getSelectedItem()).toString();

        String selectedStatus =
                Objects.requireNonNull(view.getAvailable().getSelectedItem()).toString();

        ArrayList<Item> filtered = itemRepository.getList().stream()
                // search
                .filter(item ->
                        query.isEmpty() ||
                                item.getName().toLowerCase().contains(query)
                )

                // category
                .filter(item ->
                        selectedCategory.equals("All") ||
                                item.getType() == MaterialType.valueOf(selectedCategory)
                )

                //availability
                .filter(item ->
                        selectedStatus.equals("All") ||
                                item.getStatus() == ItemStatus.valueOf(selectedStatus)
                )
                        .collect(Collectors.toCollection(ArrayList::new));


        view.renderItems(filtered, this::onItemSelect, this::onItemDelete , this::onItemEdit);
    }


    public void onItemEdit(Item item){
        System.out.println(item.getName() + "Edit");
        EditThingDetails editThingDetails = new EditThingDetails(item.getName() , String.valueOf(item.getPrice()),""  , new ImageIcon(item.getImage()),item.getAvailableQuantity() );
        baseFrame.switchContent( editThingDetails, "Item Edit");
        editThingDetails.addActionListener(_ -> {
            if(validateEdit(editThingDetails.getPrice(), editThingDetails.getAmount())){
            inventoryService.editItem(item.getName() , Integer.parseInt(editThingDetails.getAmount().trim()) , Double.parseDouble(editThingDetails.getPrice() .trim()) , 20);
            applyFilters();
            baseFrame.switchContent(view , "Items");}
            else {
                editThingDetails.showError("Price or amount cannot contain letters");
            }
        });

    }



    public void onItemSelect(Item item) {

        ThingDetails details = new ThingDetails(
                item.getName(),
                String.valueOf(item.getPrice()),
                "Description...",
                new ImageIcon(item.getImage()),
                item.getAvailableQuantity()
        );
        this.baseFrame.switchContent(details, "Details");
        details.addActionListener(_ -> baseFrame.switchContent(view, "Items"));

    }

    public void onItemDelete(Item item) {
        System.out.println(item.getName() + " Delete ");
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            inventoryService.removeItem(item.getName());
            view.renderItems(itemRepository.getList() , this::onItemSelect , this::onItemDelete , this::onItemEdit);

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

    private void addItemListener(){
        final String[] imagePath = new String[1];
        view.getAddCard().addActionListener(_ -> {
            AddItem addItemPanel = new AddItem();
            addItemPanel.getCancel().addActionListener(_ -> baseFrame.switchContent(view , "Items"));
            baseFrame.switchContent(addItemPanel , "Item");
            //Save ActionListener
            addItemPanel.getSave().addActionListener(_ -> {
                if(!addItemPanel.isAnyFieldBlank()){
                    if(validateEdit(addItemPanel.getPrice(), addItemPanel.getAmount()) && validateEdit(addItemPanel.getPrice(), addItemPanel.getMinimum())){
                        if(imagePath[0] != null){
                          inventoryService.addItem(addItemPanel.getName().trim() , addItemPanel.getCategory() , Integer.parseInt(addItemPanel.getAmount()) , Double.parseDouble(addItemPanel.getPrice()) , imagePath[0]);
                          applyFilters();
                          baseFrame.switchContent(view , "Items");
                        }
                        else{
                            baseFrame.showError("You should add an image");
                        }
                    }else{
                        baseFrame.showError("You cannot enter chars in number fields");
                    }

                }else {
                    baseFrame.showError("No field should be blank");
                }
            });
            addItemPanel.getImageBtn().addActionListener(_ -> {
                ImageFileChooser imageFileChooser= new ImageFileChooser(baseFrame);
                imagePath[0] =imageFileChooser.getPath();

            });


        });
    }

}