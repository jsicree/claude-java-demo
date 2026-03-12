/**
 * Input port for retrieving products by ID or listing all products.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.application.port.in;

import com.example.claudejavademo.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface GetProductUseCase {

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the product ID
     * @return the matching {@code Product}
     * @throws com.example.claudejavademo.domain.exception.ProductNotFoundException if no product with the given ID exists
     */
    Product getProduct(UUID id);

    /**
     * Returns all products.
     *
     * @return a list of all {@code Product} instances; empty if none exist
     */
    List<Product> getAllProducts();
}
