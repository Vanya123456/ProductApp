package com.buyukli.ivan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String title;

    @Override
    public String toString() {
        return id + ": " + title;
    }
}
