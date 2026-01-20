package View;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddProduct extends JPanel {
    JTextField nameField;
    JTextField priceField;
    JTextField itemsField;

    //Test map
    Map<String, Integer> selectedItems = new LinkedHashMap<>();


    public AddProduct(){
        this.setLayout(null);
        this.setBackground(new Color(38, 55, 85));

        //Main standards
        int startX = 350;
        int startY = 160;
        int labelW = 180;
        int fieldW = 260;
        int rowH = 40;
        int gapY = 40;

        //Name
        addRow("Name", startX, startY, labelW, fieldW, rowH);
        nameField = (JTextField) getComponent(getComponentCount() - 1);

        //Price
        addRow("Price", startX, startY + (rowH + gapY), labelW, fieldW, rowH);
        priceField = (JTextField) getComponent(getComponentCount() - 1);

        //Pick Items (button like field)
        JLabel itemsLabel = new JLabel("Items");
        itemsLabel.setBounds(startX, startY + 2 * (rowH + gapY), labelW, rowH);
        itemsLabel.setForeground(Color.WHITE);
        itemsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(itemsLabel);

        itemsField = new JTextField("Pick Items");
        itemsField.setBounds(startX + labelW + 10,
                startY + 2 * (rowH + gapY), fieldW, rowH);
        itemsField.setEditable(false);
        itemsField.setBackground(new Color(20, 33, 61));
        itemsField.setForeground(new Color(160, 180, 210));
        itemsField.setFont(new Font("Arial", Font.PLAIN, 16));
        itemsField.setHorizontalAlignment(JTextField.CENTER);
        itemsField.setCursor(new Cursor(Cursor.HAND_CURSOR));
        itemsField.setBorder(BorderFactory.createLineBorder(
                new Color(120, 165, 200), 2));
        this.add(itemsField);

        //To open the pick Items frame
        itemsField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new PickItemsFrame(AddProduct.this);
            }
        });

        //Add image button
        JButton imageBtn = new JButton("+");
        imageBtn.setBounds(950, startY, 360, 360);
        imageBtn.setFont(new Font("Arial", Font.BOLD, 170));
        imageBtn.setBackground(new Color(20, 33, 61));
        imageBtn.setForeground(new Color(120, 165, 200));
        imageBtn.setFocusPainted(false);
        imageBtn.setBorder(BorderFactory.createLineBorder(
                new Color(120, 165, 200), 4));
        this.add(imageBtn);

        //Save and Cancel
        JButton save = new JButton("Save");
        save.setBounds(980, startY + 400, 130, 45);
        styleButton(save, new Color(58, 80, 120));
        this.add(save);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(1130, startY + 400, 130, 45);
        styleButton(cancel, new Color(90, 110, 140));
        this.add(cancel);
    }

    //Add row method (to have a label and a textfield in each row similar to the other rows)
    private void addRow(String text, int x, int y, int labelW, int fieldW, int h) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, labelW, h);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);

        JTextField field = new JTextField();
        field.setBounds(x + labelW + 10, y, fieldW, h);
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBackground(new Color(20, 33, 61));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(
                new Color(120, 165, 200), 2));
        this.add(field);
    }

    //To style the buttons (فزلكة)
    private void styleButton(JButton button, Color bg) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    //The final result in the pick items "button"
    public void updateItemsField() {
        StringBuilder sb = new StringBuilder();
        selectedItems.forEach((k, v) ->
                sb.append(k).append(" x").append(v).append(", "));
        itemsField.setText(sb.toString());

    }
}
