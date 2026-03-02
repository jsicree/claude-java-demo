package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.application.port.in.GetCustomerUseCase;
import com.example.claudejavademo.application.port.in.RegisterCustomerUseCase;
import com.example.claudejavademo.domain.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
class CustomerController {

    private final RegisterCustomerUseCase registerCustomer;
    private final GetCustomerUseCase getCustomer;

    CustomerController(RegisterCustomerUseCase registerCustomer, GetCustomerUseCase getCustomer) {
        this.registerCustomer = registerCustomer;
        this.getCustomer = getCustomer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerResponse register(@RequestBody RegisterCustomerRequest request) {
        Customer customer = registerCustomer.registerCustomer(request.name(), request.email());
        return CustomerResponse.from(customer);
    }

    @GetMapping("/{id}")
    CustomerResponse getById(@PathVariable UUID id) {
        return CustomerResponse.from(getCustomer.getCustomer(id));
    }

    @GetMapping
    List<CustomerResponse> getAll() {
        return getCustomer.getAllCustomers().stream()
                .map(CustomerResponse::from)
                .toList();
    }
}
