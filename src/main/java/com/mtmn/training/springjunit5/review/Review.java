package com.mtmn.training.springjunit5.review;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection="Reviews")
public class Review {

    private String id;
    private Integer productId;
    private Integer version;

    private List<ReviewEntries> entries = new ArrayList<>();

    public Review(Integer productId) {
        this.productId = productId;
    }

    public Review(String id, Integer productId) {
        this.id = id;
        this.productId = productId;
    }

}
