/**
 * Thrown when a requested product cannot be found.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.domain.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception for the given product ID.
     *
     * @param id the ID of the product that was not found
     */
    public ProductNotFoundException(UUID id) {
        super("Product not found: " + id);
    }
}
