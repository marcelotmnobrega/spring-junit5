package com.mtmn.training.springjunit5.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;
    private String name;
    private Integer quantity;
    private Integer version;

    public Product(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
