package com.example.claudejavademo.application.service;

import com.example.claudejavademo.application.port.in.CreateProductUseCase;
import com.example.claudejavademo.application.port.in.GetProductUseCase;
import com.example.claudejavademo.application.port.out.ProductRepository;
import com.example.claudejavademo.domain.exception.ProductNotFoundException;
import com.example.claudejavademo.domain.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
class ProductService implements CreateProductUseCase, GetProductUseCase {

    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(String name, BigDecimal price) {
        Product product = Product.create(name, price);
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}