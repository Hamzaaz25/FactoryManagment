package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class ItemsView extends JPanel {

    private ArrayList<ItemBtn> allCards = new ArrayList<>();
    private JPanel container=new JPanel(new GridLayout(0, 3, 100, 100));
    private JTextField Searchtext = new JTextField(" Search ...");;
    JLabel NoResults = new JLabel("No items match your search", SwingConstants.CENTER);
    String name;

   public ItemsView(String name) {

       this.name = name;
       this.setOpaque(false);
       this.setLayout(new BorderLayout());


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

       this.add(searchBar,BorderLayout.NORTH);


        container.setBorder(new EmptyBorder(40, 150, 40, 150));
        container.setOpaque(false);

        setCards();


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
        rightContainer.add(container, BorderLayout.CENTER);

        this.add(rightContainer, BorderLayout.CENTER);
        this.setVisible(true);
    }


    private void performSearch(String query) {
        int matchCountProduct = 0;
        String searchText = query.toLowerCase().trim();
        container.removeAll();
        for (ItemBtn card : allCards) {
            if (card.getTextName().toLowerCase().contains(searchText)) {
                container.add(card);
                matchCountProduct++;
            }
        }

        if (matchCountProduct == 0 && !searchText.isEmpty()) {
            container.setLayout(new BorderLayout());

            NoResults.setHorizontalAlignment(SwingConstants.CENTER);
            NoResults.setVerticalAlignment(SwingConstants.CENTER);

            container.add(NoResults, BorderLayout.CENTER);
            NoResults.setVisible(true);

            if(addCard != null) addCard.setVisible(false);
        } else {
            container.setLayout(new GridLayout(0, 3, 100, 100));

            NoResults.setVisible(false);
            if (addCard != null) {
                addCard.setVisible(true);
                container.add(addCard);
            }
        }

        container.revalidate();
        container.repaint();
    }

    AddBtn addCard;

    public void setCards() {

        addNewItem("cotton", "$5.00", new ImageIcon("./assets/wool.png"), "description");
        addNewItem("wood", "$4.00", new ImageIcon("./assets/wood.png"), "description");
        addNewItem("silk", "$2.50", new ImageIcon("./assets/silk.png"), "description");
        addNewItem("spring", "$3.50", new ImageIcon("./assets/spring.png"), "description");
        addNewItem("hinge", "$5.50", new ImageIcon("./assets/hinge.png"), "description");
        addNewItem("handle", "$34.50", new ImageIcon("./assets/handle.png"), "description");
        addNewItem("screw", "$3.50", new ImageIcon("./assets/screw.png"), "description");
        addNewItem("paint", "$9.50", new ImageIcon("./assets/paint.png"), "description");
        addNewItem("paint", "$9.50", new ImageIcon("./assets/paint.png"), "description");
        addNewItem("paint", "$9.50", new ImageIcon("./assets/paint.png"), "description");
        addNewItem("paint", "$9.50", new ImageIcon("./assets/paint.png"), "description");
        addNewItem("paint", "$9.50", new ImageIcon("./assets/paint.png"), "description");
        addNewItem("paint", "$9.50", new ImageIcon("./assets/paint.png"), "description");



        addCard = new AddBtn(() -> {

            addNewItem("New Item", "$0.00",new ImageIcon("./assets/paint.png"),"description");
        });

        container.add(addCard);

    }

    public void addNewItem(String name, String price, ImageIcon icon, String description) {

        ItemBtn newCard = new ItemBtn(name, price, icon, description);

        allCards.add(newCard);

        if (container.getComponentCount() > 0 && addCard != null) {
            int addCardIndex = container.getComponentCount() - 1;
            container.add(newCard, addCardIndex);
        } else {
            container.add(newCard);
        }

        container.revalidate();
        container.repaint();
    }




}


