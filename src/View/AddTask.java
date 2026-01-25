package View;

import javax.swing.*;
import java.awt.*;

public class AddTask extends JPanel {

    private final JTextField clientField;
    private final JComboBox<String> productBox;
    private final JTextField quantityField;
    private final JButton saveButton;


    public AddTask(String[] products) {
        setBackground(new Color(38, 55, 85));
        setLayout(new GridBagLayout()); // center everything

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;


        JLabel headerLabel = new JLabel("Add Task Info");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(headerLabel, gbc);

        gbc.gridwidth = 1;

        int fieldW = 260;
        int fieldH = 40;


        JLabel clientLabel = createLabel("Client");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(clientLabel, gbc);

        clientField = createField(fieldW, fieldH);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(clientField, gbc);


        JLabel productLabel = createLabel("Product");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(productLabel, gbc);

        productBox = new JComboBox<>(products);
        productBox.setFont(new Font("Arial", Font.PLAIN, 16));
        productBox.setBackground(new Color(20, 33, 61));
        productBox.setForeground(Color.WHITE);
        productBox.setBorder(BorderFactory.createLineBorder(new Color(120, 165, 200), 2));
        productBox.setPreferredSize(new Dimension(fieldW, fieldH));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(productBox, gbc);


        JLabel quantityLabel = createLabel("Quantity");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(quantityLabel, gbc);

        quantityField = createField(fieldW, fieldH);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(quantityField, gbc);

        saveButton = new JButton("SAVE");
        saveButton.setFocusPainted(false);
        saveButton.putClientProperty(
                "FlatLaf.style",
                "background: #4caf50; foreground: #fff; arc:10; font: bold 16"
        );
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(saveButton, gbc);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        return lbl;
    }

    private JTextField createField(int width, int height) {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBackground(new Color(20, 33, 61));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(120, 165, 200), 2));
        field.setPreferredSize(new Dimension(width, height));
        return field;
    }


    public JTextField getClientField() {
        return clientField;
    }

    public String getProductBox() {
        return productBox.getSelectedItem().toString();
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
