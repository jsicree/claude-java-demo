/**
 * Unit tests for CustomerService, covering all use-case methods with a mocked repository.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-28
 */
package com.example.claudejavademo.application.service;

import com.example.claudejavademo.application.port.out.CustomerRepository;
import com.example.claudejavademo.domain.exception.CustomerAlreadyExistsException;
import com.example.claudejavademo.domain.exception.CustomerNotFoundException;
import com.example.claudejavademo.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService service;

    /**
     * Creates a fresh CustomerService backed by the mock repository before each test.
     */
    @BeforeEach
    void setUp() {
        service = new CustomerService(customerRepository);
    }

    /**
     * Verifies that registerCustomer with a unique email saves and returns the new customer.
     */
    @Test
    void registerCustomer_newEmail_savesAndReturnsCustomer() {
        when(customerRepository.findByEmail("alice@example.com")).thenReturn(Optional.empty());
        Customer saved = Customer.register("Alice", "alice@example.com");
        when(customerRepository.save(any(Customer.class))).thenReturn(saved);

        Customer result = service.registerCustomer("Alice", "alice@example.com");

        assertThat(result).isEqualTo(saved);
        verify(customerRepository).save(any(Customer.class));
    }

    /**
     * Verifies that registerCustomer throws CustomerAlreadyExistsException when the email is already taken.
     */
    @Test
    void registerCustomer_duplicateEmail_throwsCustomerAlreadyExistsException() {
        Customer existing = Customer.register("Alice", "alice@example.com");
        when(customerRepository.findByEmail("alice@example.com")).thenReturn(Optional.of(existing));

        assertThatThrownBy(() -> service.registerCustomer("Alice2", "alice@example.com"))
                .isInstanceOf(CustomerAlreadyExistsException.class);
    }

    /**
     * Verifies that getCustomer returns the customer when it exists in the repository.
     */
    @Test
    void getCustomer_existingId_returnsCustomer() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Alice", "alice@example.com");
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        Customer result = service.getCustomer(id);

        assertThat(result).isEqualTo(customer);
    }

    /**
     * Verifies that getCustomer throws CustomerNotFoundException when no customer has the given ID.
     */
    @Test
    void getCustomer_unknownId_throwsCustomerNotFoundException() {
        UUID id = UUID.randomUUID();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getCustomer(id))
                .isInstanceOf(CustomerNotFoundException.class);
    }

    /**
     * Verifies that getAllCustomers delegates to the repository and returns all customers.
     */
    @Test
    void getAllCustomers_returnsAllFromRepository() {
        List<Customer> customers = List.of(
                Customer.register("Alice", "alice@example.com"),
                Customer.register("Bob", "bob@example.com")
        );
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = service.getAllCustomers();

        assertThat(result).isEqualTo(customers);
    }

    /**
     * Verifies that deleteCustomer verifies existence then calls deleteById on the repository.
     */
    @Test
    void deleteCustomer_existingId_deletesFromRepository() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Alice", "alice@example.com");
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        service.deleteCustomer(id);

        verify(customerRepository).deleteById(id);
    }

    /**
     * Verifies that deleteCustomer throws CustomerNotFoundException when no customer has the given ID.
     */
    @Test
    void deleteCustomer_unknownId_throwsCustomerNotFoundException() {
        UUID id = UUID.randomUUID();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteCustomer(id))
                .isInstanceOf(CustomerNotFoundException.class);
    }
}
