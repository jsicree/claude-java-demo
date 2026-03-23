/**
 * JPA-backed implementation of the {@code ProductRepository} output port.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.adapter.out.persistence;

import com.example.claudejavademo.application.port.out.ProductRepository;
import com.example.claudejavademo.domain.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class JpaProductRepository implements ProductRepository {

    private final SpringDataProductRepository spring;

    /**
     * Constructs a JpaProductRepository with the underlying Spring Data repository.
     *
     * @param spring the Spring Data JPA repository
     */
    JpaProductRepository(SpringDataProductRepository spring) {
        this.spring = spring;
    }

    /**
     * Persists the given product via JPA.
     *
     * @param product the product to save
     * @return the saved {@code Product}
     */
    @Override
    public Product save(Product product) {
        spring.save(toEntity(product, true));
        return product;
    }

    /**
     * Finds a product by its unique identifier.
     *
     * @param id the product ID
     * @return an {@code Optional} containing the product, or empty if not found
     */
    @Override
    public Optional<Product> findById(UUID id) {
        return spring.findById(id.toString()).map(JpaProductRepository::toDomain);
    }

    /**
     * Returns all persisted products.
     *
     * @return a list of all products
     */
    @Override
    public List<Product> findAll() {
        return spring.findAll().stream().map(JpaProductRepository::toDomain).toList();
    }

    /**
     * Deletes the product with the given identifier.
     *
     * @param id the product ID
     */
    @Override
    public void deleteById(UUID id) {
        spring.deleteById(id.toString());
    }

    /**
     * Converts a domain {@code Product} to a {@code ProductJpaEntity}.
     *
     * @param p     the domain product
     * @param isNew {@code true} if this is a new entity
     * @return the corresponding JPA entity
     */
    private static ProductJpaEntity toEntity(Product p, boolean isNew) {
        return new ProductJpaEntity(p.getId().toString(), p.getName(), p.getPrice(), isNew);
    }

    /**
     * Converts a {@code ProductJpaEntity} to a domain {@code Product}.
     *
     * @param e the JPA entity
     * @return the corresponding domain product
     */
    private static Product toDomain(ProductJpaEntity e) {
        return new Product(UUID.fromString(e.getId()), e.getName(), e.getPrice());
    }
}
