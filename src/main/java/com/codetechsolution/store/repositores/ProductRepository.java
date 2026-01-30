package com.codetechsolution.store.repositores;

import com.codetechsolution.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}