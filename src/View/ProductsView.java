package View;
import Model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ProductsView extends JPanel{
    private final JPanel container;
    private JTextField searchText = new JTextField(" Search ...");;
    private JLabel noResults = new JLabel("No products match your search", SwingConstants.CENTER);
    private JButton searchButton;

    public ProductsView( ArrayList<Product> list , Consumer<Product> onSelect , Consumer<Product> onDelete , Consumer<Product> onEdit) {


        this.setOpaque(false);
        this.setLayout(new BorderLayout());


        searchText.setPreferredSize(new Dimension(250, 35));
        searchText.setForeground(Color.GRAY);
        searchText.setFont(new Font("Segoe UI", Font.ITALIC, 13));

        searchText.putClientProperty("JComponent.arc", 15);
        searchText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));





        noResults.setFont(new Font("Segoe UI", Font.BOLD, 18));
        noResults.setForeground(Color.black);
        noResults.setVisible(false);
        noResults.setIcon(new ImageIcon(("./assets/notFound.png")));
        noResults.setVerticalTextPosition(JLabel.BOTTOM);
        noResults.setHorizontalTextPosition(JLabel.CENTER);


         searchButton = new JButton();
        ImageIcon iconn = new ImageIcon("./assets/searchh.png");
        Image scaledImgg = iconn.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        searchButton.setIcon(new ImageIcon(scaledImgg));
        searchButton.setBackground(Color.white);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        searchBar.setOpaque(false);
        searchBar.add(searchText);
        searchBar.add(searchButton);

        this.add(searchBar,BorderLayout.NORTH);

        container=new JPanel(new GridLayout(0, 3, 100, 100));
        container.setBorder(new EmptyBorder(40, 150, 40, 150));
        container.setOpaque(false);

        renderProducts(list , onSelect , onDelete ,onEdit);


        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setOpaque(false);
        mainContentPanel.setFocusable(true);
        mainContentPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mainContentPanel.requestFocusInWindow();
            }
        });

        mainContentPanel.add(container);



        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setOpaque(false);

        wrapperPanel.add(container, BorderLayout.NORTH);

        
        JPanel rightContainer = new JPanel(new BorderLayout());
        rightContainer.setOpaque(false);
        rightContainer.add(container, BorderLayout.NORTH);

        this.add(rightContainer, BorderLayout.CENTER);
        this.setVisible(true);
    }



    AddBtn addCard = new AddBtn();



    public void renderProducts(ArrayList<Product> list , Consumer<Product> onSelect, Consumer<Product> onDelete , Consumer<Product> onEdit) {
        container.removeAll();
        for (Product product : list) {
            ProductBtn newCard = new ProductBtn(product, "description" , onSelect ,onDelete , onEdit);
            container.add(newCard);
        }
        if (list.isEmpty()) {
            showNoResults();
        } else
            getNoResults().setVisible(false);


        addCard.setVisible(true);

        container.add(addCard);
        container.revalidate();
        container.repaint();

    }


    private void showNoResults() {

        getContainer().removeAll();

        getContainer().setLayout(new BorderLayout());

        getNoResults().setVisible(true);
        getContainer().add(getNoResults(), BorderLayout.CENTER);

        if (addCard != null) {
            addCard.setVisible(false);
        }

        getContainer().revalidate();
        getContainer().repaint();
    }



    public JPanel getContainer() {
        return container;
    }

    public JLabel getNoResults() {
        return noResults;
    }

    public JTextField getSearchText() {
        return searchText;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
