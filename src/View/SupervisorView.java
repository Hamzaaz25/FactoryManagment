//package View;
//import Controller.LoginController;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//public class SupervisorView extends JFrame implements ActionListener {
//
//    JPanel sideMenu;
//    //JLabel menuTitle;
//
//    JLabel supervisorPIC;
//    JLabel name;
//    JLabel age;
//    JLabel experience;
//
//    JButton itembutton;
//    JButton productbutton;
//    JButton logoutbutton;
//
//    JLabel welcome;
//    JLabel label;
//    JLabel productLine;
//
//    JButton button1;
//    JLabel label1;
//
//    JButton button2;
//    JLabel label2;
//
//    JButton button3;
//    JLabel label3;
//
//    JLabel searchlabel;
//    JComboBox searchBox;
//
//    String message;
//    ActionListener e ;
//
//        public SupervisorView(String m) {
//            this.message = m;
//            this.setTitle("Supervisor");
//            this.setSize(1000, 650);
//            this.setLocationRelativeTo(null);
//            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            this.setLayout(null);
//
//            //background image
//            SupervisorBackground bg = new SupervisorBackground("./assets/Furniture Background (4).jpeg");
//            this.setContentPane(bg);
//            JLabel background = new JLabel();
//            background.setBounds(0, 0, 1000, 650);
//            background.setLayout(null);
//            this.add(background);
//
//            //side menu
//            sideMenu = new JPanel();
//            sideMenu.setBounds(0, 0, 180, 650);
//            sideMenu.setBackground(new Color(108, 185, 190));
//            sideMenu.setLayout(null);
//            background.add(sideMenu);
//
//            //The supervisor information
//            supervisorPIC = new JLabel();
//            supervisorPIC.setBounds(25, 20, 130, 130);
//            supervisorPIC.setIcon(new ImageIcon("./assets/DinoDino.jpg"));
//            sideMenu.add(supervisorPIC);
//
//            name = new JLabel("Name :"+ message);
//            name.setBounds(20, 160, 150, 25);
//            sideMenu.add(name);
//
//            age = new JLabel("Age : 1500 years");
//            age.setBounds(20, 185, 150, 25);
//            sideMenu.add(age);
//
//            experience = new JLabel("Years of work : 700 years");
//            experience.setBounds(20, 210, 150, 25);
//            sideMenu.add(experience);
//
//            //SideMenu Buttons
//            itembutton = new JButton("Item");
//            itembutton.setBounds(20, 270, 140, 40);
//            itembutton.setFocusPainted(false);
//            itembutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            sideMenu.add(itembutton);
//
//            productbutton = new JButton("Product");
//            productbutton.setBounds(20, 330, 140, 40);
//            productbutton.setFocusPainted(false);
//            productbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            sideMenu.add(productbutton);
//
//            logoutbutton = new JButton("Logout");
//            logoutbutton.setBounds(20,560,140,40);
//            logoutbutton.setFocusable(false);
//            logoutbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            sideMenu.add(logoutbutton);
//
//            //the big welcome
//            welcome = new JLabel("WELCOME", SwingConstants.CENTER);
//            welcome.setFont(new Font("MVBOLI", Font.BOLD, 42));
//            welcome.setBounds(380, 20, 400, 50);
//            welcome.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            background.add(welcome);
//
//            label = new JLabel(
//                    "Welcome "+message+" you're the supervisor of these product lines",
//                    SwingConstants.CENTER
//            );
//
//            label.setFont(new Font("Arial", Font.PLAIN, 18));
//            label.setForeground(Color.DARK_GRAY);
//            label.setBounds(330, 80, 500, 40);
//            background.add(label);
//
//
//            //Product Lines........
//            productLine = new JLabel("Product Lines", SwingConstants.CENTER);
//            productLine.setFont(new Font("Arial", Font.BOLD, 28));
//            productLine.setForeground(Color.BLACK);
//            productLine.setBounds(430, 140, 300, 40);
//            background.add(productLine);
//
//            //First Product Line
//            button1 = new JButton();
//            button1.setBounds(230, 240, 180, 180);
//            button1.setIcon(new ImageIcon(""));
//            button1.setFocusPainted(false);
//            button1.addActionListener(this);
//            button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            background.add(button1);
//
//            label1 = new JLabel("First Line", SwingConstants.CENTER);
//            label1.setFont(new Font("Arial", Font.BOLD, 16));
//            label1.setForeground(Color.BLACK);
//            label1.setBounds(230, 430, 180, 25);
//            background.add(label1);
//
//            //Second Product Line
//            button2 = new JButton();
//            button2.setBounds(500, 240, 180, 180);
//            button2.setIcon(new ImageIcon(""));
//            button2.setFocusPainted(false);
//            button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            background.add(button2);
//
//            label2 = new JLabel("Second Line", SwingConstants.CENTER);
//            label2.setFont(new Font("Arial", Font.BOLD, 16));
//            label2.setForeground(Color.BLACK);
//            label2.setBounds(500, 430, 180, 25);
//            background.add(label2);
//
//            //Third Product Line
//            button3 = new JButton();
//            button3.setBounds(770, 240, 180, 180);
//            button3.setIcon(new ImageIcon(""));
//            button3.setFocusPainted(false);
//            button3.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            background.add(button3);
//
//            label3 = new JLabel("Third Line", SwingConstants.CENTER);
//            label3.setFont(new Font("Arial", Font.BOLD, 16));
//            label3.setForeground(Color.BLACK);
//            label3.setBounds(770, 430, 180, 25);
//            background.add(label3);
//
//            //Search
//            searchlabel = new JLabel();
//            //searchlabel.setBounds(850, 20, 100, 25);
//            searchlabel.setBounds(880, 10, 100, 25);
//            background.add(searchlabel);
//            String[] searchingOptions = {
//                    "Bestest of the Bests",
//                    "Best Selling Item",
//                    "Best Selling Product",
//                    "Best Active ProductLine"
//            };
//            searchBox = new JComboBox<>(searchingOptions);
//            //searchBox.setBounds(800, 50, 180, 30);
//            searchBox.setBounds(780, 40, 200, 30);
//            background.add(searchBox);
//            logoutbutton.addActionListener(this);
//            itembutton.addActionListener(this);
//            productbutton.addActionListener(this);
//            this.setVisible(true);
//
//        }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//            if (e.getSource()==button1)
//
//                new ProductLineDisplayView();
//            if(e.getSource() == productbutton){
//                this.setVisible(false);
//                this.dispose();
//                new ProductsView(this.message);
//            }
//            if(e.getSource() == itembutton){
//                this.setVisible(false);
//                this.dispose();
//                new ItemsView(this.message);
//            }
//            if(e.getSource()==logoutbutton) {
//                this.setVisible(false);
//                this.dispose();
//                new LoginController();
//
//            }
//
//    }
//}
