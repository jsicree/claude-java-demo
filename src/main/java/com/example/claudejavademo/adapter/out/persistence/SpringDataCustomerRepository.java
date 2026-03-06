package com.example.claudejavademo.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, String> {
    Optional<CustomerJpaEntity> findByEmail(String email);
}
