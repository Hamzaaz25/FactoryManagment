package View;

import Enums.Status;
import Model.ProductLine;   // adjust package if needed

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

import static Enums.Status.Active;

public class ManagerLineView extends JPanel {

    // ===== THEME =====
    private static final Color BG = new Color(38, 55, 85);
    private static final Color CARD = new Color(20, 33, 61);
    private static final Color ACCENT = new Color(120, 165, 200);
    private JButton add;
    private JPanel list;

    public ManagerLineView(
            ArrayList<ProductLine> productLines,
            Consumer<ProductLine> onEdit
    ) {
        setLayout(new BorderLayout());
        setBackground(BG);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        list = new JPanel();
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setBackground(BG);

        // BUILD CARDS DYNAMICALLY
        for (ProductLine line : productLines) {
            list.add(new ProductLineCard(line, onEdit));
            list.add(Box.createVerticalStrut(25));
        }

        list.add(Box.createVerticalStrut(10));
        list.add(createAddCard());

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(BG);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);
    }

    // ===== ADD BUTTON CARD =====
    private JPanel createAddCard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG);

        add = new JButton("＋ Add Product Line");
        add.setFont(add.getFont().deriveFont(Font.BOLD, 22f));
        add.setPreferredSize(new Dimension(0, 120));
        add.setBackground(CARD);
        add.setForeground(ACCENT);
        add.setFocusPainted(false);
        add.putClientProperty("JButton.buttonType", "roundRect");

        panel.add(add, BorderLayout.CENTER);
        return panel;
    }

    public JPanel buildCard(ProductLine line, Consumer<ProductLine> onEdit) {
        return new ProductLineCard(line, onEdit);
    }

    public void updateAllCards(ArrayList<ProductLine> productLines, Consumer<ProductLine> onEdit) {
        // Clear the current list panel
        list.removeAll();

        // Rebuild cards dynamically
        for (ProductLine line : productLines) {
            list.add(buildCard(line, onEdit));
            list.add(Box.createVerticalStrut(25));
        }

        // Add spacing and the "Add" button at the bottom
        list.add(Box.createVerticalStrut(10));
        list.add(createAddCard());

        // Refresh the panel
        list.revalidate();
        list.repaint();
    }


        // ===== REUSABLE CARD (VIEW ONLY) =====
    private static class ProductLineCard extends JPanel {

        public ProductLineCard(
                ProductLine productLine,
                Consumer<ProductLine> onEdit
        ) {

            setLayout(new GridBagLayout());
            setBackground(BG);
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(8, 10, 8, 10);
            c.fill = GridBagConstraints.HORIZONTAL;

            // ===== STATUS LABEL (TOP LEFT) =====
            JLabel statusLabel = new JLabel(productLine.getStatus().name());
            statusLabel.setOpaque(true);
            statusLabel.setBackground(getStatusColor(productLine.getStatus()));
            statusLabel.setForeground(Color.WHITE);
            statusLabel.setFont(statusLabel.getFont().deriveFont(Font.BOLD, 12f));
            statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
            statusLabel.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));

            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            add(statusLabel, c);

            // ===== ICON (LEFT) =====
            JButton iconBtn = new JButton();
            iconBtn.setIcon(loadIcon("./assets/factory.png", 64, 64));
            iconBtn.setPreferredSize(new Dimension(140, 140));
            iconBtn.setBackground(CARD);
            iconBtn.setFocusPainted(false);
            iconBtn.putClientProperty("JButton.buttonType", "roundRect");

            c.gridx = 0;
            c.gridy = 1;
            c.gridheight = 3;
            c.anchor = GridBagConstraints.NORTH;
            add(iconBtn, c);

            // ===== NAME =====
            JLabel nameLabel = new JLabel(productLine.getName());
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 20f));

            c.gridx = 1;
            c.gridy = 1;
            c.gridheight = 1;
            c.gridwidth = 2;
            c.weightx = 1;
            add(nameLabel, c);

            // ===== EDIT BUTTON (RIGHT) =====
            JButton editBtn = new JButton("Edit");
            editBtn.setBackground(CARD);
            editBtn.setForeground(ACCENT);
            editBtn.setFocusPainted(false);
            editBtn.putClientProperty("JButton.buttonType", "roundRect");
            editBtn.addActionListener(e -> onEdit.accept(productLine));

            c.gridx = 3;
            c.gridy = 1;
            c.gridwidth = 1;
            c.weightx = 0;
            c.anchor = GridBagConstraints.LINE_END;
            add(editBtn, c);

            // ===== NOTES =====
            JLabel notesLabel = new JLabel("Notes");
            notesLabel.setForeground(Color.WHITE);
            notesLabel.setFont(notesLabel.getFont().deriveFont(Font.BOLD, 15f));

            c.gridx = 1;
            c.gridy = 2;
            c.anchor = GridBagConstraints.LINE_START;
            add(notesLabel, c);

            JTextField notesField = new JTextField(productLine.getNotes());
            notesField.setEditable(false);
            notesField.setBackground(CARD);
            notesField.setForeground(Color.WHITE);
            notesField.setCaretColor(Color.WHITE);
            notesField.putClientProperty("JComponent.roundRect", true);

            c.gridx = 2;
            c.gridy = 2;
            c.gridwidth = 2;
            c.weightx = 1;
            add(notesField, c);

            // ===== PROGRESS BAR (SMALL) =====
            JProgressBar bar = new JProgressBar();
            bar.setValue(productLine.getProgress());
            bar.setForeground(ACCENT);
            bar.setBackground(CARD);
            bar.setBorderPainted(false);
            bar.putClientProperty("JProgressBar.roundRect", true);
            bar.setPreferredSize(new Dimension(0, 14));

            c.gridx = 1;
            c.gridy = 3;
            c.gridwidth = 3;
            c.weightx = 1;
            add(bar, c);
        }

        // Optional: color by status
        private static Color getStatusColor(Status status) {
            return switch (status) {
                case Active -> new Color(0, 200, 0);
                case Idle -> new Color(200, 200, 0);
                case Maintenance -> new Color(200, 80, 0);
            };
        }

        private static Icon loadIcon(String path, int w, int h) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
    }


    public JButton getAdd() {
        return add;
    }

}
