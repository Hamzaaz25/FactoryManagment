package View;

import Model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class ItemBtn extends JButton {
    String name;
    JLabel Namelbl;
    String price;
    JLabel Pricelbl;
    String description;
    JLabel Desclbl;
    JButton Deletebtn ;
    JButton Editbtn;

    public String getTextName() {
        return Namelbl.getText();
    }

    public ItemBtn(Item item , ImageIcon icon, String description ,  Consumer<Item> onSelect,
                   Consumer<Item> onDelete , Consumer<Item> onEdit) {
        super.putClientProperty("JButton.buttonType", "roundRect"); // rounded buttons
        super.putClientProperty("JComponent.hoverBackground", Color.CYAN);

        this.name=item.getName();
        this.price=String.valueOf(item.getPrice());
        this.description=description;
        this.setLayout(new BorderLayout(5, 5));
        this.setPreferredSize(new Dimension(180, 220));
        this.setContentAreaFilled(true);
        this.setOpaque(false);
        this.setBorder(new javax.swing.border.EmptyBorder(10, 10, 10, 10));
        this.setBorderPainted(true);
        this.setFocusPainted(false);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setIconTextGap(15);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(new Color(94, 142, 180));


        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(74, 120, 160));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(94, 142, 180));
            }
        });


        JLabel Imagelbl = new JLabel();
        Image img = icon.getImage().getScaledInstance(180, 80, Image.SCALE_SMOOTH);
        Imagelbl.setIcon(new ImageIcon(img));
        Imagelbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(Imagelbl, BorderLayout.NORTH);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        Namelbl = new JLabel(name);
        Namelbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        Namelbl.setForeground(Color.black);
        Namelbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        Desclbl = new JLabel(description);
        Desclbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        Desclbl.setForeground(Color.WHITE);
        Desclbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(Namelbl);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(Desclbl);
        this.add(textPanel, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        Pricelbl = new JLabel(price);
        Pricelbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        Pricelbl.setForeground(Color.WHITE);

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        actionsPanel.setOpaque(false);

         Editbtn = new JButton();
        ImageIcon editIcon = new ImageIcon("./assets/pen.png");
        Image scaledImg = editIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Editbtn.setIcon(new ImageIcon(scaledImg));
        Editbtn.setPreferredSize(new Dimension(38, 38));
        styleActionIcon(Editbtn, new Color(240, 240, 240));


        Deletebtn = new JButton();
        ImageIcon DeleteIcon = new ImageIcon("./assets/delete.png");
        Image scaleImg = DeleteIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Deletebtn.setIcon(new ImageIcon(scaleImg));
        Deletebtn.setPreferredSize(new Dimension(38, 38));
        styleActionIcon(Deletebtn, new Color(240, 240, 240));



        this.Deletebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDelete.accept(item);
            }
        });

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelect.accept(item);
            }
        });

        this.Editbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEdit.accept(item);
            }
        });

//        Deletebtn.addActionListener(e -> {
//
//            int response = JOptionPane.showConfirmDialog(
//                    null,
//                    "Are you sure you want to delete this item?",
//                    "Delete Confirmation",
//                    JOptionPane.YES_NO_OPTION,
//                    JOptionPane.WARNING_MESSAGE
//            );
//
//            if (response == JOptionPane.YES_OPTION) {
//                Container parent = this.getParent();
//                parent.remove(this);
//                parent.revalidate();
//                parent.repaint();
//
//                JOptionPane.showMessageDialog(
//                        null,
//                        "Model.Product has been deleted successfully!",
//                        "Success",
//                        JOptionPane.INFORMATION_MESSAGE
//                );
//            }
//        });
        actionsPanel.add(Editbtn);
        actionsPanel.add(Deletebtn);

        bottomPanel.add(Pricelbl, BorderLayout.WEST);
        bottomPanel.add(actionsPanel, BorderLayout.EAST);


        this.add(bottomPanel, BorderLayout.SOUTH);


    }

    private void styleActionIcon(JButton btn, Color bg) {
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setBackground(bg);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.putClientProperty("JComponent.arc", 15);
        btn.setBorder(BorderFactory.createLineBorder(bg, 2));

    }
    public JButton getDeleteBtn() {
        return this.Deletebtn;
    }






}


