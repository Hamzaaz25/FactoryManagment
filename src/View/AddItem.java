package View;

import Enums.MaterialType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddItem extends JPanel {
    JButton save;
    JButton cancel;
    JButton imageBtn;
    JTextField nameField  ;
    JTextField amountField  ;
    JTextField priceField ;
    JTextField minimumField ;
    JComboBox categoryBox ;





    public AddItem() {
        this.setLayout(null);
        this.setBackground(new Color(38, 55, 85));

        //Main standards
        int startX = 350;
        int startY = 160;
        int labelW = 190;
        int fieldW = 260;
        int rowH = 40;
        int gapY = 40;

        //Fields
        nameField = new JTextField();
        addRow("Name", startX, startY, labelW, fieldW, rowH , nameField);
        priceField = new JTextField();
        addRow("Price", startX, startY + (rowH + gapY), labelW, fieldW, rowH ,priceField);

        //Combo Box
        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(startX, startY + 2 * (rowH + gapY), labelW, rowH);
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(categoryLabel);
        categoryBox = new JComboBox<>(MaterialType.values());
        addComboBox(categoryBox , startX + labelW + 10,
                startY + 2 * (rowH + gapY), fieldW, rowH);

        amountField = new JTextField();
        addRow("Amount", startX, startY + 3 * (rowH + gapY), labelW, fieldW, rowH , amountField);
        minimumField = new JTextField();
        addRow("Minimum Quantity", startX, startY + 4 * (rowH + gapY), labelW, fieldW, rowH ,minimumField);

        //Add image button
        imageBtn = new JButton("+");
        imageBtn.setBounds(950, startY, 360, 360);
        imageBtn.setFont(new Font("Arial", Font.BOLD, 170));
        imageBtn.setBackground(new Color(20, 33, 61));
        imageBtn.setForeground(new Color(120, 165, 200));
        imageBtn.setFocusPainted(false);
        imageBtn.setBorder(BorderFactory.createLineBorder(new Color(120, 165, 200), 4));
        this.add(imageBtn);

        //Save and Cancel
        save = new JButton("Save");
        save.setBounds(980, startY + 400, 130, 45);
        styleButton(save, new Color(58, 80, 120));
        this.add(save);

        cancel = new JButton("Cancel");
        cancel.setBounds(1130, startY + 400, 130, 45);
        styleButton(cancel, new Color(90, 110, 140));
        this.add(cancel);
    }

    //Add row method (to have a label and a textfield in each row similar to the other rows)
    private void addRow(String text, int x, int y, int labelW, int fieldW, int h , JTextField field) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, labelW, h);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);
        field.setBounds(x + labelW + 10, y, fieldW, h);
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBackground(new Color(20, 33, 61));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(120, 165, 200), 2));
        this.add(field);
    }

    //To style the buttons (فزلكة)
    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
    }

    private void addComboBox(JComboBox categoryBox ,  int x, int y, int fieldW, int h){
        categoryBox.setBounds(x,
                y,
                fieldW, h);
        categoryBox.setFont(new Font("Arial", Font.PLAIN, 16));
        categoryBox.setBackground(new Color(20, 33, 61));
        categoryBox.setForeground(Color.WHITE);
        categoryBox.setBorder(BorderFactory.createLineBorder(new Color(120, 165, 200), 2));
        categoryBox.setFocusable(false);

        this.add(categoryBox);
    }

    public JButton getSave() {
        return save;
    }

    public JButton getCancel() {
        return cancel;
    }

    public JButton getImageBtn() {
        return imageBtn;
    }

    public String getName (){
        return nameField.getText();
    }

    public String getPrice (){
        return priceField.getText();
    }
    public MaterialType getCategory(){
        return (MaterialType) categoryBox.getSelectedItem();
    }
    public String getAmount (){
        return amountField.getText();
    }
    public String getMinimum (){
        return minimumField.getText();
    }

    public boolean isAnyFieldBlank(){
        return (getMinimum().isBlank() || getAmount().isBlank() || getName().isBlank() || getPrice().isBlank());
    }
}
