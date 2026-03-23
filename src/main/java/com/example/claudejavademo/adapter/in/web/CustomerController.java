/**
 * REST controller exposing customer endpoints at {@code /api/customers}.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.application.port.in.DeleteCustomerUseCase;
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
    private final DeleteCustomerUseCase deleteCustomer;

    /**
     * Constructs a CustomerController with the required use cases.
     *
     * @param registerCustomer use case for registering customers
     * @param getCustomer      use case for retrieving customers
     * @param deleteCustomer   use case for deleting customers
     */
    CustomerController(RegisterCustomerUseCase registerCustomer, GetCustomerUseCase getCustomer, DeleteCustomerUseCase deleteCustomer) {
        this.registerCustomer = registerCustomer;
        this.getCustomer = getCustomer;
        this.deleteCustomer = deleteCustomer;
    }

    /**
     * Handles POST /api/customers — registers a new customer.
     *
     * @param request the request body containing name and email
     * @return a {@code CustomerResponse} representing the registered customer
     * @throws com.example.claudejavademo.domain.exception.CustomerAlreadyExistsException if a customer with the given email already exists
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerResponse register(@RequestBody RegisterCustomerRequest request) {
        Customer customer = registerCustomer.registerCustomer(request.name(), request.email());
        return CustomerResponse.from(customer);
    }

    /**
     * Handles GET /api/customers/{id} — retrieves a customer by ID.
     *
     * @param id the customer ID
     * @return a {@code CustomerResponse} for the matching customer
     * @throws com.example.claudejavademo.domain.exception.CustomerNotFoundException if no customer with the given ID exists
     */
    @GetMapping("/{id}")
    CustomerResponse getById(@PathVariable UUID id) {
        return CustomerResponse.from(getCustomer.getCustomer(id));
    }

    /**
     * Handles GET /api/customers — retrieves all customers.
     *
     * @return a list of {@code CustomerResponse} objects
     */
    @GetMapping
    List<CustomerResponse> getAll() {
        return getCustomer.getAllCustomers().stream()
                .map(CustomerResponse::from)
                .toList();
    }

    /**
     * Handles DELETE /api/customers/{id} — deletes a customer.
     *
     * @param id the customer ID
     * @throws com.example.claudejavademo.domain.exception.CustomerNotFoundException if no customer with the given ID exists
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id) {
        deleteCustomer.deleteCustomer(id);
    }
}
