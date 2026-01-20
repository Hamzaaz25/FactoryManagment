package View;

import javax.swing.*;
import java.awt.*;

public class PickItemsFrame extends JFrame {

    JComboBox<String> itemsBox;
    JSlider slider;

    AddProduct parent;

    String[] items = {
            "Wood", "Cotton", "Item 3",
            "Item 4", "Item 5", "Item 6", "Item 7"
    };

    public PickItemsFrame(AddProduct parent) {
        this.parent = parent;

        this.setTitle("Pick Items");
        this.setSize(350, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(38, 55, 85));
        this.setResizable(false);

        slider = new JSlider(0, 10, 0);
        slider.setBounds(30, 20, 280, 50);
        slider.setBackground(new Color(38, 55, 85));
        slider.setForeground(new Color(160, 180, 210));
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        this.add(slider);

        itemsBox = new JComboBox<>(items);
        itemsBox.setBounds(30, 90, 280, 30);
        itemsBox.setBackground(new Color(20, 33, 61));
        itemsBox.setForeground(Color.WHITE);
        itemsBox.setBorder(BorderFactory.createLineBorder(
                new Color(120, 165, 200), 2
        ));
        this.add(itemsBox);

        //Changing the slider maximum value based on the item
        itemsBox.addActionListener(e -> {
            slider.setMaximum(5 + itemsBox.getSelectedIndex() * 2);
        });

        JButton addAnother = new JButton("Add Another Item");
        addAnother.setBounds(30, 150, 140, 35);
        addAnother.setBackground(new Color(90, 110, 140));
        addAnother.setForeground(Color.WHITE);
        addAnother.setFocusPainted(false);
        this.add(addAnother);

        JButton done = new JButton("Done");
        done.setBounds(190, 150, 120, 35);
        done.setBackground(new Color(58, 80, 120));
        done.setForeground(Color.WHITE);
        done.setFocusPainted(false);
        this.add(done);

        addAnother.addActionListener(e -> {
            String item = (String) itemsBox.getSelectedItem();
            parent.selectedItems.put(item, slider.getValue());
            slider.setValue(0);
        });

        done.addActionListener(e -> {
            String item = (String) itemsBox.getSelectedItem();
            parent.selectedItems.put(item, slider.getValue());

            parent.updateItemsField();

            JOptionPane.showMessageDialog(
                    this,
                    parent.selectedItems.toString(),
                    "Selected Items",
                    JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
        });

        this.setVisible(true);
    }
}
