package com.example.claudejavademo.domain.model;

import java.util.UUID;

public class Customer {

    private final UUID id;
    private String name;
    private String email;

    public Customer(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Customer register(String name, String email) {
        return new Customer(UUID.randomUUID(), name, email);
    }

    public UUID getId()     { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
