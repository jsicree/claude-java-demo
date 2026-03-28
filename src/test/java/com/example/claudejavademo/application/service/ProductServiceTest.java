/**
 * Unit tests for ProductService, covering all use-case methods with a mocked repository.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-28
 */
package com.example.claudejavademo.application.service;

import com.example.claudejavademo.application.port.out.ProductRepository;
import com.example.claudejavademo.domain.exception.ProductNotFoundException;
import com.example.claudejavademo.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService service;

    /**
     * Creates a fresh ProductService backed by the mock repository before each test.
     */
    @BeforeEach
    void setUp() {
        service = new ProductService(productRepository);
    }

    /**
     * Verifies that createProduct delegates to the repository and returns the saved product.
     */
    @Test
    void createProduct_savesAndReturnsProduct() {
        Product saved = Product.create("Widget", new BigDecimal("9.99"));
        when(productRepository.save(any(Product.class))).thenReturn(saved);

        Product result = service.createProduct("Widget", new BigDecimal("9.99"));

        assertThat(result).isEqualTo(saved);
        verify(productRepository).save(any(Product.class));
    }

    /**
     * Verifies that getProduct returns the product when it exists in the repository.
     */
    @Test
    void getProduct_existingId_returnsProduct() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Widget", new BigDecimal("9.99"));
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product result = service.getProduct(id);

        assertThat(result).isEqualTo(product);
    }

    /**
     * Verifies that getProduct throws ProductNotFoundException when no product has the given ID.
     */
    @Test
    void getProduct_unknownId_throwsProductNotFoundException() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getProduct(id))
                .isInstanceOf(ProductNotFoundException.class);
    }

    /**
     * Verifies that getAllProducts delegates to the repository and returns all products.
     */
    @Test
    void getAllProducts_returnsAllFromRepository() {
        List<Product> products = List.of(
                Product.create("A", BigDecimal.ONE),
                Product.create("B", BigDecimal.TEN)
        );
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = service.getAllProducts();

        assertThat(result).isEqualTo(products);
    }

    /**
     * Verifies that deleteProduct verifies existence then calls deleteById on the repository.
     */
    @Test
    void deleteProduct_existingId_deletesFromRepository() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Widget", BigDecimal.ONE);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        service.deleteProduct(id);

        verify(productRepository).deleteById(id);
    }

    /**
     * Verifies that deleteProduct throws ProductNotFoundException when no product has the given ID.
     */
    @Test
    void deleteProduct_unknownId_throwsProductNotFoundException() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteProduct(id))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
