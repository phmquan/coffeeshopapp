package com.uit.coffeeshop.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;
import com.uit.coffeeshop.domain.Order;
import com.uit.coffeeshop.domain.request.OrderCreateDTO;
import com.uit.coffeeshop.domain.response.ResultPaginationDTO;
import com.uit.coffeeshop.service.OrderService;
import com.uit.coffeeshop.util.annotation.ApiMessage;
import com.uit.coffeeshop.util.error.IdInvalidException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    @ApiMessage("Get All Order By User")
    public ResponseEntity<ResultPaginationDTO> getAllOrderByUserWithPagination(@Filter Specification<Order> spec, Pageable pageable, @PathVariable String id){
        return ResponseEntity.ok(orderService.getAllOrderByUserId(spec,pageable,id));
    }
    @GetMapping
    @ApiMessage("Get All Order")
    public ResponseEntity<ResultPaginationDTO> getAllOrder(@Filter Specification<Order> spec, Pageable pageable){
        return ResponseEntity.ok(orderService.getAllOrder(spec,pageable));

    }
    @PostMapping
    @ApiMessage("Create Order")
    public ResponseEntity<?> createOrder(
        @Valid @RequestBody OrderCreateDTO order,
        @RequestHeader("Authorization") String authorizationHeader
    ) {
        try {
            return ResponseEntity.ok(orderService.createOrder(order, authorizationHeader));
        } catch (IdInvalidException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}/{status}")
    @ApiMessage("Update Status Order")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String id, @PathVariable String status){
        return ResponseEntity.ok(orderService.updateOrderStatus(id,status));
    }
}
