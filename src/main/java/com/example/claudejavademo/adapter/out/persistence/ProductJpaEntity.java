/**
 * JPA entity mapping the {@code products} table.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
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

    /**
     * Constructs a ProductJpaEntity with all fields.
     *
     * @param id    the product ID as a string
     * @param name  the product name
     * @param price the product price
     * @param isNew {@code true} if this entity has not yet been persisted
     */
    ProductJpaEntity(String id, String name, BigDecimal price, boolean isNew) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isNew = isNew;
    }

    /**
     * Returns the entity ID.
     *
     * @return the product ID as a string
     */
    @Override
    public String getId() { return id; }

    /**
     * Returns whether this entity is new (not yet persisted).
     *
     * @return {@code true} if the entity has not been saved to the database
     */
    @Override
    public boolean isNew() { return isNew; }

    /**
     * Returns the product name.
     *
     * @return the product name
     */
    String getName()      { return name; }

    /**
     * Returns the product price.
     *
     * @return the product price
     */
    BigDecimal getPrice() { return price; }
}
