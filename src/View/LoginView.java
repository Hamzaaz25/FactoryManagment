package View;

import View.Util.LogInBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    JPanel card;
    JLabel title;
    JTextField user;
    JPasswordField password;
    JButton loginbutton;


    public LoginView() {

        this.setTitle("Furniture Factory Login");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //background image
       // LogInBackground bg = new LogInBackground("./assets/log in background.jpeg");
        LogInBackground bg = new LogInBackground("./assets/walll.jpg");
        bg.setLayout(new GridBagLayout());
        this.setContentPane(bg);

        //login card
        card = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
              //  Image img = new ImageIcon("./assets/login card.jpeg").getImage();
                Image img = new ImageIcon("./assets/cardd.png").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        card.setPreferredSize(new Dimension(350, 350));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        card.setBackground(Color.WHITE);

        title = new JLabel("User Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(40));

        Dimension fieldSize = new Dimension(280, 35);

        user = new JTextField();
        user.putClientProperty("JComponent.arc", 15);
        user.putClientProperty("JTextField.placeholderText", "Username");
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        user.setMaximumSize(fieldSize);
        user.setPreferredSize(fieldSize);
        card.add(user);
        card.add(Box.createVerticalStrut(40));


        password = new JPasswordField(20);
        password.putClientProperty("JComponent.arc", 15);
        password.putClientProperty("JTextField.placeholderText", "Password");
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setMaximumSize(fieldSize);
        password.setPreferredSize(fieldSize);
        card.add(password);
        card.add(Box.createVerticalStrut(40));

        loginbutton = new JButton("LOGIN");
        loginbutton.setBackground(new Color(2, 37, 52));
        loginbutton.setPreferredSize(new Dimension(600, 40));
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setFocusPainted(false);
        loginbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginbutton.setMaximumSize(fieldSize);
        loginbutton.setPreferredSize(fieldSize);
        card.add(loginbutton);
        this.getRootPane().setDefaultButton(loginbutton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 5.0;
        gbc.weighty = 5.0;
        gbc.anchor = GridBagConstraints.CENTER;

        this.add(card,gbc);
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

    public JPasswordField getPassword() {
        return password;
    }

    public JTextField getUserField(){
        return user;
    }

    public JButton getLoginbutton() {
        return loginbutton;
    }
}
