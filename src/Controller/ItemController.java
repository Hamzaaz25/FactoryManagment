package Controller;

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

    public ItemController(ItemRepository itemRepository , BaseFrame bf , User user){
        this.itemRepository = itemRepository;
        this.baseFrame = bf;
        view = new ItemsView(user.getUsername(),itemRepository.getList());
        this.baseFrame.setVisible(true);
        bf.switchContent(view , "Items");

        view.getCategory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = view.getCategory().getSelectedItem().toString();
                if(selected.equals("All")){
                    view.setActiveCards(itemRepository.getList());
                    view.setCurrentItems(itemRepository.getList());
                }else{
                    ArrayList<Item> base = itemRepository.getList().stream().filter(item -> item.getType() == MaterialType.valueOf(selected)).collect(Collectors.toCollection(ArrayList :: new));
                    view.setActiveCards(base);
                    view.setCurrentItems(base);
                }
                view.updateCards();

                bf.switchContent(view , "Items");

            }
        });
        for(ItemBtn pb :view.getProductButtons()){
            pb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Item item =  itemRepository.getByName(pb.getTextName());

                    ThingDetails details = new ThingDetails(item.getName() , String.valueOf(item.getPrice()) , "des" , new ImageIcon(item.getImage()) , item.getAvailableQuantity());
                    baseFrame.switchContent( details ,item.getName());
                    details.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            baseFrame.switchContent(view , "Items");
                        }
                    });
                }
            });
        }

    }
}
