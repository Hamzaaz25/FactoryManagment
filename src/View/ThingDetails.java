package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ThingDetails extends JPanel {
    String name;
    String price;
    String description;
    ImageIcon productIcon;
    int Amount;
    JButton btnAction;


    public ThingDetails( String name, String price, String description, ImageIcon productIcon, int Amount ) {
        this.name=name;
        this.price=price;
        this.description=description;
        this.productIcon=productIcon;
        this.Amount = Amount;

        this.setLayout(new GridLayout(1, 2));
        this.setOpaque(false);


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(true);
        infoPanel.setBackground(new Color(0, 35, 71));
        infoPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        JLabel lblName = new JLabel(name);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 45));
        lblName.setForeground(Color.WHITE);
        lblName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblPrice = new JLabel("Price: " + this.price);
        lblPrice.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        lblPrice.setForeground(new Color(200, 200, 200));
        lblPrice.setAlignmentX(Component.LEFT_ALIGNMENT);


        JLabel lblAmount = new JLabel("Available Stock: " + Amount);
        lblAmount.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblAmount.setForeground(new Color(144, 238, 144));
        lblAmount.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea txtDescription = new JTextArea(description);
        txtDescription.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        txtDescription.setForeground(Color.WHITE);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setEditable(false);
        txtDescription.setOpaque(false);
        txtDescription.setMaximumSize(new Dimension(500, 200));
        txtDescription.setAlignmentX(Component.LEFT_ALIGNMENT);


        btnAction = new JButton("OK");
        btnAction.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnAction.setBackground(new Color(55, 100, 145));
        btnAction.setForeground(Color.WHITE);
        btnAction.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAction.setAlignmentX(Component.LEFT_ALIGNMENT);


        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(lblName);
        infoPanel.add(Box.createVerticalStrut(40));
        infoPanel.add(lblPrice);
        infoPanel.add(Box.createVerticalStrut(40));
        infoPanel.add(lblAmount);
        infoPanel.add(Box.createVerticalStrut(40));
        infoPanel.add(txtDescription);
        infoPanel.add(Box.createVerticalStrut(40));
        infoPanel.add(btnAction);
        infoPanel.add(Box.createVerticalGlue());


        JLabel imageContainer = new JLabel();
        imageContainer.setOpaque(true);
        imageContainer.setHorizontalAlignment(SwingConstants.CENTER);


        Image img = productIcon.getImage();
        Image scaledImg = img.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        imageContainer.setIcon(new ImageIcon(scaledImg));
        imageContainer.setBackground(Color.white);


        this.add(infoPanel);
        this.add(imageContainer);
    }


    public void addActionListener(ActionListener a){
        this.btnAction.addActionListener(a);
    }
}
