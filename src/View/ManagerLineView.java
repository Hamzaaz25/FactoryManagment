package View;

import javax.swing.*;
import java.awt.*;

public class ManagerLineView extends JPanel {

//        public ManagerLineView() {
//            setLayout(null);
//            setBackground(new Color(38, 55, 85));
//
//            int startX = 320;
//            int startY = 130;
//
//            int boxSize = 150;
//
//            JPanel grid = new JPanel();
//            grid.setLayout(new GridLayout(0, 1, 0, 60));
//            grid.setBounds(startX, startY, 1500, 500);
//            grid.setBackground(new Color(38, 55, 85));
//
//            //Product Line 1
//            JPanel row1 = new JPanel(null);
//            row1.setBackground(new Color(38, 55, 85));
//
//            JButton plButton1 = new JButton("Product Line 1");
//            plButton1.setBounds(0, 20, boxSize, boxSize);
//            plButton1.setFont(new Font("Arial", Font.BOLD, 28));
//            plButton1.setBackground(new Color(20, 33, 61));
//            plButton1.setForeground(Color.WHITE);
//            plButton1.setFocusPainted(false);
//            row1.add(plButton1);
//
//            JLabel notesLabel1 = new JLabel("Notes:");
//            notesLabel1.setBounds(boxSize + 60, 30, 90, 40);
//            notesLabel1.setFont(new Font("Arial", Font.BOLD, 20));
//            notesLabel1.setForeground(Color.WHITE);
//            row1.add(notesLabel1);
//
//            JTextField notesField1 = new JTextField();
//            notesField1.setBounds(boxSize + 170, 30, 680, 50);
//            notesField1.setFont(new Font("Arial", Font.PLAIN, 20));
//            notesField1.setBackground(new Color(20, 33, 61));
//            notesField1.setForeground(Color.WHITE);
//            notesField1.setBorder(BorderFactory.createLineBorder(new Color(120, 165, 200), 2));
//            row1.add(notesField1);
//
//            JProgressBar bar1 = new JProgressBar();
//            bar1.setBounds(boxSize + 60, 20 + boxSize - 25, 790, 16);
//            bar1.setValue(40);
//            bar1.setForeground(new Color(120, 165, 200));
//            bar1.setBackground(new Color(20, 33, 61));
//            bar1.setBorderPainted(false);
//            row1.add(bar1);
//
//            grid.add(row1);
//
//            //Product Line 2
//            JPanel row2 = new JPanel(null);
//            row2.setBackground(new Color(38, 55, 85));
//
//            JButton plButton2 = new JButton("Product Line 2");
//            plButton2.setBounds(0, 20, boxSize, boxSize);
//            plButton2.setFont(new Font("Arial", Font.BOLD, 28));
//            plButton2.setBackground(new Color(20, 33, 61));
//            plButton2.setForeground(Color.WHITE);
//            plButton2.setFocusPainted(false);
//            row2.add(plButton2);
//
//            JLabel notesLabel2 = new JLabel("Notes:");
//            notesLabel2.setBounds(boxSize + 60, 30, 90, 40);
//            notesLabel2.setFont(new Font("Arial", Font.BOLD, 20));
//            notesLabel2.setForeground(Color.WHITE);
//            row2.add(notesLabel2);
//
//            JTextField notesField2 = new JTextField();
//            notesField2.setBounds(boxSize + 170, 30, 680, 50);
//            notesField2.setFont(new Font("Arial", Font.PLAIN, 20));
//            notesField2.setBackground(new Color(20, 33, 61));
//            notesField2.setForeground(Color.WHITE);
//            notesField2.setBorder(BorderFactory.createLineBorder(new Color(120, 165, 200), 2));
//            row2.add(notesField2);
//
//            JProgressBar bar2 = new JProgressBar();
//            bar2.setBounds(boxSize + 60, 20 + boxSize - 25, 790, 16);
//            bar2.setValue(40);
//            bar2.setForeground(new Color(120, 165, 200));
//            bar2.setBackground(new Color(20, 33, 61));
//            bar2.setBorderPainted(false);
//            row2.add(bar2);
//
//            grid.add(row2);
//
//            //Add Button
//            JButton addButton = new JButton("+");
//            addButton.setBounds(startX, startY + 2 * (190 + 60) + 20, boxSize, boxSize);
//            addButton.setFont(new Font("Arial", Font.BOLD, 52));
//            addButton.setBackground(new Color(20, 33, 61));
//            addButton.setForeground(new Color(120, 165, 200));
//            addButton.setFocusPainted(false);
//
//            add(grid);
//            add(addButton);
//        }


        private int boxSize = 150;
        private int rowHeight = 180;

        public ManagerLineView() {
            this.setLayout(null);
            this.setBackground(new Color(38, 55, 85));

            int startX = 320;
            int startY = 130;

            JPanel grid = new JPanel();
            grid.setLayout(new GridLayout(0, 1, 0, 40));
            grid.setBackground(new Color(38, 55, 85));

            //add rows
            grid.add(createRow("Product Line 1", 40));
            grid.add(createRow("Product Line 2", 60));
            grid.add(createRow("Product Line 3", 20));

            //scroll just as a test :)
            JScrollPane scroll = new JScrollPane(grid);
            scroll.setBounds(startX, startY, 1400, 600);
            scroll.setBorder(null);
            scroll.getVerticalScrollBar().setUnitIncrement(16);

            //add button
            JButton addBtn = new JButton("+");
            addBtn.setBounds(startX, startY + 620, boxSize, boxSize);
            addBtn.setFont(new Font("Arial", Font.BOLD, 52));
            addBtn.setBackground(new Color(20, 33, 61));
            addBtn.setForeground(new Color(120, 165, 200));
            addBtn.setFocusPainted(false);

            this.add(scroll);
            this.add(addBtn);
        }

        private JPanel createRow(String productLineText, int progressValue) {

            JPanel row = new JPanel(null);
            row.setPreferredSize(new Dimension(1500, rowHeight));
            row.setBackground(new Color(38, 55, 85));

            JButton productButton = new JButton(productLineText);
            productButton.setBounds(0, 20, boxSize, boxSize);
            productButton.setFont(new Font("Arial", Font.BOLD, 28));
            productButton.setBackground(new Color(20, 33, 61));
            productButton.setForeground(Color.WHITE);
            productButton.setFocusPainted(false);
            row.add(productButton);

            JLabel notesLabel = new JLabel("Notes:");
            notesLabel.setBounds(boxSize + 60, 30, 90, 40);
            notesLabel.setFont(new Font("Arial", Font.BOLD, 20));
            notesLabel.setForeground(Color.WHITE);
            row.add(notesLabel);

            JTextField notesField = new JTextField();
            notesField.setBounds(boxSize + 170, 30, 680, 50);
            notesField.setFont(new Font("Arial", Font.PLAIN, 20));
            notesField.setBackground(new Color(20, 33, 61));
            notesField.setForeground(Color.WHITE);
            notesField.setBorder(BorderFactory.createLineBorder(new Color(120, 165, 200), 2));
            row.add(notesField);

            JProgressBar bar = new JProgressBar();
            bar.setBounds(boxSize + 60, 20 + boxSize - 25, 790, 16);
            bar.setValue(progressValue);
            bar.setForeground(new Color(120, 165, 200));
            bar.setBackground(new Color(20, 33, 61));
            bar.setBorderPainted(false);
            row.add(bar);

            return row;
        }
}
