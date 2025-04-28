package com.uit.coffeeshop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uit.coffeeshop.domain.Product;
import com.uit.coffeeshop.domain.request.ProductDTO;
import com.uit.coffeeshop.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

   

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
            .map(productMapper::toDTO)
            .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
            .map(productMapper::toDTO)
            .orElse(null);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        if (!productRepository.existsById(id)) {
            return null;
        }
        Product product = productMapper.toEntity(productDTO);
        product.setId(id);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
