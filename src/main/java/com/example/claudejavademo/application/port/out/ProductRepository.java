/**
 * Output port defining persistence operations for products.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.application.port.out;

import com.example.claudejavademo.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    /**
     * Persists the given product.
     *
     * @param product the product to save
     * @return the saved {@code Product}
     */
    Product save(Product product);

    /**
     * Finds a product by its unique identifier.
     *
     * @param id the product ID
     * @return an {@code Optional} containing the product, or empty if not found
     */
    Optional<Product> findById(UUID id);

    /**
     * Returns all persisted products.
     *
     * @return a list of all products; empty if none exist
     */
    List<Product> findAll();
}
