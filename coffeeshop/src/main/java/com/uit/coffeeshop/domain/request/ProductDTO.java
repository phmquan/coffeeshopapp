package com.uit.coffeeshop.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;  // Changed from String to Long
    private String category;
    private String description;
    private String image_url;
    private String name;
    private Double price;
    private Double rating;
}
