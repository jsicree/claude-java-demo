/**
 * Request body for creating a new product.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo.adapter.in.web;

import java.math.BigDecimal;

record CreateProductRequest(String name, BigDecimal price) {}