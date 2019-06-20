package com.mtmn.training.springjunit5.review;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {
    Optional<Review> findByProductId(Integer productId);
}
