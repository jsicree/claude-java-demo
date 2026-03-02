package com.example.claudejavademo.adapter.out.persistence;

import com.example.claudejavademo.application.port.out.CustomerRepository;
import com.example.claudejavademo.domain.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<UUID, Customer> store = new ConcurrentHashMap<>();

    @Override
    public Customer save(Customer customer) {
        store.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return store.values().stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<Customer> findAll() {
        return List.copyOf(store.values());
    }
}
