package com.example.claudejavademo.application.port.out;

import com.example.claudejavademo.domain.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(UUID id);

    Optional<Customer> findByEmail(String email);

    List<Customer> findAll();
}
