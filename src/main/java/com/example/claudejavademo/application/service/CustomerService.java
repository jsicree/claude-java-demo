/**
 * Use-case implementation for customer registration and retrieval.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.application.service;

import com.example.claudejavademo.application.port.in.DeleteCustomerUseCase;
import com.example.claudejavademo.application.port.in.GetCustomerUseCase;
import com.example.claudejavademo.application.port.in.RegisterCustomerUseCase;
import com.example.claudejavademo.application.port.out.CustomerRepository;
import com.example.claudejavademo.domain.exception.CustomerAlreadyExistsException;
import com.example.claudejavademo.domain.exception.CustomerNotFoundException;
import com.example.claudejavademo.domain.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
class CustomerService implements RegisterCustomerUseCase, GetCustomerUseCase, DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    /**
     * Constructs a CustomerService with the required repository.
     *
     * @param customerRepository the output port for customer persistence
     */
    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Registers a new customer, enforcing email uniqueness.
     *
     * @param name  the customer name
     * @param email the customer email address
     * @return the newly registered and persisted {@code Customer}
     * @throws CustomerAlreadyExistsException if a customer with the given email already exists
     */
    @Override
    public Customer registerCustomer(String name, String email) {
        customerRepository.findByEmail(email).ifPresent(existing -> {
            throw new CustomerAlreadyExistsException(email);
        });
        Customer customer = Customer.register(name, email);
        return customerRepository.save(customer);
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id the customer ID
     * @return the matching {@code Customer}
     * @throws CustomerNotFoundException if no customer with the given ID exists
     */
    @Override
    public Customer getCustomer(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    /**
     * Returns all customers.
     *
     * @return a list of all customers; empty if none exist
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Deletes the customer with the given ID.
     *
     * @param id the customer ID
     * @throws CustomerNotFoundException if no customer with the given ID exists
     */
    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.deleteById(id);
    }
}
