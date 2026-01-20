package View;

import javax.swing.*;
import java.awt.*;

public class AddItem extends JPanel {


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
        addRow("Name", startX, startY, labelW, fieldW, rowH);
        addRow("Price", startX, startY + (rowH + gapY), labelW, fieldW, rowH);
        addRow("category", startX, startY + 2 * (rowH + gapY), labelW, fieldW, rowH);
        addRow("Amount", startX, startY + 3 * (rowH + gapY), labelW, fieldW, rowH);
        addRow("Minimum Quantity", startX, startY + 4 * (rowH + gapY), labelW, fieldW, rowH);

        //Add image button
        JButton imageBtn = new JButton("+");
        imageBtn.setBounds(950, startY, 360, 360);
        imageBtn.setFont(new Font("Arial", Font.BOLD, 170));
        imageBtn.setBackground(new Color(20, 33, 61));
        imageBtn.setForeground(new Color(120, 165, 200));
        imageBtn.setFocusPainted(false);
        imageBtn.setBorder(BorderFactory.createLineBorder(new Color(120, 165, 200), 4));
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
}
