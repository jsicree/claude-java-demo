package com.example.claudejavademo.application.port.in;

import com.example.claudejavademo.domain.model.Customer;

public interface RegisterCustomerUseCase {

    Customer registerCustomer(String name, String email);
}
