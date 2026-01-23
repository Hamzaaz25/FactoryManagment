package View;

import javax.swing.*;
import java.awt.*;

public class AddTask extends JPanel {

        public AddTask() {

            setLayout(null);
            setBackground(new Color(38, 55, 85));

            //The center
            int centerX = 450;
            int startY  = 260;

            int labelW = 140;
            int fieldW = 300;
            int comboW = 320;
            int rowH   = 44;
            int gapY   = 28;
            int gapX   = 40;

            //Client
            JLabel clientLabel = createLabel("Client");
            clientLabel.setBounds(centerX, startY, labelW, rowH);
            add(clientLabel);

            JTextField clientField = createField();
            clientField.setBounds(centerX + labelW + 10, startY, fieldW, rowH);
            add(clientField);

            //Quantity
            JLabel quantityLabel = createLabel("Quantity");
            quantityLabel.setBounds(centerX,
                    startY + rowH + gapY, labelW, rowH);
            add(quantityLabel);

            JTextField quantityField = createField();
            quantityField.setBounds(centerX + labelW + 10,
                    startY + rowH + gapY, fieldW, rowH);
            add(quantityField);

            //Product
            JLabel productLabel = createLabel("Product");
            productLabel.setBounds(centerX + labelW + fieldW + gapX,
                    startY, labelW, rowH);
            add(productLabel);

            String[] products = {
                    "Product 1", "Product 2", "Product 3",
                    "Product 4", "Product 5", "Product 6", "Product 7"
            };

            JComboBox<String> productBox = new JComboBox<>(products);
            productBox.setBounds(centerX + labelW + fieldW + gapX + labelW + 10,
                    startY, comboW, rowH);
            productBox.setFont(new Font("Arial", Font.PLAIN, 18));
            productBox.setBackground(new Color(20, 33, 61));
            productBox.setForeground(Color.WHITE);
            productBox.setBorder(BorderFactory.createLineBorder(
                    new Color(120, 165, 200), 2));
            add(productBox);

            //Progress Bar
            int progressY = startY + 2 * (rowH + gapY) + 10;
            int progressW = labelW + fieldW + gapX + labelW + comboW + 10;

            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            progressBar.setFont(new Font("Arial", Font.BOLD, 16));
            progressBar.setForeground(new Color(120, 165, 200));
            progressBar.setBackground(new Color(20, 33, 61));
            progressBar.setBorder(BorderFactory.createLineBorder(
                    new Color(120, 165, 200), 2));

            progressBar.setBounds(centerX, progressY, progressW, 34);
            add(progressBar);
        }

        //Labels styling
        private JLabel createLabel(String text) {
            JLabel lbl = new JLabel(text);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 20));
            return lbl;
        }

        //TextField styling
        private JTextField createField() {
            JTextField field = new JTextField();
            field.setFont(new Font("Arial", Font.PLAIN, 18));
            field.setBackground(new Color(20, 33, 61));
            field.setForeground(Color.WHITE);
            field.setCaretColor(Color.WHITE);
            field.setBorder(BorderFactory.createLineBorder(
                    new Color(120, 165, 200), 2));
            return field;
        }

}
