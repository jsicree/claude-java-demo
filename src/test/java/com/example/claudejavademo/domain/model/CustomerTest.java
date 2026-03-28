/**
 * Unit tests for the Customer domain model.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-28
 */
package com.example.claudejavademo.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    /**
     * Verifies that the register factory method generates a non-null UUID and stores name and email.
     */
    @Test
    void register_generatesUniqueIdAndStoresFields() {
        Customer customer = Customer.register("Alice", "alice@example.com");

        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getName()).isEqualTo("Alice");
        assertThat(customer.getEmail()).isEqualTo("alice@example.com");
    }

    /**
     * Verifies that two calls to register produce different UUIDs.
     */
    @Test
    void register_eachCallProducesUniqueId() {
        Customer a = Customer.register("Alice", "alice@example.com");
        Customer b = Customer.register("Bob", "bob@example.com");

        assertThat(a.getId()).isNotEqualTo(b.getId());
    }

    /**
     * Verifies that the full constructor stores all fields.
     */
    @Test
    void constructor_storesAllFields() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Carol", "carol@example.com");

        assertThat(customer.getId()).isEqualTo(id);
        assertThat(customer.getName()).isEqualTo("Carol");
        assertThat(customer.getEmail()).isEqualTo("carol@example.com");
    }
}
