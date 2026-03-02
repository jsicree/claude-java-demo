package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.domain.model.Customer;

import java.util.UUID;

record CustomerResponse(UUID id, String name, String email) {

    static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }
}
