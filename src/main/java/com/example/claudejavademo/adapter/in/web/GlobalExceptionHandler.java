/**
 * Global exception handler mapping domain exceptions to HTTP responses.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-22
 */
package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.domain.exception.CustomerAlreadyExistsException;
import com.example.claudejavademo.domain.exception.CustomerNotFoundException;
import com.example.claudejavademo.domain.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * Maps {@code ProductNotFoundException} to HTTP 404.
     *
     * @param ex the exception
     * @return a map containing the error message
     */
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, String> handleProductNotFound(ProductNotFoundException ex) {
        return Map.of("error", ex.getMessage());
    }

    /**
     * Maps {@code CustomerNotFoundException} to HTTP 404.
     *
     * @param ex the exception
     * @return a map containing the error message
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, String> handleCustomerNotFound(CustomerNotFoundException ex) {
        return Map.of("error", ex.getMessage());
    }

    /**
     * Maps {@code CustomerAlreadyExistsException} to HTTP 409.
     *
     * @param ex the exception
     * @return a map containing the error message
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    Map<String, String> handleCustomerAlreadyExists(CustomerAlreadyExistsException ex) {
        return Map.of("error", ex.getMessage());
    }
}
