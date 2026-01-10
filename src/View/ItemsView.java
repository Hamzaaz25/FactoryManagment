package View;

import Enums.ItemStatus;
import Enums.MaterialType;
import Model.Item;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Consumer;


public class ItemsView extends JPanel {

    private final JPanel container=new JPanel(new GridLayout(0, 3, 100, 100));
    private final JTextField searchText = new JTextField(" Search ...");
    private final JLabel noResults = new JLabel("No items match your search", SwingConstants.CENTER);
    private final JComboBox<String> category;
    private final JComboBox<String> available;
    private final JButton searchButton;
    private AddBtn addCard = new AddBtn();




    public ItemsView( ArrayList<Item> list , Consumer<Item> onSelect , Consumer<Item> onDelete , Consumer<Item> onEdit) {
       this.setOpaque(false);
       this.setLayout(new BorderLayout());

       JPanel topBar = new JPanel(new BorderLayout());
       topBar.setOpaque(false);


       category = new JComboBox<>(new String[]{"All", MaterialType.Wood.toString(), MaterialType.Fabric.toString(), MaterialType.Metal.toString()});
       JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
       categoryPanel.setOpaque(false);
       categoryPanel.add(category);

        available = new JComboBox<>(new String[]{"All", ItemStatus.Available.toString(), ItemStatus.BelowMinimum.toString()});
        JPanel availablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        availablePanel.setOpaque(false);
        availablePanel.add(available);



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
        ImageIcon searchIcon = new ImageIcon("./assets/searchh.png");
        Image scaledImage = searchIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        searchButton.setIcon(new ImageIcon(scaledImage));
        searchButton.setBackground(Color.white);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));



        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        searchBar.setOpaque(false);
        searchBar.add(searchText);
        searchBar.add(searchButton);
        topBar.add(categoryPanel, BorderLayout.WEST);
        topBar.add(availablePanel ,BorderLayout.EAST);
        topBar.add(searchBar, BorderLayout.CENTER);
        this.add(topBar, BorderLayout.NORTH);



        container.setBorder(new EmptyBorder(40, 150, 40, 150));
        container.setOpaque(false);

        renderItems(list , onSelect , onDelete , onEdit);



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



    public void renderItems(ArrayList<Item> list , Consumer<Item> onSelect, Consumer<Item> onDelete ,Consumer<Item> onEdit) {
    container.removeAll();
    for (Item item : list) {
        ItemBtn newCard = new ItemBtn(item, new ImageIcon(item.getImage()), "description" , onSelect ,onDelete , onEdit);
        container.add(newCard);
    }
        if (list.isEmpty()) {
            showNoResults();

        } else
            noResults.setVisible(false);


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





    public JComboBox<String> getCategory() {
        return category;
    }

    public JComboBox<String> getAvailable() {
        return available;
    }
    public JTextField getSearchText() {
        return this.searchText;
    }
    public JButton getSearchBtn(){
        return this.searchButton;
    }

    public JLabel getNoResults() {
        return noResults;
    }

    public JPanel getContainer() {
        return container;
    }
    public AddBtn getAddCard() {
        return addCard;
    }
}


