package View;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;
    private JButton themeBtn;

    public LoginView() {
        // Apply FlatLightLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use BoxLayout for vertical stacking
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        // Username
        JPanel userPanel = new JPanel(new BorderLayout(5, 5));
        userPanel.setOpaque(false);
        userPanel.add(new JLabel("Username:"), BorderLayout.WEST);
        userField = new JTextField();
        userPanel.add(userField, BorderLayout.CENTER);
        panel.add(userPanel);
        panel.add(Box.createVerticalStrut(10));

        // Password
        JPanel passPanel = new JPanel(new BorderLayout(5, 5));
        passPanel.setOpaque(false);
        passPanel.add(new JLabel("Password:"), BorderLayout.WEST);
        passField = new JPasswordField();
        passPanel.add(passField, BorderLayout.CENTER);
        panel.add(passPanel);
        panel.add(Box.createVerticalStrut(20));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);

        loginBtn = new JButton("Login");
        loginBtn.putClientProperty("JComponent.roundRect", true); // FlatLaf rounded
        loginBtn.setBackground(new Color(0, 120, 215));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setFocusPainted(false);

        themeBtn = new JButton("Toggle Theme");
        themeBtn.putClientProperty("JComponent.roundRect", true);
        themeBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        themeBtn.addActionListener(e -> switchTheme());

        buttonPanel.add(loginBtn);
        buttonPanel.add(themeBtn);

        panel.add(buttonPanel);

        setContentPane(panel);
        setVisible(true);
    }

    public String getUser() { return userField.getText(); }
    public String getPass() { return new String(passField.getPassword()); }
    public void addLoginListener(ActionListener listener) { loginBtn.addActionListener(listener); }

    private void switchTheme() {
        try {
            if (UIManager.getLookAndFeel() instanceof FlatLightLaf) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

        // Reset GUI
        userField.setText("");
        passField.setText("");
        userField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginView::new);
    }
}
