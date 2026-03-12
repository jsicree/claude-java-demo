/**
 * Thrown when a requested customer cannot be found.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.domain.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception for the given customer ID.
     *
     * @param id the ID of the customer that was not found
     */
    public CustomerNotFoundException(UUID id) {
        super("Customer not found: " + id);
    }
}
