/**
 * Web-layer tests for ProductController using standalone MockMvc setup.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-28
 */
package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.application.port.in.CreateProductUseCase;
import com.example.claudejavademo.application.port.in.DeleteProductUseCase;
import com.example.claudejavademo.application.port.in.GetProductUseCase;
import com.example.claudejavademo.domain.exception.ProductNotFoundException;
import com.example.claudejavademo.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private CreateProductUseCase createProduct;

    @Mock
    private GetProductUseCase getProduct;

    @Mock
    private DeleteProductUseCase deleteProduct;

    private MockMvc mockMvc;

    /**
     * Builds a standalone MockMvc with the controller and global exception handler before each test.
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ProductController(createProduct, getProduct, deleteProduct))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    /**
     * Verifies that POST /api/products with a valid body returns 201 and the created product JSON.
     */
    @Test
    void createProduct_validRequest_returns201WithBody() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Widget", new BigDecimal("9.99"));
        when(createProduct.createProduct(eq("Widget"), any(BigDecimal.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Widget\",\"price\":9.99}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Widget"));
    }

    /**
     * Verifies that GET /api/products/{id} for an existing product returns 200 and the product body.
     */
    @Test
    void getById_existingId_returns200WithBody() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Widget", new BigDecimal("9.99"));
        when(getProduct.getProduct(id)).thenReturn(product);

        mockMvc.perform(get("/api/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Widget"));
    }

    /**
     * Verifies that GET /api/products/{id} for an unknown product returns 404.
     */
    @Test
    void getById_unknownId_returns404() throws Exception {
        UUID id = UUID.randomUUID();
        when(getProduct.getProduct(id)).thenThrow(new ProductNotFoundException(id));

        mockMvc.perform(get("/api/products/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Verifies that GET /api/products returns 200 and a list of all products.
     */
    @Test
    void getAll_returns200WithList() throws Exception {
        when(getProduct.getAllProducts()).thenReturn(List.of(
                new Product(UUID.randomUUID(), "A", BigDecimal.ONE),
                new Product(UUID.randomUUID(), "B", BigDecimal.TEN)
        ));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    /**
     * Verifies that DELETE /api/products/{id} for an existing product returns 204 with no body.
     */
    @Test
    void delete_existingId_returns204() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/products/{id}", id))
                .andExpect(status().isNoContent());
    }

    /**
     * Verifies that DELETE /api/products/{id} for an unknown product returns 404.
     */
    @Test
    void delete_unknownId_returns404() throws Exception {
        UUID id = UUID.randomUUID();
        doThrow(new ProductNotFoundException(id)).when(deleteProduct).deleteProduct(id);

        mockMvc.perform(delete("/api/products/{id}", id))
                .andExpect(status().isNotFound());
    }
}
