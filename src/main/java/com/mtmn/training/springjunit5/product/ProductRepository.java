package com.mtmn.training.springjunit5.product;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    public Optional<Product> findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    public List<Product> findAll() {
        throw new UnsupportedOperationException();
    }

    public Product save(Product product) {
        throw new UnsupportedOperationException();
    }

    public boolean update(Product product) {
        throw new UnsupportedOperationException();
    }

    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
