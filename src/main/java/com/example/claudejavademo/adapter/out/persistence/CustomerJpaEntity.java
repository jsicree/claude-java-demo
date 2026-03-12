/**
 * JPA entity mapping the {@code customers} table.
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

@Entity
@Table(name = "customers")
class CustomerJpaEntity implements Persistable<String> {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Transient
    private boolean isNew;

    protected CustomerJpaEntity() {}

    /**
     * Constructs a CustomerJpaEntity with all fields.
     *
     * @param id    the customer ID as a string
     * @param name  the customer name
     * @param email the customer email address
     * @param isNew {@code true} if this entity has not yet been persisted
     */
    CustomerJpaEntity(String id, String name, String email, boolean isNew) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isNew = isNew;
    }

    /**
     * Returns the entity ID.
     *
     * @return the customer ID as a string
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
     * Returns the customer name.
     *
     * @return the customer name
     */
    String getName()  { return name; }

    /**
     * Returns the customer email address.
     *
     * @return the customer email
     */
    String getEmail() { return email; }
}
