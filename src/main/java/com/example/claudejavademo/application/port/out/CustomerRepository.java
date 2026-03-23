/**
 * Output port defining persistence operations for customers.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.application.port.out;

import com.example.claudejavademo.domain.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    /**
     * Persists the given customer.
     *
     * @param customer the customer to save
     * @return the saved {@code Customer}
     */
    Customer save(Customer customer);

    /**
     * Finds a customer by their unique identifier.
     *
     * @param id the customer ID
     * @return an {@code Optional} containing the customer, or empty if not found
     */
    Optional<Customer> findById(UUID id);

    /**
     * Finds a customer by their email address.
     *
     * @param email the email address to search for
     * @return an {@code Optional} containing the customer, or empty if not found
     */
    Optional<Customer> findByEmail(String email);

    /**
     * Returns all persisted customers.
     *
     * @return a list of all customers; empty if none exist
     */
    List<Customer> findAll();

    /**
     * Deletes the customer with the given identifier.
     *
     * @param id the customer ID
     */
    void deleteById(UUID id);
}
