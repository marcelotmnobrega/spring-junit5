package com.mtmn.training.springjunit5.review;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private static final Logger logger = LogManager.getLogger(ReviewController.class);

    private final ReviewService service;

    @GetMapping("/review/{id}")
    public ResponseEntity<?> getReview(@PathVariable String id) {
        return service.findById(id)
                .map(review -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(Integer.toString(review.getVersion()))
                                .location(new URI("/review/" + review.getId()))
                                .body(review);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/reviews")
    public Iterable<Review> getReviews(@RequestParam(value = "productId", required = false) Optional<String> productId) {
        return productId.map(pid -> {
            return service.findByProductId(Integer.valueOf(pid))
                    .map(Arrays::asList)
                    .orElseGet(ArrayList::new);
        }).orElse(service.findAll());
    }

    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        logger.info("Creating new review for product id: {}, {}", review.getProductId(), review);

        //Set the date to any entries to now since we're creating a review now.
        review.getEntries().forEach(entry -> entry.setDate(new Date()));

        Review newReview = service.save(review);
        logger.info("Saved review: {}", newReview);

        try {
            return ResponseEntity
                    .created(new URI("/review/" + newReview.getId()))
                    .eTag(Integer.toString(newReview.getVersion()))
                    .body(newReview);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/review/{productId}/entry")
    public ResponseEntity<Review> addEntryToReview(@PathVariable Integer productId, @RequestBody ReviewEntry entry) {
        logger.info("Add review entry for product id: {}, {}", productId, entry);

        //Retrieve the review for the specified productId; if there is no review, create a new one
        Review review = service.findByProductId(productId).orElseGet(() -> new Review(productId));

        //Add this new entry to the review
        entry.setDate(new Date());
        review.getEntries().add(entry);

        //Save the review
        Review updatedReview = service.save(review);
        logger.info("Updated review: {}", updatedReview);

        try {
            return ResponseEntity
                    .ok()
                    .location(new URI("/review/" + updatedReview.getId()))
                    .eTag(Integer.toString(updatedReview.getVersion()))
                    .body(updatedReview);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable String id) {

        logger.info("Deleting review with Id {}", id);

        //Get the existing product
        Optional<Review> existingReview = service.findById(id);

        //Delete the review if it exists in the database
        return existingReview.map(review -> {
            service.delete(review.getId());
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }



}
