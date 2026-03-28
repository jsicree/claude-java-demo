/**
 * Web-layer tests for CustomerController using standalone MockMvc setup.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-28
 */
package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.application.port.in.DeleteCustomerUseCase;
import com.example.claudejavademo.application.port.in.GetCustomerUseCase;
import com.example.claudejavademo.application.port.in.RegisterCustomerUseCase;
import com.example.claudejavademo.domain.exception.CustomerAlreadyExistsException;
import com.example.claudejavademo.domain.exception.CustomerNotFoundException;
import com.example.claudejavademo.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private RegisterCustomerUseCase registerCustomer;

    @Mock
    private GetCustomerUseCase getCustomer;

    @Mock
    private DeleteCustomerUseCase deleteCustomer;

    private MockMvc mockMvc;

    /**
     * Builds a standalone MockMvc with the controller and global exception handler before each test.
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new CustomerController(registerCustomer, getCustomer, deleteCustomer))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    /**
     * Verifies that POST /api/customers with a valid body returns 201 and the registered customer JSON.
     */
    @Test
    void registerCustomer_validRequest_returns201WithBody() throws Exception {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Alice", "alice@example.com");
        when(registerCustomer.registerCustomer("Alice", "alice@example.com")).thenReturn(customer);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Alice\",\"email\":\"alice@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    /**
     * Verifies that POST /api/customers with a duplicate email returns 409.
     */
    @Test
    void registerCustomer_duplicateEmail_returns409() throws Exception {
        when(registerCustomer.registerCustomer(anyString(), anyString()))
                .thenThrow(new CustomerAlreadyExistsException("alice@example.com"));

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Alice\",\"email\":\"alice@example.com\"}"))
                .andExpect(status().isConflict());
    }

    /**
     * Verifies that GET /api/customers/{id} for an existing customer returns 200 and the customer body.
     */
    @Test
    void getById_existingId_returns200WithBody() throws Exception {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Alice", "alice@example.com");
        when(getCustomer.getCustomer(id)).thenReturn(customer);

        mockMvc.perform(get("/api/customers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    /**
     * Verifies that GET /api/customers/{id} for an unknown customer returns 404.
     */
    @Test
    void getById_unknownId_returns404() throws Exception {
        UUID id = UUID.randomUUID();
        when(getCustomer.getCustomer(id)).thenThrow(new CustomerNotFoundException(id));

        mockMvc.perform(get("/api/customers/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Verifies that GET /api/customers returns 200 and a list of all customers.
     */
    @Test
    void getAll_returns200WithList() throws Exception {
        when(getCustomer.getAllCustomers()).thenReturn(List.of(
                new Customer(UUID.randomUUID(), "Alice", "alice@example.com"),
                new Customer(UUID.randomUUID(), "Bob", "bob@example.com")
        ));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    /**
     * Verifies that DELETE /api/customers/{id} for an existing customer returns 204 with no body.
     */
    @Test
    void delete_existingId_returns204() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/customers/{id}", id))
                .andExpect(status().isNoContent());
    }

    /**
     * Verifies that DELETE /api/customers/{id} for an unknown customer returns 404.
     */
    @Test
    void delete_unknownId_returns404() throws Exception {
        UUID id = UUID.randomUUID();
        doThrow(new CustomerNotFoundException(id)).when(deleteCustomer).deleteCustomer(id);

        mockMvc.perform(delete("/api/customers/{id}", id))
                .andExpect(status().isNotFound());
    }
}
