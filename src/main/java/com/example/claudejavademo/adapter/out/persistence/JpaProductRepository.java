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

    JpaProductRepository(SpringDataProductRepository spring) {
        this.spring = spring;
    }

    @Override
    public Product save(Product product) {
        spring.save(toEntity(product, true));
        return product;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return spring.findById(id.toString()).map(JpaProductRepository::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return spring.findAll().stream().map(JpaProductRepository::toDomain).toList();
    }

    private static ProductJpaEntity toEntity(Product p, boolean isNew) {
        return new ProductJpaEntity(p.getId().toString(), p.getName(), p.getPrice(), isNew);
    }

    private static Product toDomain(ProductJpaEntity e) {
        return new Product(UUID.fromString(e.getId()), e.getName(), e.getPrice());
    }
}
