package Controller;

import Enums.ItemStatus;
import Enums.MaterialType;
import Model.Item;
import Repository.ItemRepository;
import Model.User;
import View.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ItemController {
    private final ItemRepository itemRepository;
    private final BaseFrame baseFrame;
    private final ItemsView view;

    public ItemController(ItemRepository itemRepository, BaseFrame bf, User user) {
        this.itemRepository = itemRepository;
        this.baseFrame = bf;
        view = new ItemsView(user.getUsername(), itemRepository.getList());
        this.baseFrame.setVisible(true);
        bf.switchContent(view, "Items");

        view.getCategory().addActionListener(e -> {

            String selected = view.getCategory().getSelectedItem().toString();
            ArrayList<Item> filteredList;
            boolean isAllSelected = selected.equals("All");

            if (isAllSelected) {
                filteredList = itemRepository.getList();
                view.setActiveCardsWhenAll(filteredList);
            } else {
                filteredList = itemRepository.getList().stream()
                        .filter(item -> item.getType() == MaterialType.valueOf(selected))
                        .collect(Collectors.toCollection(ArrayList::new));
                view.setActiveCards(filteredList);
            }

            view.setCurrentItems(filteredList);

            attachListenersToCards();

            view.updateCards();

            bf.switchContent(view, "Items");
        });

        view.getAvailable().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = view.getAvailable().getSelectedItem().toString();
                ArrayList<Item> filteredList;
                boolean isAllSelected = selected.equals("All");

                if (isAllSelected) {
                    filteredList = itemRepository.getList();
                    view.setActiveCardsWhenAll(filteredList);
                } else {
                    filteredList = itemRepository.getList().stream()
                            .filter(item -> item.getStatus() == ItemStatus.valueOf(selected))
                            .collect(Collectors.toCollection(ArrayList::new));
                    view.setActiveCards(filteredList);
                }

                view.setCurrentItems(filteredList);

                attachListenersToCards();

                view.updateCards();

                bf.switchContent(view, "Items");
            }
        });

        attachListenersToCards();

        if (view.addCard != null) {
            view.addCard.addActionListener(e -> {

                view.addNewItem("New Item", "0.00", new ImageIcon("./assets/paint.png"), "des");
                attachListenersToCards();

                System.out.println("Add new item clicked!");
            });
        }
    }

    private void attachListenersToCards() {
        for (ItemBtn pb : view.getProductButtons()) {
            for (ActionListener al : pb.getActionListeners()) pb.removeActionListener(al);
            for (ActionListener al : pb.getDeleteBtn().getActionListeners())
                pb.getDeleteBtn().removeActionListener(al);

            pb.addActionListener(e -> {
                Item item = itemRepository.getByName(pb.getTextName());
                if (item != null) {
                    ThingDetails details = new ThingDetails(
                            item.getName(),
                            String.valueOf(item.getPrice()),
                            "Description...",
                            new ImageIcon(item.getImage()),
                            item.getAvailableQuantity()
                    );

                    baseFrame.switchContent(details, item.getName());


                    details.addActionListener(backEvt -> {
                                baseFrame.switchContent(view, "Items");
                            }
                    );
                }
            });
            pb.getDeleteBtn().addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {

                    view.removeItem(pb);


                    itemRepository.getList().removeIf(item -> item.getName().equals(pb.getTextName()));



                    JOptionPane.showMessageDialog(null, "Item deleted successfully!");
                }
            });
        }
    }
}
