package com.mtmn.training.springjunit5.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Reviews")
public class Review {

    private String id;
    private Integer productId;
    private Integer version;

    private List<ReviewEntry> entries = new ArrayList<>();

    public Review(Integer productId) {
        this.productId = productId;
    }

    public Review(String id, Integer productId) {
        this.id = id;
        this.productId = productId;
    }

    public Review(String id, Integer productId, Integer version) {
        this.id = id;
        this.productId = productId;
        this.version = version;
    }
}
