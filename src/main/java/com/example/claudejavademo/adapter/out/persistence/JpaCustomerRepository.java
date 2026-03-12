/**
 * JPA-backed implementation of the {@code CustomerRepository} output port.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.adapter.out.persistence;

import com.example.claudejavademo.application.port.out.CustomerRepository;
import com.example.claudejavademo.domain.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class JpaCustomerRepository implements CustomerRepository {

    private final SpringDataCustomerRepository spring;

    /**
     * Constructs a JpaCustomerRepository with the underlying Spring Data repository.
     *
     * @param spring the Spring Data JPA repository
     */
    JpaCustomerRepository(SpringDataCustomerRepository spring) {
        this.spring = spring;
    }

    /**
     * Persists the given customer via JPA.
     *
     * @param customer the customer to save
     * @return the saved {@code Customer}
     */
    @Override
    public Customer save(Customer customer) {
        spring.save(toEntity(customer, true));
        return customer;
    }

    /**
     * Finds a customer by their unique identifier.
     *
     * @param id the customer ID
     * @return an {@code Optional} containing the customer, or empty if not found
     */
    @Override
    public Optional<Customer> findById(UUID id) {
        return spring.findById(id.toString()).map(JpaCustomerRepository::toDomain);
    }

    /**
     * Finds a customer by their email address.
     *
     * @param email the email address to search for
     * @return an {@code Optional} containing the customer, or empty if not found
     */
    @Override
    public Optional<Customer> findByEmail(String email) {
        return spring.findByEmail(email).map(JpaCustomerRepository::toDomain);
    }

    /**
     * Returns all persisted customers.
     *
     * @return a list of all customers
     */
    @Override
    public List<Customer> findAll() {
        return spring.findAll().stream().map(JpaCustomerRepository::toDomain).toList();
    }

    /**
     * Converts a domain {@code Customer} to a {@code CustomerJpaEntity}.
     *
     * @param c     the domain customer
     * @param isNew {@code true} if this is a new entity
     * @return the corresponding JPA entity
     */
    private static CustomerJpaEntity toEntity(Customer c, boolean isNew) {
        return new CustomerJpaEntity(c.getId().toString(), c.getName(), c.getEmail(), isNew);
    }

    /**
     * Converts a {@code CustomerJpaEntity} to a domain {@code Customer}.
     *
     * @param e the JPA entity
     * @return the corresponding domain customer
     */
    private static Customer toDomain(CustomerJpaEntity e) {
        return new Customer(UUID.fromString(e.getId()), e.getName(), e.getEmail());
    }
}
