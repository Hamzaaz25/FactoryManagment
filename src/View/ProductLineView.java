package View;

import Model.ProductLine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ProductLineView extends JPanel {

    private final JPanel container = new JPanel(new GridLayout(0, 2, 60, 60));
    private JComboBox<String> filterCombo;

    public ProductLineView(ArrayList<ProductLine> productLines,String[] filterOptions) {


        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 20));
        topPanel.setOpaque(false);

        filterCombo = new JComboBox<>(filterOptions);

        filterCombo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        filterCombo.setBackground(new Color(147, 177, 181));
        filterCombo.setForeground(new Color(11, 46, 51));
        filterCombo.setPreferredSize(new Dimension(220, 35));

        filterCombo.setBorder(BorderFactory.createLineBorder(new Color(11, 46, 51), 1));

        topPanel.add(filterCombo);
        add(topPanel, BorderLayout.NORTH);

        container.setOpaque(false);
        container.setBorder(new EmptyBorder(20, 100, 40, 100));

        container.setOpaque(false);
        container.setBorder(new EmptyBorder(20, 100, 40, 100));



        add(container, BorderLayout.CENTER);
    }


    public void renderProductLines(ArrayList<ProductLine> list , Consumer<ProductLine> lineConsumer) {
        container.removeAll();

        for (ProductLine pl : list) {
            container.add(createProductCard(pl, lineConsumer));
        }

        container.revalidate();
        container.repaint();
    }

    private JPanel createProductCard(ProductLine pl , Consumer<ProductLine> onLineClicked) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        wrapper.setOpaque(false);

        JPanel card = new JPanel(new BorderLayout(0,15));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.setBackground(new Color(94, 142, 180));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.setPreferredSize(new Dimension(380, 400));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(0, 0, 20, 0)
        ));

        //Image
        String imagePath;
        switch (pl.getStatus()) {
            case Active -> imagePath = "./assets/productlinee.png";
            case Maintenance -> imagePath = "./assets/stopedline.png";
            default -> imagePath = "./assets/brokenline.png";
        }

        JLabel lblImage = new JLabel();
        ImageIcon icon = new ImageIcon(imagePath);

        if (icon.getIconWidth() != -1) {
            Image scaledImg = icon.getImage().getScaledInstance(380, 220, Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(scaledImg));
        } else {
            lblImage.setText("Image Not Found");
            lblImage.setForeground(Color.RED);
        }

        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblImage, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(0, 25, 10, 25));

        // Name
        JLabel lblName = new JLabel(pl.getName());
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblName.setForeground(new Color(44, 62, 80));
        lblName.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ID
        JLabel lblId = new JLabel("ID: #" + String.format("%03d", pl.getId()));
        lblId.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lblId.setForeground(Color.WHITE);
        lblId.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Status
        JLabel lblStatus = new JLabel("Status: " + pl.getStatus().toString());
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStatus.setAlignmentX(Component.LEFT_ALIGNMENT);

        switch (pl.getStatus()) {
            case Active -> lblStatus.setForeground(Color.GREEN);
            case Idle -> lblStatus.setForeground(new Color(255, 193, 7));
            case Maintenance -> lblStatus.setForeground(new Color(220, 53, 69));
        }

        infoPanel.add(lblName);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(lblId);
        infoPanel.add(Box.createVerticalStrut(25));
        infoPanel.add(lblStatus);

        card.add(infoPanel, BorderLayout.CENTER);

        // Add click listener for the whole card
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onLineClicked.accept(pl);
            }
        });

        wrapper.add(card);
        return wrapper;
    }


}
