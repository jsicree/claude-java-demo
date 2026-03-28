/**
 * Unit tests for the Product domain model.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-28
 */
package com.example.claudejavademo.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    /**
     * Verifies that the create factory method generates a non-null UUID and stores name and price.
     */
    @Test
    void create_generatesUniqueIdAndStoresFields() {
        Product product = Product.create("Widget", new BigDecimal("9.99"));

        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Widget");
        assertThat(product.getPrice()).isEqualByComparingTo("9.99");
    }

    /**
     * Verifies that two calls to create produce different UUIDs.
     */
    @Test
    void create_eachCallProducesUniqueId() {
        Product a = Product.create("A", BigDecimal.ONE);
        Product b = Product.create("B", BigDecimal.ONE);

        assertThat(a.getId()).isNotEqualTo(b.getId());
    }

    /**
     * Verifies that the full constructor stores all fields.
     */
    @Test
    void constructor_storesAllFields() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Gadget", new BigDecimal("19.99"));

        assertThat(product.getId()).isEqualTo(id);
        assertThat(product.getName()).isEqualTo("Gadget");
        assertThat(product.getPrice()).isEqualByComparingTo("19.99");
    }

    /**
     * Verifies that updatePrice accepts a positive price and updates the stored value.
     */
    @Test
    void updatePrice_positiveValue_updatesPrice() {
        Product product = Product.create("Widget", new BigDecimal("5.00"));
        product.updatePrice(new BigDecimal("12.50"));

        assertThat(product.getPrice()).isEqualByComparingTo("12.50");
    }

    /**
     * Verifies that updatePrice accepts zero as a valid price.
     */
    @Test
    void updatePrice_zero_updatesPrice() {
        Product product = Product.create("Widget", new BigDecimal("5.00"));
        product.updatePrice(BigDecimal.ZERO);

        assertThat(product.getPrice()).isEqualByComparingTo("0");
    }

    /**
     * Verifies that updatePrice with a negative value throws IllegalArgumentException.
     */
    @Test
    void updatePrice_negativeValue_throwsIllegalArgumentException() {
        Product product = Product.create("Widget", new BigDecimal("5.00"));

        assertThatThrownBy(() -> product.updatePrice(new BigDecimal("-1.00")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Price must be non-negative");
    }

    /**
     * Verifies that updatePrice with null throws IllegalArgumentException.
     */
    @Test
    void updatePrice_null_throwsIllegalArgumentException() {
        Product product = Product.create("Widget", new BigDecimal("5.00"));

        assertThatThrownBy(() -> product.updatePrice(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Price must be non-negative");
    }
}
