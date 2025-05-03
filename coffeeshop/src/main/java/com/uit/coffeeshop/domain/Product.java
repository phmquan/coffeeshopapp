package com.uit.coffeeshop.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Changed to IDENTITY for auto-increment
    private Long id;  // Changed from String to Long

    @Column(nullable = false)
    private String category;

    @Column(length = 1000)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    private String name;

    private Long price;

    private Double rating;

   

  
}
