/**
 * Spring Data JPA repository interface for {@code CustomerJpaEntity}.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, String> {

    /**
     * Finds a customer entity by email address.
     *
     * @param email the email address to search for
     * @return an {@code Optional} containing the entity, or empty if not found
     */
    Optional<CustomerJpaEntity> findByEmail(String email);
}
