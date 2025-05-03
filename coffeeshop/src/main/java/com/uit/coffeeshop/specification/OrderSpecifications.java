package com.uit.coffeeshop.specification;

import java.time.Instant;

import org.springframework.data.jpa.domain.Specification;

import com.uit.coffeeshop.domain.Order;
import com.uit.coffeeshop.domain.User;
import com.uit.coffeeshop.util.constant.OrderStatus;

public class OrderSpecifications {

    public static Specification<Order> belongsToUser(User user) {
        return (root, query, cb) -> cb.equal(root.get("user"), user);
    }

    // You can also add more reusable filters here
    public static Specification<Order> hasStatus(OrderStatus status) {
        return (root, query, cb) -> cb.equal(root.get("orderStatus"), status);
    }

    public static Specification<Order> createdAfter(Instant date) {
        return (root, query, cb) -> cb.greaterThan(root.get("createdAt"), date);
    }

    // etc...
}
