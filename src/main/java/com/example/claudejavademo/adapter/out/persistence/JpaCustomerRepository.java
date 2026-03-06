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

    JpaCustomerRepository(SpringDataCustomerRepository spring) {
        this.spring = spring;
    }

    @Override
    public Customer save(Customer customer) {
        spring.save(toEntity(customer, true));
        return customer;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return spring.findById(id.toString()).map(JpaCustomerRepository::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return spring.findByEmail(email).map(JpaCustomerRepository::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return spring.findAll().stream().map(JpaCustomerRepository::toDomain).toList();
    }

    private static CustomerJpaEntity toEntity(Customer c, boolean isNew) {
        return new CustomerJpaEntity(c.getId().toString(), c.getName(), c.getEmail(), isNew);
    }

    private static Customer toDomain(CustomerJpaEntity e) {
        return new Customer(UUID.fromString(e.getId()), e.getName(), e.getEmail());
    }
}
