package com.example.claudejavademo.application.port.out;

import com.example.claudejavademo.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(UUID id);

    List<Product> findAll();
}