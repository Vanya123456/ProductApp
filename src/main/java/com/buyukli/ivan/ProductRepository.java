package com.buyukli.ivan;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {
    @Getter
    @Setter
    private List<Product> products;

    @PostConstruct
    public List<Product> init(){
        return products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Cucumber"),
                new Product(2L, "Bread"),
                new Product(3L, "Vodka"),
                new Product(4L, "Tomato"),
                new Product(5L, "Cigarettes")
        ));
    }

    public Product findById(Long id){
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> findAllProducts(){
        return products;
    }
}
