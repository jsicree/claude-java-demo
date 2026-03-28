/**
 * Integration tests for JpaProductRepository using a full Spring Boot context with H2.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-28
 */
package com.example.claudejavademo.adapter.out.persistence;

import com.example.claudejavademo.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaProductRepositoryTest {

    @Autowired
    private SpringDataProductRepository springRepo;

    private JpaProductRepository repo;

    /**
     * Clears persisted products and instantiates a fresh JpaProductRepository before each test.
     */
    @BeforeEach
    void setUp() {
        springRepo.deleteAll();
        repo = new JpaProductRepository(springRepo);
    }

    /**
     * Verifies that save persists a product and returns the same domain object.
     */
    @Test
    void save_persistsProductAndReturnsIt() {
        Product product = Product.create("Widget", new BigDecimal("9.99"));

        Product result = repo.save(product);

        assertThat(result.getId()).isEqualTo(product.getId());
        assertThat(result.getName()).isEqualTo("Widget");
        assertThat(result.getPrice()).isEqualByComparingTo("9.99");
    }

    /**
     * Verifies that findById returns the product when it exists.
     */
    @Test
    void findById_existingId_returnsProduct() {
        Product product = Product.create("Widget", new BigDecimal("9.99"));
        repo.save(product);

        Optional<Product> result = repo.findById(product.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(product.getId());
        assertThat(result.get().getName()).isEqualTo("Widget");
    }

    /**
     * Verifies that findById returns empty when no product has the given ID.
     */
    @Test
    void findById_unknownId_returnsEmpty() {
        Optional<Product> result = repo.findById(UUID.randomUUID());

        assertThat(result).isEmpty();
    }

    /**
     * Verifies that findAll returns all persisted products.
     */
    @Test
    void findAll_returnsAllProducts() {
        repo.save(Product.create("A", BigDecimal.ONE));
        repo.save(Product.create("B", BigDecimal.TEN));

        List<Product> result = repo.findAll();

        assertThat(result).hasSize(2);
    }

    /**
     * Verifies that deleteById removes the product so it can no longer be found.
     */
    @Test
    void deleteById_removesProduct() {
        Product product = Product.create("Widget", new BigDecimal("9.99"));
        repo.save(product);

        repo.deleteById(product.getId());

        assertThat(repo.findById(product.getId())).isEmpty();
    }
}
