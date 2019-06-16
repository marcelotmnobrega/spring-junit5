package com.mtmn.training.springjunit5.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        product.setVersion(1);
        return productRepository.save(product);
    }

    public boolean update(Product product) {
        return productRepository.update(product);
    }

    public boolean delete(Integer id) {
        return productRepository.delete(id);
    }
}
