/**
 * Thrown when attempting to register a customer with an email that is already in use.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.domain.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new exception for the given email address.
     *
     * @param email the duplicate email address
     */
    public CustomerAlreadyExistsException(String email) {
        super("Customer already exists with email: " + email);
    }
}
