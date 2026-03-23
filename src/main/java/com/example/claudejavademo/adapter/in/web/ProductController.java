/**
 * REST controller exposing product endpoints at {@code /api/products}.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.application.port.in.CreateProductUseCase;
import com.example.claudejavademo.application.port.in.DeleteProductUseCase;
import com.example.claudejavademo.application.port.in.GetProductUseCase;
import com.example.claudejavademo.domain.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final CreateProductUseCase createProduct;
    private final GetProductUseCase getProduct;
    private final DeleteProductUseCase deleteProduct;

    /**
     * Constructs a ProductController with the required use cases.
     *
     * @param createProduct use case for creating products
     * @param getProduct    use case for retrieving products
     * @param deleteProduct use case for deleting products
     */
    ProductController(CreateProductUseCase createProduct, GetProductUseCase getProduct, DeleteProductUseCase deleteProduct) {
        this.createProduct = createProduct;
        this.getProduct = getProduct;
        this.deleteProduct = deleteProduct;
    }

    /**
     * Handles POST /api/products — creates a new product.
     *
     * @param request the request body containing name and price
     * @return a {@code ProductResponse} representing the created product
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductResponse create(@RequestBody CreateProductRequest request) {
        Product product = createProduct.createProduct(request.name(), request.price());
        return ProductResponse.from(product);
    }

    /**
     * Handles GET /api/products/{id} — retrieves a product by ID.
     *
     * @param id the product ID
     * @return a {@code ProductResponse} for the matching product
     * @throws com.example.claudejavademo.domain.exception.ProductNotFoundException if no product with the given ID exists
     */
    @GetMapping("/{id}")
    ProductResponse getById(@PathVariable UUID id) {
        return ProductResponse.from(getProduct.getProduct(id));
    }

    /**
     * Handles GET /api/products — retrieves all products.
     *
     * @return a list of {@code ProductResponse} objects
     */
    @GetMapping
    List<ProductResponse> getAll() {
        return getProduct.getAllProducts().stream()
                .map(ProductResponse::from)
                .toList();
    }

    /**
     * Handles DELETE /api/products/{id} — deletes a product.
     *
     * @param id the product ID
     * @throws com.example.claudejavademo.domain.exception.ProductNotFoundException if no product with the given ID exists
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id) {
        deleteProduct.deleteProduct(id);
    }
}
