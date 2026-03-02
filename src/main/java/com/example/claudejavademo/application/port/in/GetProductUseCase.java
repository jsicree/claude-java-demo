package com.example.claudejavademo.application.port.in;

import com.example.claudejavademo.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface GetProductUseCase {

    Product getProduct(UUID id);

    List<Product> getAllProducts();
}