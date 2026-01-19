package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditThingDetails extends JPanel {

    JTextField txtName;
    JTextField txtPrice;
    JTextField txtAmount;
    JTextArea txtDescription;
    ImageIcon productIcon;
    JButton btnSave;

    public EditThingDetails(String name, String price, String description, ImageIcon productIcon, int amount) {
        this.productIcon = productIcon;

        this.setLayout(new GridLayout(1, 2));
        this.setOpaque(false);

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.setOpaque(true);
        editPanel.setBackground(new Color(0, 35, 71));
        editPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        JLabel lblEditTitle = new JLabel("Edit Product Details");
        lblEditTitle.setForeground(new Color(173, 216, 230));
        lblEditTitle.setFont(new Font("Segoe UI", Font.ITALIC, 14));

        txtName = new JTextField(name);
        formatEditField(txtName, 35, true);

        txtPrice = new JTextField(price);
        formatEditField(txtPrice, 25, false);

        txtAmount = new JTextField(String.valueOf(amount));
        formatEditField(txtAmount, 20, false);
        txtAmount.setForeground(new Color(144, 238, 144));

        txtDescription = new JTextArea(description);
        txtDescription.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtDescription.setForeground(Color.WHITE);
        txtDescription.setBackground(new Color(20, 55, 91));
        txtDescription.setCaretColor(Color.WHITE);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setBorder(BorderFactory.createLineBorder(new Color(55, 100, 145)));


        btnSave = new JButton("Save Changes");
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnSave.setBackground(new Color(40, 167, 69));
        btnSave.setForeground(Color.WHITE);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setAlignmentX(Component.LEFT_ALIGNMENT);

        editPanel.add(Box.createVerticalGlue());
        editPanel.add(lblEditTitle);
        editPanel.add(Box.createVerticalStrut(10));
        editPanel.add(txtName);
        editPanel.add(Box.createVerticalStrut(30));
        editPanel.add(new JLabel("Price:"));
        editPanel.add(txtPrice);
        editPanel.add(Box.createVerticalStrut(30));
        editPanel.add(new JLabel("Stock:"));
        editPanel.add(txtAmount);
        editPanel.add(Box.createVerticalStrut(30));
        editPanel.add(new JScrollPane(txtDescription));
        editPanel.add(Box.createVerticalStrut(40));
        editPanel.add(btnSave);
        editPanel.add(Box.createVerticalGlue());

        JLabel imageContainer = new JLabel();
        imageContainer.setOpaque(true);
        imageContainer.setHorizontalAlignment(SwingConstants.CENTER);
        imageContainer.setBackground(Color.WHITE);

        Image img = productIcon.getImage();
        Image scaledImg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        imageContainer.setIcon(new ImageIcon(scaledImg));

        this.add(editPanel);
        this.add(imageContainer);
    }

    private void formatEditField(JTextField field, int fontSize, boolean bold) {
        field.setFont(new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, fontSize));
        field.setForeground(Color.WHITE);
        field.setOpaque(false);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        field.setMaximumSize(new Dimension(500, 50));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public String getName() { return txtName.getText(); }
    public String getPrice() { return txtPrice.getText(); }
    public String getDescription() { return txtDescription.getText(); }
    public String getAmount() { return txtAmount.getText(); }

    public void addActionListener(ActionListener a) {
        this.btnSave.addActionListener(a);
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
        txtName.setText(this.getName());
        txtPrice.setText(String.valueOf(getPrice()));
        txtAmount.setText(String.valueOf(this.getAmount()));
        txtName.requestFocusInWindow();


    }
}
