import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemBtn extends JButton {
    String name;
    JLabel Namelbl;
    String price;
    JLabel Pricelbl;
    String description;
    JLabel Desclbl;
    @Override
    public String getName() {
        return Namelbl.getText();
    }

    public ItemBtn(String name, String price, ImageIcon icon, String description) {

        this.name=name;
        this.price=price;
        this.description=description;
        this.setLayout(new BorderLayout(5, 5));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setPreferredSize(new Dimension(180, 220));
        this.putClientProperty("JComponent.arc", 25);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        this.setContentAreaFilled(false);
        this.setBorderPainted(true);
        this.setFocusPainted(false);
        this.setOpaque(true);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setIconTextGap(15);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));



        Color defaultColor = new Color(94, 142, 180);
        Color hoverColor = new Color(245, 245, 245);
        Color borderColor = new Color(200, 200, 200);

        this.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        this.setBackground(defaultColor);
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setBorder(BorderFactory.createRaisedBevelBorder());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultColor);
                setBorder(BorderFactory.createLineBorder(borderColor, 1));
            }
        });

        JLabel labelImage = new JLabel();
        Image img = icon.getImage().getScaledInstance(180, 80, Image.SCALE_SMOOTH);
        labelImage.setIcon(new ImageIcon(img));
        labelImage.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(labelImage, BorderLayout.NORTH);

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

        JButton Editbtn = new JButton();
        ImageIcon editIcon = new ImageIcon("./assets/pen.png");
        Image scaledImg = editIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Editbtn.setIcon(new ImageIcon(scaledImg));
        Editbtn.setPreferredSize(new Dimension(38, 38));
        styleActionIcon(Editbtn, new Color(240, 240, 240));


        JButton Deletebtn = new JButton();
        ImageIcon DeleteIcon = new ImageIcon("./assets/delete.png");
        Image scaleImg = DeleteIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Deletebtn.setIcon(new ImageIcon(scaleImg));
        Deletebtn.setPreferredSize(new Dimension(38, 38));
        styleActionIcon(Deletebtn, new Color(240, 240, 240));

        Deletebtn.addActionListener(e -> {

            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this item?",
                    "Delete Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                Container parent = this.getParent();
                parent.remove(this);
                parent.revalidate();
                parent.repaint();

                JOptionPane.showMessageDialog(
                        null,
                        "Product has been deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
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

}


