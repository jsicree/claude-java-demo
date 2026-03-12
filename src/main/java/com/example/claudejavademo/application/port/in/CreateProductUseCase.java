/**
 * Input port for creating a new product.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.application.port.in;

import com.example.claudejavademo.domain.model.Product;

import java.math.BigDecimal;

public interface CreateProductUseCase {

    /**
     * Creates a new product with the given name and price.
     *
     * @param name  the product name
     * @param price the product price
     * @return the newly created {@code Product}
     */
    Product createProduct(String name, BigDecimal price);
}
