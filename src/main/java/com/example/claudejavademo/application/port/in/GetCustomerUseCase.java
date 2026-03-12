/**
 * Input port for retrieving customers by ID or listing all customers.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.application.port.in;

import com.example.claudejavademo.domain.model.Customer;

import java.util.List;
import java.util.UUID;

public interface GetCustomerUseCase {

    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param id the customer ID
     * @return the matching {@code Customer}
     * @throws com.example.claudejavademo.domain.exception.CustomerNotFoundException if no customer with the given ID exists
     */
    Customer getCustomer(UUID id);

    /**
     * Returns all customers.
     *
     * @return a list of all {@code Customer} instances; empty if none exist
     */
    List<Customer> getAllCustomers();
}
