/**
 * Response body representing a product resource.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.domain.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

record ProductResponse(UUID id, String name, BigDecimal price) {

    /**
     * Creates a {@code ProductResponse} from a domain {@code Product}.
     *
     * @param product the source domain product
     * @return a new {@code ProductResponse}
     */
    static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
