package Controller;

import Model.Product;
import Repository.ProductRepository;
import Model.User;
import View.BaseFrame;
import View.ProductBtn;
import View.ProductsView;
import View.ThingDetails;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductController {
    ProductsView view;
    private final ProductRepository productRepository;
    BaseFrame baseFrame;



    public ProductController(ProductRepository productRepository , BaseFrame bf  , User user) {
        this.productRepository = productRepository;
        this.baseFrame = bf;
        view = new ProductsView(user.getUsername(),productRepository.getList());
        this.baseFrame.setVisible(true);
        bf.switchContent(view , "Products");
        for(ProductBtn pb :view.getProductButtons()){
            pb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                       Product product =  productRepository.getByName(pb.getTextName());

                       ThingDetails details = new ThingDetails(product.getName() , String.valueOf(product.getPrice()) , "des" , new ImageIcon(product.getImage()) , product.getAmount());
                       baseFrame.switchContent( details ,product.getName());
                       details.addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               baseFrame.switchContent(view , "Products");
                           }
                       });
                }
            });
        }

    }





}
