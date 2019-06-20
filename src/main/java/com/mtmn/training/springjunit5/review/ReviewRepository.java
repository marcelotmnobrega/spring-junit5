package com.mtmn.training.springjunit5.review;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository {


    public Optional<Review> findById(String id) {
        throw new RuntimeException();
    }

    public Optional<List<Review>> findByProductId(Integer productId) {
        throw new RuntimeException();
    }

    public List<Review> findAll() {
        throw new RuntimeException();
    }

    public Review save(Review review) {
        throw new RuntimeException();
    }
}
