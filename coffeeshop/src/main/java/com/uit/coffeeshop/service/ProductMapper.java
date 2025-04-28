package com.uit.coffeeshop.service;

import org.springframework.stereotype.Component;

import com.uit.coffeeshop.domain.Product;
import com.uit.coffeeshop.domain.request.ProductDTO;

@Component
public class ProductMapper {
    
    public ProductDTO toDTO(Product product) {
        if (product == null) return null;
        
        return new ProductDTO(
            product.getId(),
            product.getCategory(),
            product.getDescription(),
            product.getImageUrl(),
            product.getName(),
            product.getPrice(),
            product.getRating()
        );
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) return null;
        
        return new Product(
            dto.getId(),
            dto.getCategory(),
            dto.getDescription(),
            dto.getImage_url(),
            dto.getName(),
            dto.getPrice(),
            dto.getRating()
        );
    }
}
