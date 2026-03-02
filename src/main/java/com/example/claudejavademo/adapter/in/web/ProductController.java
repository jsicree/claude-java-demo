package com.example.claudejavademo.adapter.in.web;

import com.example.claudejavademo.application.port.in.CreateProductUseCase;
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

    ProductController(CreateProductUseCase createProduct, GetProductUseCase getProduct) {
        this.createProduct = createProduct;
        this.getProduct = getProduct;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductResponse create(@RequestBody CreateProductRequest request) {
        Product product = createProduct.createProduct(request.name(), request.price());
        return ProductResponse.from(product);
    }

    @GetMapping("/{id}")
    ProductResponse getById(@PathVariable UUID id) {
        return ProductResponse.from(getProduct.getProduct(id));
    }

    @GetMapping
    List<ProductResponse> getAll() {
        return getProduct.getAllProducts().stream()
                .map(ProductResponse::from)
                .toList();
    }
}