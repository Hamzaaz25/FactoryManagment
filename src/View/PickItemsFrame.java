package View;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PickItemsFrame extends JFrame {

    JComboBox<String> itemsBox;
    JSlider slider;
    AddProduct parent;
    JButton addAnother;
    JButton done;


    public PickItemsFrame(AddProduct parent , String[] items) {
        this.parent = parent;
        this.setTitle("Pick Items");
        this.setSize(350, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(38, 55, 85));
        this.setResizable(false);

        slider = new JSlider(0, 20, 0);
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

        addAnother = new JButton("Add Another Item");
        addAnother.setBounds(30, 150, 140, 35);
        addAnother.setBackground(new Color(90, 110, 140));
        addAnother.setForeground(Color.WHITE);
        addAnother.setFocusPainted(false);
        this.add(addAnother);

        done = new JButton("Done");
        done.setBounds(190, 150, 120, 35);
        done.setBackground(new Color(58, 80, 120));
        done.setForeground(Color.WHITE);
        done.setFocusPainted(false);
        this.add(done);


        this.setVisible(false);
    }

    public JComboBox<String> getItemsBox() {
        return itemsBox;
    }

    public JSlider getSlider() {
        return slider;
    }

    public JButton getDone() {
        return done;
    }

    public JButton getAddAnother() {
        return addAnother;
    }








    public void showDialog(HashMap <String ,Integer> recipe){

        JOptionPane.showMessageDialog(
                this
                ,recipe.toString(),
                "Selected Items",
                JOptionPane.INFORMATION_MESSAGE);

}


}
