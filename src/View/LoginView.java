package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    JLabel background;
    JPanel card;
    JLabel cardImage;
    JLabel title;
    JLabel username;
    JTextField user;
    JPasswordField password;
    JButton loginbutton;
    JLabel userPassword;

    public LoginView() {

        this.setTitle("Furniture Factory Login");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        //background image
        LogInBackground bg = new LogInBackground("./assets/log in background.jpeg");
        this.setContentPane(bg);
        background = new JLabel();
        background.setBounds(0, 0, 1000, 600);
        background.setLayout(null);
        this.add(background);

        //login card
        card = new JPanel();
        card.setBounds(300, 100, 400, 400);
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        background.add(card);


        ImageIcon cardImg = new ImageIcon("./assets/login card.jpeg");
        cardImage = new JLabel(cardImg);
        cardImage.setBounds(0, 0, 400, 400);
        cardImage.setLayout(null);
        card.add(cardImage);

        title = new JLabel("User Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(0, 30, 400, 30);
        card.add(title);

        username = new JLabel("Username");
        username.setBounds(60, 90, 100, 25);
        card.add(username);








        user = new JTextField();
//        user.setText("Username");
        user.setBounds(60, 120, 280, 35);
        card.add(user);

        userPassword = new JLabel("Password");
        userPassword.setBounds(60, 170, 100, 25);
        card.add(userPassword);

        password = new JPasswordField();
//        password.setText("Password");
        password.setBounds(60, 200, 280, 35);
        card.add(password);

        loginbutton = new JButton("LOGIN");
        loginbutton.setBounds(60, 260, 280, 40);
        loginbutton.setBackground(new Color(2, 37, 52));
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setFocusPainted(false);

        card.add(loginbutton);


        this.setVisible(true);
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

        // Reset GUI
        user.setText("");
        password.setText("");
        user.requestFocusInWindow();
    }

    public String getUser() { return user.getText(); }
    public String getPass() { return new String(password.getPassword()); }
    public void addLoginListener(ActionListener listener) { loginbutton.addActionListener(listener);}


}
