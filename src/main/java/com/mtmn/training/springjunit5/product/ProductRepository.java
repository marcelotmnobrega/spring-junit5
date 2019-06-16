package com.mtmn.training.springjunit5.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {

    private static final Logger logger = LogManager.getLogger(ProductRepository.class);

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    public Optional<Product> findById(Integer id) {
        try {
            Product product = jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?",
                    new Object[] {id},
                    (rs, rowNum) -> {
                        Product p = new Product();
                        p.setId(rs.getInt("id"));
                        p.setName(rs.getString("name"));
                        p.setQuantity(rs.getInt("quantity"));
                        p.setVersion(rs.getInt("version"));
                        return p;
                    });
            return Optional.of(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Product> findAll() {

        return jdbcTemplate.query("SELECT * FROM products",
                (rs, rowNum) -> {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setQuantity(rs.getInt("quantity"));
                    p.setVersion(rs.getInt("version"));
                    return p;
                });
    }

    public boolean update(Product product) {
        int rowsUpdated = jdbcTemplate.update("UPDATE products SET name = ?, quantity = ?, version = ? WHERE id = ?",
                product.getName(), product.getQuantity(), product.getVersion(), product.getId());
        return rowsUpdated == 1;
    }

    public Product save(Product product) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", product.getName());
        params.put("quantity", product.getQuantity());
        params.put("version", product.getVersion());

        Number newId = simpleJdbcInsert.executeAndReturnKey(params);

        logger.info("Inserting product into database, generated key: {}", newId);

        product.setId((Integer)newId);

        return product;
    }

    public boolean delete(Integer id) {
        int rowsDeleted = jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
        return rowsDeleted == 1;
    }
}
