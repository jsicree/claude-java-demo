package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.domain.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

record ProductResponse(UUID id, String name, BigDecimal price) {

    static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}