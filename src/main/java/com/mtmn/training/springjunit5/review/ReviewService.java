package com.mtmn.training.springjunit5.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository repository;

    public Optional<Review> findById(String id) {
        return repository.findById(id);
    }

    public Optional<Review> findByProductId(Integer productId) {
        return repository.findByProductId(productId);
    }

    public List<Review> findAll() {
        return repository.findAll();
    }

    public Review save(Review review) {
        review.setVersion(1);
        return repository.save(review);
    }

    public Review update(Review review) {
        review.setVersion(review.getVersion() + 1);
        return repository.save(review);
    }

    public void delete(String id){
        repository.deleteById(id);
    }
}
