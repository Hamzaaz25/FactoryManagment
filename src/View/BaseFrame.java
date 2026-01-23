package View;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BaseFrame extends JFrame {

    String name;
    protected JPanel sideBar;
    protected JPanel footerBar;
    protected JPanel menuBar;
    protected JPanel mainContent;
    protected JPanel container;
    protected JLabel TitlelblPage;
    JButton profileButton;
    String title;
    JButton productsLineButton;
    JButton productsButton;
    JButton itemsButton;
    JButton tasksButton;
    JButton logo;
    private JButton SelectedButton = null;



    public BaseFrame(String name,String title){



        this.name=name;
        this.title=title;

        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        BackGround bg = new BackGround("./assets/sof.jpg");
        bg.setLayout(new BorderLayout());
        this.setContentPane(bg);

        sideBar = new JPanel();

        sideBar.setPreferredSize(new Dimension(150, 200));
        sideBar.setBackground (new Color(120, 165, 200) );
        sideBar.setLayout(new BorderLayout());


        ImageIcon icon5 = new ImageIcon("./assets/logoo.png");
        Image scaled5 = icon5.getImage().getScaledInstance(150, 56, Image.SCALE_SMOOTH);
        logo = new JButton( icon5);
        logo.setBorderPainted(false);
        logo.setFocusPainted(false);
        logo.setContentAreaFilled(false);
        logo.setMargin(new java.awt.Insets(0, 0, 0, 0));
       logo.setIcon(new ImageIcon(scaled5));


        ImageIcon icon1 = new ImageIcon("./assets/productline.png");
        Image scaled1 = icon1.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        productsLineButton = new JButton("Lines", icon1);
        productsLineButton.setIcon(new ImageIcon(scaled1));


        ImageIcon icon2 = new ImageIcon("./assets/product.png");
        Image scaled2 = icon2.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        productsButton = new JButton("products", icon2);
        productsButton.setIcon(new ImageIcon(scaled2));


        ImageIcon icon3 = new ImageIcon("./assets/item.png");
        Image scaled3 = icon3.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        itemsButton = new JButton("items", icon3);
        itemsButton.setIcon(new ImageIcon(scaled3));


        ImageIcon icon4 = new ImageIcon("./assets/tasks.png");
        Image scaled4 = icon4.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        tasksButton = new JButton("tasks", icon4);
        tasksButton.setIcon(new ImageIcon(scaled4));


        JPanel buttonsContainer = new JPanel(new GridLayout(0, 1, 0, 0));
        buttonsContainer.setOpaque(false);

        buttonsContainer.add(logo);

        styleSidebarButton(productsButton);
        productsButton.setOpaque(true);
        productsButton.setContentAreaFilled(true);
        productsButton.setBackground(new Color(20, 40, 80));
        SelectedButton = productsButton;
        buttonsContainer.add(productsButton);


        styleSidebarButton(itemsButton);
        buttonsContainer.add(itemsButton);


        styleSidebarButton(productsLineButton);
        buttonsContainer.add(productsLineButton);


        styleSidebarButton(tasksButton);
        buttonsContainer.add(tasksButton);

        sideBar.add(buttonsContainer,BorderLayout.NORTH);

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


        menuBar = new JPanel(new GridLayout());
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
        JLabel Userlbl = new JLabel("Welcome  "+this.name);
        Userlbl.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        Userlbl.setForeground(Color.WHITE);
        ImageIcon profileImg = new ImageIcon("./assets/profile.png");
        Image img = profileImg.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        profileButton = new JButton();
        profileButton.setIcon(new ImageIcon(img));
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        profileButton.setPreferredSize(new Dimension(35, 35));
        profileButton.setFocusPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setBorderPainted(false);
        profilePanel.add(Userlbl);
        profilePanel.add(profileButton);
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        menuBar.add(profilePanel, gbc);
        TitlelblPage = new JLabel(title, SwingConstants.CENTER);
        TitlelblPage.setFont(new Font("Segoe UI", Font.BOLD, 40));
        TitlelblPage.setForeground(Color.white);
        gbc.gridx = 1;
        gbc.weightx = 0;
        menuBar.add(TitlelblPage, gbc);
        menuBar.add(profilePanel, BorderLayout.EAST);


        mainContent = new JPanel();
        mainContent.setLayout(new CardLayout());
        mainContent.setOpaque(false);


        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        footerBar = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        footerBar.setPreferredSize(new Dimension(0, 85));
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

        container =new JPanel(new BorderLayout());
        container.setOpaque(false);
         container.add(menuBar,BorderLayout.NORTH);
         container.add(scrollPane,BorderLayout.CENTER);
         container.add(footerBar,BorderLayout.SOUTH);

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topBar.setOpaque(true);
        topBar.setBackground(new Color(20, 33, 61));
        topBar.setPreferredSize(new Dimension(this.getWidth(), 35));

        JButton closeBtn = new JButton();
        ImageIcon originalIcon = new ImageIcon("./assets/cross.png");
        Image scaledImgg = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        closeBtn.setIcon(new ImageIcon(scaledImgg));
        closeBtn.setFocusPainted(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.setForeground(Color.RED);
        closeBtn.setFont(new Font("Arial", Font.BOLD, 18));

        closeBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Exit",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        topBar.add(closeBtn);

        this.add(topBar, BorderLayout.NORTH);


         this.add(container,BorderLayout.CENTER);
         this.add(sideBar,BorderLayout.WEST);

        productsButton.doClick();

        this.setVisible(true);
    }
    public void styleSidebarButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        button.setForeground(Color.BLACK);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 60));
        button.setBorder(new EmptyBorder(0, 20, 0, 0));

        button.addActionListener(e -> {

            if (SelectedButton != null) {
                SelectedButton.setOpaque(false);
                SelectedButton.setContentAreaFilled(false);
                SelectedButton.setBackground(null);
            }

            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.setBackground(new Color(20, 40, 80));

            SelectedButton = button;


            button.repaint();
        });
    }


    public void switchContent(JPanel newPanel, String newTitle) {

        mainContent.removeAll();

        mainContent.setLayout(new BorderLayout());
        mainContent.add(newPanel, BorderLayout.CENTER);


        TitlelblPage.setText(newTitle);


        mainContent.revalidate();
        mainContent.repaint();
    }


public JButton getProductsLineButton(){
        return this.productsLineButton;
}

    public JButton getTasksButton() {
        return tasksButton;
    }

    public JButton getItemsButton() {
        return itemsButton;
    }

    public JButton getProductsButton() {
        return productsButton;
    }

    public JButton getProfileButton(){
        return this.profileButton;
    }

    public JButton getProductLinesButton(){
        return this.productsLineButton;
    }


    public void showError(String message) {
        // Create a custom panel for the popup
        JPanel panel = new JPanel();
        panel.setBackground(UIManager.getColor("OptionPane.background"));
        panel.setLayout(new BorderLayout(10, 10));

        JLabel msgLabel = new JLabel(message);
        msgLabel.setForeground(UIManager.getColor("OptionPane.messageForeground"));
        msgLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panel.add(msgLabel, BorderLayout.CENTER);

        // Show the popup
        JOptionPane.showMessageDialog(
                this,
                panel,
                message,
                JOptionPane.ERROR_MESSAGE
        );




    }
}
