package com.example.claudejavademo.domain.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(UUID id) {
        super("Customer not found: " + id);
    }
}
