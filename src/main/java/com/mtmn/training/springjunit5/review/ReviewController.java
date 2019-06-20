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
    public Iterable<Review> getReviews(@RequestParam(value="productId", required = false) Optional<String> productId) {
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



}
