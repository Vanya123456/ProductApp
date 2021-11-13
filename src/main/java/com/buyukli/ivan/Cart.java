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
    private List<Product> products;
    private ProductService productService;

    public Cart() {
        products = new ArrayList<>();
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(Long id) {
        products.add(productService.getProductById(id));
    }

    public void deleteProduct(Long id) throws RuntimeException{
        Product product = productService.getProductById(id);
            if (products.contains(product)) {
                products.remove(product);
            }else {
                throw new RuntimeException();
            }
    }

    public List<Product> getAllProductsInCart(){
        return products;
    }
}
