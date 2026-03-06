package com.example.claudejavademo.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataProductRepository extends JpaRepository<ProductJpaEntity, String> {
}
