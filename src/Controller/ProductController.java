package Controller;

import Model.*;
import Repository.ItemRepository;
import Repository.ProductLineRepository;
import Repository.ProductRepository;
import Repository.TaskRepository;
import View.*;
import View.Util.DateRangePopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ProductController {
    ProductsView view;
    private final ProductRepository productRepository;
    BaseFrame baseFrame;
    private final InventoryService inventoryService;
    private final ItemRepository itemRepository;
    private HashMap<String, Integer> recipe;
    private PickItemsFrame pickItemsFrame;
    ProductService productService ;
    TaskRepository taskRepository;
    ProductLineRepository productLineRepository;




    public ProductController(ProductRepository productRepository, InventoryService inventoryService , BaseFrame bf , ItemRepository itemRepository , TaskRepository taskRepository , ProductLineRepository lineRepository) {
        productService = new ProductService(productRepository ,taskRepository);
        this.taskRepository=taskRepository;
        this.itemRepository=itemRepository;
        this.inventoryService = inventoryService;
        this.productRepository = productRepository;
        this.productLineRepository =lineRepository;
        this.baseFrame = bf;
        view = new ProductsView(productRepository.getList(),getProductLineOptions(),this::onProductSelect , this::onProductDelete , this::onProductEdit);
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

        view.getSearchButton().addActionListener(_ -> {
            applyFilters();
        });

        view.getSearchText().addActionListener(_ -> {
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
        addProductListener();
        showMostTaskedProduct();
        filterProductsByProductLine();


    }


    private String getQuery() {
        String q = view.getSearchText().getText().trim().toLowerCase();
        return q.equals("search ...") ? "" : q;
    }

    private void filterProductsByProductLine(){
        view.getProductLines().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters();
            }
        });
    }

    private void showMostTaskedProduct(){
        view.getFilters().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getFilters().getSelectedIndex()==0){
                    view.getSearchText().setText("");
                    applyFilters();
                }

                if(view.getFilters().getSelectedIndex() == 1){
                    DateRangePopup dateRangePopup = new DateRangePopup();
                    dateRangePopup.setVisible(true);
        dateRangePopup.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate start = dateRangePopup.getStartDate();
                LocalDate end = dateRangePopup.getEndDate();
                if (end.isBefore(start)) {
                    baseFrame.showError( "End date cannot be before start date!");
                    return;
                }
                Product mostTasked = productService.getMostTaskedProduct(start, end);
                if (mostTasked != null) {
                    System.out.println("Most tasked product from " + start + " to " + end + " is: " +
                            mostTasked.getName());
                        view.getSearchText().setText(mostTasked.getName());
                        applyFilters();
                        dateRangePopup.setVisible(false);
                        dateRangePopup.dispose();



                } else {
                    baseFrame.showError("No tasks found in this period.");
                }

            }
        });
                }
            }
        });
    }

    private void applyFilters() {
        String query = getQuery().toLowerCase();
        int selectedProductLine = view.getProductLines().getSelectedIndex();

        ArrayList<Product> filtered;

        if (selectedProductLine == 0) { // "All" selected
            filtered = productRepository.getList().stream()
                    .filter(product -> query.isEmpty() || product.getName().toLowerCase().contains(query))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            // Filter by product line AND search query
            HashSet<Product> productsByLine = new HashSet<>();
            for (Task t : this.taskRepository.getListOfTasks()) {
                if (t.getProductLine() == selectedProductLine) {
                    Product p = productRepository.getByName(t.getRequestedProduct());
                    if (p != null) {
                        productsByLine.add(p);
                    }
                }
            }

            filtered = productsByLine.stream()
                    .filter(product -> query.isEmpty() || product.getName().toLowerCase().contains(query))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        view.renderProducts(filtered, this::onProductSelect, this::onProductDelete, this::onProductEdit);
    }




    public String[] getProductLineOptions() {
        ArrayList<ProductLine> productLines = productLineRepository.getList();


        String[] options = new String[productLines.size() + 1];

        options[0] = "All";

        int i = 1;
        for (ProductLine pl : productLines) {
            options[i++] = String.valueOf(pl.getId());
        }

        return options;
    }




    public void onProductEdit(Product product){
        EditThingDetails editThingDetails = new EditThingDetails(product.getName() , String.valueOf(product.getPrice()), "Description" , new ImageIcon(product.getImage()) , product.getAmount());
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
                product.getRecipe().toString(),
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
            inventoryService.removeProduct(product.getName());
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

    private void addProductListener(){
        final String[] imagePath = new String[1];

        view.getAddCard().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recipe = new HashMap<>();
                AddProduct addProductPanel = new AddProduct();
                pickItemListener(addProductPanel);
                pickItemListener(addProductPanel);
                baseFrame.switchContent(addProductPanel,"Products");
                addProductPanel.getCancel().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        baseFrame.switchContent(view, "Products");
                    }
                });
                addProductPanel.getImageBtn().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ImageFileChooser imageFileChooser= new ImageFileChooser(baseFrame);
                        JButton btn = addProductPanel.getImageBtn();
                        int w = btn.getWidth() > 0 ? btn.getWidth() : btn.getPreferredSize().width;
                        int h = btn.getHeight() > 0 ? btn.getHeight() : btn.getPreferredSize().height;
                        imagePath[0] =imageFileChooser.getPath();
                        ImageIcon imageIcon = new ImageIcon(imagePath[0]);
                        Image scaledImg = imageIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                        btn.setIcon(new ImageIcon(scaledImg) );
                        btn.setText("");
                        if(imagePath[0] == null)
                            btn.setText("+");
                    }
                });

                addProductPanel.getSave().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!addProductPanel.isAnyFieldBlank()){
                            if(validateEdit(addProductPanel.getPrice(), "0")){
                               if(Double.parseDouble(addProductPanel.getPrice()) > 0){
                                if(imagePath[0] != null) {
                                    if(!recipe.isEmpty()){
                                        inventoryService.addProduct(addProductPanel.getName() , 0 ,Double.parseDouble(addProductPanel.getPrice()) , recipe , imagePath[0]);
                                        applyFilters();
                                        baseFrame.switchContent(view , "Products");
                                    }else baseFrame.showError("You should add a recipe of items");
                                 }else baseFrame.showError("Add an image");
                               }else baseFrame.showError("Price cannot be negative");
                            }else baseFrame.showError("Price cannot contain a char");
                        }else baseFrame.showError("NO FIELD SHOULD BE EMPTY");
                    }
                });








            }






        });



    }

    private String[] itemsToStringArrayOfNames(){
        String[] a = new String[itemRepository.getList().size()];
        int i =0;
        for(Item item : itemRepository.getList()){
            a[i] = item.getName();
            i++;
        }
        return a;
    }




    private void pickItemListener(AddProduct addProduct) {

        if (pickItemsFrame == null) {
            pickItemsFrame = new PickItemsFrame(addProduct, itemsToStringArrayOfNames());

            pickItemsFrame.getDone().addActionListener(_ -> {
                if (pickItemsFrame.getSlider().getValue() > 0) {
                    recipe.merge(
                            pickItemsFrame.getItemsBox().getSelectedItem().toString(),
                            pickItemsFrame.getSlider().getValue() ,
                            Integer::sum
                    );
                }
                    addProduct.updateItemsField(recipe);
                    pickItemsFrame.setVisible(false);
                    pickItemsFrame.showDialog(recipe);

            });

            pickItemsFrame.getAddAnother().addActionListener(_ -> {
                if (pickItemsFrame.getSlider().getValue() > 0) {
                    recipe.merge(
                            pickItemsFrame.getItemsBox().getSelectedItem().toString(),
                            pickItemsFrame.getSlider().getValue() ,
                            Integer::sum
                    );
                }
                pickItemsFrame.getItemsBox().setSelectedIndex(0);
                pickItemsFrame.getSlider().setValue(0);
            });
        }

        addProduct.getItemsField().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pickItemsFrame.setVisible(true);
            }
        });
    }



}
