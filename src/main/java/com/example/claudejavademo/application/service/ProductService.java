/**
 * Use-case implementation for product creation and retrieval.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.application.service;

import com.example.claudejavademo.application.port.in.CreateProductUseCase;
import com.example.claudejavademo.application.port.in.DeleteProductUseCase;
import com.example.claudejavademo.application.port.in.GetProductUseCase;
import com.example.claudejavademo.application.port.out.ProductRepository;
import com.example.claudejavademo.domain.exception.ProductNotFoundException;
import com.example.claudejavademo.domain.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
class ProductService implements CreateProductUseCase, GetProductUseCase, DeleteProductUseCase {

    private final ProductRepository productRepository;

    /**
     * Constructs a ProductService with the required repository.
     *
     * @param productRepository the output port for product persistence
     */
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a new product and persists it.
     *
     * @param name  the product name
     * @param price the product price
     * @return the newly created and persisted {@code Product}
     */
    @Override
    public Product createProduct(String name, BigDecimal price) {
        Product product = Product.create(name, price);
        return productRepository.save(product);
    }

    /**
     * Retrieves a product by ID.
     *
     * @param id the product ID
     * @return the matching {@code Product}
     * @throws ProductNotFoundException if no product with the given ID exists
     */
    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Returns all products.
     *
     * @return a list of all products; empty if none exist
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Deletes the product with the given ID.
     *
     * @param id the product ID
     * @throws ProductNotFoundException if no product with the given ID exists
     */
    @Override
    public void deleteProduct(UUID id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.deleteById(id);
    }
}
