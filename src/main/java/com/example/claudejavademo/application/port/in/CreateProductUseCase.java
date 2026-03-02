package com.example.claudejavademo.application.port.in;

import com.example.claudejavademo.domain.model.Product;

import java.math.BigDecimal;

public interface CreateProductUseCase {

    Product createProduct(String name, BigDecimal price);
}