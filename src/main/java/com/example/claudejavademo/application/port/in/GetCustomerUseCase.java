package com.example.claudejavademo.application.port.in;

import com.example.claudejavademo.domain.model.Customer;

import java.util.List;
import java.util.UUID;

public interface GetCustomerUseCase {

    Customer getCustomer(UUID id);

    List<Customer> getAllCustomers();
}
