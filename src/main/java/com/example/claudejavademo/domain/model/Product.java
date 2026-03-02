package com.example.claudejavademo.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private final UUID id;
    private String name;
    private BigDecimal price;

    public Product(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Product create(String name, BigDecimal price) {
        return new Product(UUID.randomUUID(), name, price);
    }

    public UUID getId()          { return id; }
    public String getName()      { return name; }
    public BigDecimal getPrice() { return price; }

    public void updatePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.price = newPrice;
    }
}