package com.example.claudejavademo.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
class ProductJpaEntity implements Persistable<String> {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal price;

    @Transient
    private boolean isNew;

    protected ProductJpaEntity() {}

    ProductJpaEntity(String id, String name, BigDecimal price, boolean isNew) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isNew = isNew;
    }

    @Override
    public String getId() { return id; }

    @Override
    public boolean isNew() { return isNew; }

    String getName()      { return name; }
    BigDecimal getPrice() { return price; }
}
