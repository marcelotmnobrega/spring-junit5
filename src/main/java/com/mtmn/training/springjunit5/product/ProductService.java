package com.mtmn.training.springjunit5.product;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    public Optional<Product> findById(int i) {
        throw new UnsupportedOperationException();
    }

    public Iterable<Product> findAll() {
        throw new UnsupportedOperationException();
    }

    public Product save(Product product) {
        throw new UnsupportedOperationException();
    }

    public boolean update(Product p) {
        throw new UnsupportedOperationException();
    }
}
