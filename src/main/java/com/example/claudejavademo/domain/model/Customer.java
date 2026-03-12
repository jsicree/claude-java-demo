/**
 * Domain entity representing a registered customer.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.domain.model;

import java.util.UUID;

public class Customer {

    private final UUID id;
    private String name;
    private String email;

    /**
     * Constructs a Customer with the given ID, name, and email.
     *
     * @param id    the unique identifier
     * @param name  the customer name
     * @param email the customer email address
     */
    public Customer(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Factory method that registers a new Customer with a randomly generated ID.
     *
     * @param name  the customer name
     * @param email the customer email address
     * @return a new {@code Customer} instance
     */
    public static Customer register(String name, String email) {
        return new Customer(UUID.randomUUID(), name, email);
    }

    /**
     * Returns the unique identifier of this customer.
     *
     * @return the customer ID
     */
    public UUID getId()     { return id; }

    /**
     * Returns the name of this customer.
     *
     * @return the customer name
     */
    public String getName() { return name; }

    /**
     * Returns the email address of this customer.
     *
     * @return the customer email
     */
    public String getEmail() { return email; }
}
