/**
 * Domain entity representing a product in the catalog.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private final UUID id;
    private String name;
    private BigDecimal price;

    /**
     * Constructs a Product with the given ID, name, and price.
     *
     * @param id    the unique identifier
     * @param name  the product name
     * @param price the product price
     */
    public Product(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Factory method that creates a new Product with a randomly generated ID.
     *
     * @param name  the product name
     * @param price the product price
     * @return a new {@code Product} instance
     */
    public static Product create(String name, BigDecimal price) {
        return new Product(UUID.randomUUID(), name, price);
    }

    /**
     * Returns the unique identifier of this product.
     *
     * @return the product ID
     */
    public UUID getId()          { return id; }

    /**
     * Returns the name of this product.
     *
     * @return the product name
     */
    public String getName()      { return name; }

    /**
     * Returns the price of this product.
     *
     * @return the product price
     */
    public BigDecimal getPrice() { return price; }

    /**
     * Updates the price of this product.
     *
     * @param newPrice the new price; must be non-negative
     * @throws IllegalArgumentException if {@code newPrice} is null or negative
     */
    public void updatePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.price = newPrice;
    }
}
