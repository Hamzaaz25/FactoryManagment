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

    public ProductLineView(ArrayList<ProductLine> productLines) {


        setOpaque(false);
        setLayout(new BorderLayout());

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
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(94, 142, 180));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

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

        card.add(lblName);
        card.add(Box.createVerticalStrut(8));
        card.add(lblId);
        card.add(Box.createVerticalStrut(20));
        card.add(lblStatus);

        // Add click listener for the whole card
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onLineClicked.accept(pl);
            }
        });

        return card;
    }


}
