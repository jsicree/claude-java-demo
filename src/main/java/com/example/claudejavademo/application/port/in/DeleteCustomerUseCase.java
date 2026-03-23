/**
 * Input port for deleting an existing customer.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-22
 */
package com.example.claudejavademo.application.port.in;

import java.util.UUID;

public interface DeleteCustomerUseCase {

    /**
     * Deletes the customer with the given identifier.
     *
     * @param id the customer ID
     * @throws com.example.claudejavademo.domain.exception.CustomerNotFoundException if no customer with the given ID exists
     */
    void deleteCustomer(UUID id);
}
