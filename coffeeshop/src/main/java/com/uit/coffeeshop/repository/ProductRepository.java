package com.uit.coffeeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uit.coffeeshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
