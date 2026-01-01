package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;


public class ItemFrame extends JFrame {

    private ArrayList<ItemBtn> allCards = new ArrayList<>();
    private JPanel container=new JPanel(new GridLayout(0, 3, 100, 100));
    private JTextField Searchtext = new JTextField(" Search ...");;
    JLabel NoResults = new JLabel("No items match your search", SwingConstants.CENTER);
    String message ;

   public ItemFrame(String message) {
       try {
           UIManager.setLookAndFeel(new FlatLightLaf());
       } catch (UnsupportedLookAndFeelException e) {
           throw new RuntimeException(e);
       }
       this.message = message;
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.black);
        BackGround bg = new BackGround("./assets/ttt.jpg");
        bg.setLayout(new BorderLayout());
        this.setContentPane(bg);

        JPanel sideBar = new JPanel();

        sideBar.setPreferredSize(new Dimension(150, 200));
        sideBar.setBackground (new Color(120, 165, 200) );
        sideBar.setBorder(new EmptyBorder(60, 0, 0, 0));
        sideBar.setLayout(new BorderLayout());


        ImageIcon icon = new ImageIcon("./assets/return.png");
        Image scaled = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        JButton backButton = new JButton("Back", icon);
        backButton.setIcon(new ImageIcon(scaled));
        backButton.setBackground(new Color(55, 100, 145));

       styleSidebarButton(backButton);
        sideBar.add(backButton, BorderLayout.NORTH);

        JPanel socialPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        socialPanel.setOpaque(false);

        JButton Instabtn = new JButton();
        ImageIcon icone = new ImageIcon("./assets/insta.png");
        Image scaledImg = icone.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Instabtn.setIcon(new ImageIcon(scaledImg));
        Instabtn.setPreferredSize(new Dimension(40, 40));
        Instabtn.setFocusPainted(false);
        Instabtn.setBorderPainted(false);
        Instabtn.setContentAreaFilled(false);
        Instabtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton FBbtn = new JButton();
        ImageIcon iconee = new ImageIcon("./assets/face.png");
        Image scaledImge = iconee.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
       FBbtn.setIcon(new ImageIcon(scaledImge));
       FBbtn.setPreferredSize(new Dimension(40, 40));
       FBbtn.setFocusPainted(false);
       FBbtn.setBorderPainted(false);
       FBbtn.setContentAreaFilled(false);
       FBbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        socialPanel.add(Instabtn);
        socialPanel.add(FBbtn);


        sideBar.add(socialPanel, BorderLayout.SOUTH);


        JPanel menuBar = new JPanel(new GridLayout());
        menuBar.setPreferredSize(new Dimension(0, 60));
        menuBar.setBackground(new Color(35, 70, 110));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftPanel.setOpaque(false);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        menuBar.add(leftPanel, gbc);
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        profilePanel.setOpaque(false);
        JLabel Userlbl = new JLabel("Welcome , "+this.message);
        Userlbl.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        Userlbl.setForeground(Color.WHITE);
        ImageIcon profileImg = new ImageIcon("./assets/profile.png");
        Image img = profileImg.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        JButton Profilebtn = new JButton();
        Profilebtn.setIcon(new ImageIcon(img));
        Profilebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Profilebtn.setPreferredSize(new Dimension(35, 35));
        Profilebtn.setFocusPainted(false);
        Profilebtn.setContentAreaFilled(false);
        Profilebtn.setBorderPainted(false);
        profilePanel.add(Userlbl);
        profilePanel.add(Profilebtn);
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        menuBar.add(profilePanel, gbc);
        JLabel TitlelblPage = new JLabel("Items", SwingConstants.CENTER);
        TitlelblPage.setFont(new Font("Segoe UI", Font.BOLD, 40));
        TitlelblPage.setForeground(Color.white);
        gbc.gridx = 1;
        gbc.weightx = 0;
        menuBar.add(TitlelblPage, gbc);
        menuBar.add(profilePanel, BorderLayout.EAST);



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

        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setOpaque(false);
        topSection.add(menuBar);
        topSection.add(searchBar);


        container.setBorder(new EmptyBorder(40, 150, 40, 150));
        container.setOpaque(false);

        setCards();

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

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

        JPanel footerBar = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        footerBar.setPreferredSize(new Dimension(0, 100));
        footerBar.setBackground(new Color(255, 255, 255, 200));
        footerBar.setOpaque(false);
        JLabel Footerlbl = new JLabel("©  AMH Furniture ", SwingConstants.CENTER);
        Footerlbl.setForeground(Color.BLACK);
        JPanel footerImagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        footerImagePanel.setOpaque(false);

        JLabel FooterImagelbl = new JLabel();
        ImageIcon footerIcon = new ImageIcon("./assets/AMH.png");
        Image scaledFooterImg = footerIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        FooterImagelbl.setIcon(new ImageIcon(scaledFooterImg));
        footerImagePanel.add(FooterImagelbl);
        footerBar.add(footerImagePanel, BorderLayout.EAST);

        JLabel DateTimelbl = new JLabel();
        DateTimelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        DateTimelbl.setForeground(Color.black);
        DateTimelbl.setBorder(new EmptyBorder(0, 20, 0, 0));

        Timer timer = new Timer(1000, e -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy  |  HH:mm:ss");
            DateTimelbl.setText(now.format(formatter));
        });
        timer.start();


        footerBar.add(DateTimelbl, BorderLayout.WEST);
        footerBar.add(Footerlbl, BorderLayout.CENTER);


        mainContentPanel.add(footerBar);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setOpaque(false);

        wrapperPanel.add(container, BorderLayout.NORTH);
        wrapperPanel.add(footerBar, BorderLayout.SOUTH);

        scrollPane.setViewportView(wrapperPanel);


        this.add(sideBar, BorderLayout.WEST);
        JPanel rightContainer = new JPanel(new BorderLayout());
        rightContainer.setOpaque(false);
        rightContainer.add(scrollPane, BorderLayout.CENTER);
        rightContainer.add(topSection, BorderLayout.NORTH);
        this.add(rightContainer, BorderLayout.CENTER);
        this.setVisible(true);
    }
    public void styleSidebarButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        button.setForeground(Color.WHITE);
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.setIconTextGap(15);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

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


