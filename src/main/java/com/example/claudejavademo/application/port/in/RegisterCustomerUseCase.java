/**
 * Input port for registering a new customer.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.application.port.in;

import com.example.claudejavademo.domain.model.Customer;

public interface RegisterCustomerUseCase {

    /**
     * Registers a new customer with the given name and email.
     *
     * @param name  the customer name
     * @param email the customer email address
     * @return the newly registered {@code Customer}
     * @throws com.example.claudejavademo.domain.exception.CustomerAlreadyExistsException if a customer with the given email already exists
     */
    Customer registerCustomer(String name, String email);
}
