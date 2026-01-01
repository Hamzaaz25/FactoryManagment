package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;

    public LoginView() {
        setTitle("Login");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Username:"));
        userField = new JTextField();
        panel.add(userField);

        panel.add(new JLabel("Password:"));
        passField = new JPasswordField();
        panel.add(passField);

        loginBtn = new JButton("Login");

        panel.add(new JLabel());
        panel.add(loginBtn);

        setContentPane(panel);   // ⚠️ IMPORTANT
    }

    public String getUser() {
        return userField.getText();
    }

    public String getPass() {
        return new String(passField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginBtn.addActionListener(listener);
    }
}
