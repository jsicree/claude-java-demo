/**
 * Response body representing a customer resource.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.domain.model.Customer;

import java.util.UUID;

record CustomerResponse(UUID id, String name, String email) {

    /**
     * Creates a {@code CustomerResponse} from a domain {@code Customer}.
     *
     * @param customer the source domain customer
     * @return a new {@code CustomerResponse}
     */
    static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }
}
