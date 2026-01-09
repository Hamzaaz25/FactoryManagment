package View;

import Enums.ItemStatus;
import Enums.MaterialType;
import Enums.TaskStatus;
import Model.Item;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class ItemsView extends JPanel {

    public ArrayList<ItemBtn> allCards = new ArrayList<>();
    private JPanel container=new JPanel(new GridLayout(0, 3, 100, 100));
    private JTextField Searchtext = new JTextField(" Search ...");;
    JLabel NoResults = new JLabel("No items match your search", SwingConstants.CENTER);
    String name;
    ArrayList<Item> currentItems ;
    ArrayList<ItemBtn> activeCards = new ArrayList<>();
    JComboBox<String> category;
    JComboBox<String> available;


    public ItemsView(String name , ArrayList<Item> list) {
       this.currentItems = new ArrayList<>();
       this.name = name;
       this.setOpaque(false);
       this.setLayout(new BorderLayout());


       JPanel topBar = new JPanel(new BorderLayout());
       topBar.setOpaque(false);


       category = new JComboBox<>(new String[]{"All", MaterialType.Wood.toString(), MaterialType.Fabric.toString(), MaterialType.Metal.toString()});
       JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
       categoryPanel.setOpaque(false);
       categoryPanel.add(category);

        available = new JComboBox<>(new String[]{"All", ItemStatus.Available.toString(), ItemStatus.BelowMinimum.toString()});
        JPanel availablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        availablePanel.setOpaque(false);
        availablePanel.add(available);



        Searchtext.setPreferredSize(new Dimension(250, 35));
        Searchtext.setForeground(Color.GRAY);
        Searchtext.setFont(new Font("Segoe UI", Font.ITALIC, 13));

        Searchtext.putClientProperty("JComponent.arc", 15);
        Searchtext.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));


        Searchtext.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Searchtext.getText().equals(" Search ...")) {
                    Searchtext.setText("");
                    Searchtext.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Searchtext.getText().isEmpty()) {
                    Searchtext.setText(" Search ...");
                    Searchtext.setForeground(Color.GRAY);
                    performSearch("");
                }
            }
        });


        NoResults.setFont(new Font("Segoe UI", Font.BOLD, 18));
        NoResults.setForeground(Color.black);
        NoResults.setVisible(false);
        NoResults.setIcon(new ImageIcon(("./assets/notFound.png")));
        NoResults.setVerticalTextPosition(JLabel.BOTTOM);
        NoResults.setHorizontalTextPosition(JLabel.CENTER);


        JButton Searchbtn = new JButton();
        ImageIcon iconn = new ImageIcon("./assets/searchh.png");
        Image scaledImgg = iconn.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Searchbtn.setIcon(new ImageIcon(scaledImgg));
        Searchbtn.setBackground(Color.white);
        Searchbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Searchbtn.addActionListener(e -> {
            performSearch(Searchtext.getText());
        });

        Searchtext.addActionListener(e -> {
            performSearch(Searchtext.getText());
        });

        Searchtext.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                performSearch(Searchtext.getText());
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                performSearch(Searchtext.getText());
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                performSearch(Searchtext.getText());
            }
        });

        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        searchBar.setOpaque(false);
        searchBar.add(Searchtext);
        searchBar.add(Searchbtn);
        topBar.add(categoryPanel, BorderLayout.WEST);
        topBar.add(availablePanel ,BorderLayout.EAST);
        topBar.add(searchBar, BorderLayout.CENTER);
        this.add(topBar, BorderLayout.NORTH);



        container.setBorder(new EmptyBorder(40, 150, 40, 150));
        container.setOpaque(false);

        setCards(list);
        this.activeCards.addAll(allCards);


        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setOpaque(false);
        mainContentPanel.setFocusable(true);
        mainContentPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mainContentPanel.requestFocusInWindow();
            }
        });

        mainContentPanel.add(container);


        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setOpaque(false);

        wrapperPanel.add(container, BorderLayout.NORTH);

        JPanel rightContainer = new JPanel(new BorderLayout());
        rightContainer.setOpaque(false);
        rightContainer.add(container, BorderLayout.NORTH);

        this.add(rightContainer, BorderLayout.CENTER);
        this.setVisible(true);
    }


    private void performSearch(String query) {

        String searchText = query.toLowerCase().trim();
        if (searchText.isEmpty() || searchText.equals(" Search ...")) {
            updateCards();
            return;
        }

        int matchCountProduct = 0;
        container.removeAll();

        for (ItemBtn card : activeCards) {
            if (card.getTextName().toLowerCase().contains(searchText)) {
                container.add(card);
                matchCountProduct++;
            }
        }

        if (matchCountProduct == 0) {
            showNoResults();
        } else {
            container.setLayout(new GridLayout(0, 3, 100, 100));
            NoResults.setVisible(false);

            if (addCard != null) {
                addCard.setVisible(false);
            }
        }

        container.revalidate();
        container.repaint();
    }

    public AddBtn addCard;


    public void setCards(ArrayList<Item> list  ) {
        allCards.clear();
        activeCards.clear();
    container.removeAll();
    for (Item item : list) {
        ItemBtn newCard = new ItemBtn(item.getName(), String.valueOf(item.getPrice()), new ImageIcon(item.getImage()), "description");
        allCards.add(newCard);
        activeCards.add(newCard);
        container.add(newCard);
    }

        if (addCard == null) {
            addCard = new AddBtn(() -> {
                addNewItem("New Item", "$0.00", new ImageIcon("./assets/paint.png"), "description");
            });
        }
        updateCards();
        addCard.setVisible(true);

        container.add(addCard);
        container.revalidate();
        container.repaint();

    }
    public void updateCards() {
        container.removeAll();
        container.setLayout(new GridLayout(0, 3, 100, 100));

        for (ItemBtn button : activeCards) {
        container.add(button);
        }
        boolean isAllSelected = category.getSelectedItem().equals("All");
        boolean isSearchEmpty = Searchtext.getText().isEmpty() || Searchtext.getText().equals(" Search ...");

        if (addCard != null) {
            if (isAllSelected && isSearchEmpty) {
                addCard.setVisible(true);
                container.add(addCard);
            } else {
                addCard.setVisible(false);
            }
        }

        container.revalidate();
        container.repaint();
    }

    public ItemBtn addNewItem(String name, String price, ImageIcon icon, String description) {

        ItemBtn newCard = new ItemBtn(name, price, icon, description);

        allCards.add(newCard);
        activeCards.add(newCard);

        if (addCard != null) {
            int index = container.getComponentZOrder(addCard);
            if (index != -1) {
                container.add(newCard, index);
            } else {
                container.add(newCard);
                container.add(addCard);
            }
        } else {
            container.add(newCard);
        }

        container.revalidate();
        container.repaint();

        return newCard;

    }

    private void showNoResults() {

        container.removeAll();

        container.setLayout(new BorderLayout());

        NoResults.setVisible(true);
        container.add(NoResults, BorderLayout.CENTER);

        if (addCard != null) {
            addCard.setVisible(false);
        }

        container.revalidate();
        container.repaint();
    }

    public ArrayList<ItemBtn> getProductButtons() {
        return this.allCards;
    }

    public ArrayList<Item> getCurrentItems() {
        return currentItems;
    }

    public void setCurrentItems(ArrayList<Item> currentItems) {
        this.currentItems = currentItems;
    }

    public ArrayList<ItemBtn> getActiveCards() {
        return activeCards;
    }

    public void setActiveCards(ArrayList<Item> filteredItems) {
        ArrayList<ItemBtn> btns = new ArrayList<>();

        for (Item item : filteredItems) {
            for (ItemBtn btn : activeCards) {
                if (btn.getTextName().equalsIgnoreCase(item.getName())) {
                    btns.add(btn);
                    break;
                }
            }
        }

        this.activeCards = btns;

        updateCards();
    }

    public void setActiveCardsWhenAll(ArrayList<Item> filteredItems) {
        ArrayList<ItemBtn> btns = new ArrayList<>();

        for (Item item : filteredItems) {
            for (ItemBtn btn : allCards) {
                if (btn.getTextName().equalsIgnoreCase(item.getName())) {
                    btns.add(btn);
                    break;
                }
            }
        }

        this.activeCards = btns;

        updateCards();
    }

    public void removeItem(ItemBtn card) {

        allCards.remove(card);
        activeCards.remove(card);

        container.remove(card);

        container.revalidate();
        container.repaint();
    }


    public JComboBox<String> getCategory() {
        return category;
    }

    public JComboBox<String> getAvailable() {
        return available;
    }


    public JPanel getContainer() {
        return container;
    }
}


