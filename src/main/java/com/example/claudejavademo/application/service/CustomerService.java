package com.example.claudejavademo.application.service;

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
class CustomerService implements RegisterCustomerUseCase, GetCustomerUseCase {

    private final CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer registerCustomer(String name, String email) {
        customerRepository.findByEmail(email).ifPresent(existing -> {
            throw new CustomerAlreadyExistsException(email);
        });
        Customer customer = Customer.register(name, email);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
