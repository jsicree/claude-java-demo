/**
 * Integration tests for JpaCustomerRepository using a full Spring Boot context with H2.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-28
 */
package com.example.claudejavademo.adapter.out.persistence;

import com.example.claudejavademo.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaCustomerRepositoryTest {

    @Autowired
    private SpringDataCustomerRepository springRepo;

    private JpaCustomerRepository repo;

    /**
     * Clears persisted customers and instantiates a fresh JpaCustomerRepository before each test.
     */
    @BeforeEach
    void setUp() {
        springRepo.deleteAll();
        repo = new JpaCustomerRepository(springRepo);
    }

    /**
     * Verifies that save persists a customer and returns the same domain object.
     */
    @Test
    void save_persistsCustomerAndReturnsIt() {
        Customer customer = Customer.register("Alice", "alice@example.com");

        Customer result = repo.save(customer);

        assertThat(result.getId()).isEqualTo(customer.getId());
        assertThat(result.getName()).isEqualTo("Alice");
        assertThat(result.getEmail()).isEqualTo("alice@example.com");
    }

    /**
     * Verifies that findById returns the customer when it exists.
     */
    @Test
    void findById_existingId_returnsCustomer() {
        Customer customer = Customer.register("Alice", "alice@example.com");
        repo.save(customer);

        Optional<Customer> result = repo.findById(customer.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(customer.getId());
        assertThat(result.get().getName()).isEqualTo("Alice");
        assertThat(result.get().getEmail()).isEqualTo("alice@example.com");
    }

    /**
     * Verifies that findById returns empty when no customer has the given ID.
     */
    @Test
    void findById_unknownId_returnsEmpty() {
        Optional<Customer> result = repo.findById(UUID.randomUUID());

        assertThat(result).isEmpty();
    }

    /**
     * Verifies that findByEmail returns the customer when one exists with that email.
     */
    @Test
    void findByEmail_existingEmail_returnsCustomer() {
        Customer customer = Customer.register("Alice", "alice@example.com");
        repo.save(customer);

        Optional<Customer> result = repo.findByEmail("alice@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("alice@example.com");
    }

    /**
     * Verifies that findByEmail returns empty when no customer has the given email.
     */
    @Test
    void findByEmail_unknownEmail_returnsEmpty() {
        Optional<Customer> result = repo.findByEmail("nobody@example.com");

        assertThat(result).isEmpty();
    }

    /**
     * Verifies that findAll returns all persisted customers.
     */
    @Test
    void findAll_returnsAllCustomers() {
        repo.save(Customer.register("Alice", "alice@example.com"));
        repo.save(Customer.register("Bob", "bob@example.com"));

        List<Customer> result = repo.findAll();

        assertThat(result).hasSize(2);
    }

    /**
     * Verifies that deleteById removes the customer so it can no longer be found.
     */
    @Test
    void deleteById_removesCustomer() {
        Customer customer = Customer.register("Alice", "alice@example.com");
        repo.save(customer);

        repo.deleteById(customer.getId());

        assertThat(repo.findById(customer.getId())).isEmpty();
    }
}
