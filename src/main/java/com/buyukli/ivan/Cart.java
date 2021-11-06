package com.buyukli.ivan;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    @Setter
    @Getter
    private List<Product> cartOfProducts;
    private ProductService productService;

    public Cart() {
        cartOfProducts = new ArrayList<>();
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(Long id) {
        cartOfProducts.add(productService.getProductById(id));
    }

    public void deleteProduct(Long id) throws RuntimeException{
            if (cartOfProducts.contains(productService.getProductById(id))) {
                cartOfProducts.remove(productService.getProductById(id));
            }else {
                throw new RuntimeException();
            }
    }

    public List<Product> getAllProductsInCart(){
        return cartOfProducts;
    }
}
