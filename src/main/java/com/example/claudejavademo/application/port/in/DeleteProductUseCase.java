/**
 * Input port for deleting an existing product.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-22
 */
package com.example.claudejavademo.application.port.in;

import java.util.UUID;

public interface DeleteProductUseCase {

    /**
     * Deletes the product with the given identifier.
     *
     * @param id the product ID
     * @throws com.example.claudejavademo.domain.exception.ProductNotFoundException if no product with the given ID exists
     */
    void deleteProduct(UUID id);
}
