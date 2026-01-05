package Controller;

import Model.ProductRepository;
import Model.User;
import View.BaseFrame;
import View.ProductsView;

public class ProductController {
    ProductsView view;
    private final ProductRepository productRepository;
    BaseFrame baseFrame;



    public ProductController(ProductRepository productRepository , BaseFrame bf  , User user) {
        this.productRepository = productRepository;
        view = new ProductsView(user.getUsername(),productRepository.getList());
        bf.switchContent(view , "Products");
    }





}
