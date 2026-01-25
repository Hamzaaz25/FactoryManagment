package View.Util;

import javax.swing.*;
import java.awt.*;

public class SimpleInputFrame extends JFrame {

    private JTextField textField;
    private JButton addButton;

    public SimpleInputFrame() {
        setTitle("ADD PRODUCT LINE");
        setSize(450, 280);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(38, 55, 85));


        textField = new JTextField();
        textField.setFont(textField.getFont().deriveFont(Font.PLAIN, 20f));
        textField.setBackground(new Color(20, 33, 61));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(textField, BorderLayout.CENTER);


        addButton = new JButton("Add");
        addButton.setFont(addButton.getFont().deriveFont(Font.BOLD, 18f));
        addButton.setBackground(new Color(20, 33, 61));
        addButton.setForeground(new Color(120, 165, 200));
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new Dimension(100, 50));

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(38, 55, 85));
        btnPanel.add(addButton);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(btnPanel, BorderLayout.SOUTH);
        setVisible(false);
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
