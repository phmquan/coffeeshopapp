package com.uit.coffeeshop.controller;



import com.uit.coffeeshop.domain.request.ProductDTO;
import com.uit.coffeeshop.service.ProductMapper;
import com.uit.coffeeshop.service.ProductService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {  // Changed from String to Long
        ProductDTO product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,  // Changed from String to Long
            @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {  // Changed from String to Long
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
