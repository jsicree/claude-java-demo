package com.example.claudejavademo.adapter.out.persistence;

import com.example.claudejavademo.application.port.out.ProductRepository;
import com.example.claudejavademo.domain.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryProductRepository implements ProductRepository {

    private final Map<UUID, Product> store = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(store.values());
    }
}