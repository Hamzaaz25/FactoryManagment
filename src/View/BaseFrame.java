package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseFrame extends JFrame {
    String name;
    protected JPanel sideBar;
    protected JPanel footerBar;
    protected JPanel menuBar;
    protected JPanel mainContent;
    protected JPanel container;
    String title;



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
        JLabel TitlelblPage = new JLabel(title, SwingConstants.CENTER);
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

        container =new JPanel(new BorderLayout());
        container.setOpaque(false);
         container.add(menuBar,BorderLayout.NORTH);
         container.add(scrollPane,BorderLayout.CENTER);
         container.add(footerBar,BorderLayout.SOUTH);

         this.add(container,BorderLayout.CENTER);
         this.add(sideBar,BorderLayout.WEST);

        this.setVisible(true);
    }
    public void styleSidebarButton(JButton button) {
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        button.setForeground(Color.WHITE);
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.setIconTextGap(15);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 60));

    }

    public void showAPanel(JPanel panel) {
        mainContent.add(panel);
    }

}
