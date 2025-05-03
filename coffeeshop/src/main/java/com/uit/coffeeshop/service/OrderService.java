package com.uit.coffeeshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import com.uit.coffeeshop.domain.Order;
import com.uit.coffeeshop.domain.OrderDetails;
import com.uit.coffeeshop.domain.Product;
import com.uit.coffeeshop.domain.User;
import com.uit.coffeeshop.domain.request.OrderCreateDTO;
import com.uit.coffeeshop.domain.request.OrderDetailsDTO;
import com.uit.coffeeshop.domain.response.ResultPaginationDTO;
import com.uit.coffeeshop.repository.OrderDetailsRepository;
import com.uit.coffeeshop.repository.OrderRepository;
import com.uit.coffeeshop.repository.ProductRepository;
import com.uit.coffeeshop.specification.OrderSpecifications;
import com.uit.coffeeshop.util.SecurityUtil;
import com.uit.coffeeshop.util.constant.OrderStatus;
import com.uit.coffeeshop.util.error.IdInvalidException;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final SecurityUtil securityUtil;

    public ResultPaginationDTO getAllOrderByUserId(Specification<Order> spec, Pageable pageable,String id){
        User currentUser=userService.getUserById(Long.parseLong(id));
        Specification<Order> specOrder = spec == null
        ? OrderSpecifications.belongsToUser(currentUser)
        : spec.and(OrderSpecifications.belongsToUser(currentUser));
        Page<Order> pageOrder=orderRepository.findAll(specOrder,pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber()+1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageOrder.getTotalPages());
        mt.setTotal(pageOrder.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pageOrder.getContent());
        return rs;
    }

    @Transactional
    public Order createOrder(OrderCreateDTO orderDTO,@RequestHeader("Authorization") String authorizationHeader) throws IdInvalidException {
        // Extract and validate JWT token
        String accessToken = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            accessToken = authorizationHeader.substring(7); // Remove "Bearer "
        } else {
            throw new IdInvalidException("Invalid authorization header");
        }

        // Validate token and get user email
        Jwt decodedToken = securityUtil.checkValidRefreshToken(accessToken);
        String email = decodedToken.getSubject();
        
        // Get current user
        User currentUser = userService.getUserByEmail(email);
        if (currentUser == null) {
            throw new IdInvalidException("User not found");
        }

        // Create and save order
        Order newOrder = new Order();
        newOrder.setUser(currentUser);
        newOrder.setOrderNote(orderDTO.getOrderNote());
        newOrder.setPhoneNumber(orderDTO.getPhoneNumber());
        newOrder.setReceivedAddress(orderDTO.getReceivedAddress());
        newOrder.setReceivedName(orderDTO.getReceivedName());
        newOrder.setOrderStatus(OrderStatus.PENDING);

        // Calculate total price and create order details
        Long totalPrice = 0L;
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (OrderDetailsDTO detailDTO : orderDTO.getOrderDetails()) {
            Product product = productRepository.findById(detailDTO.getProductId())
                .orElseThrow(() -> new IdInvalidException("Product not found: " + detailDTO.getProductId()));

            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(detailDTO.getQuantity());
            orderDetail.setOrder(newOrder);
            orderDetail.setPrice(product.getPrice());
            totalPrice += (long)(product.getPrice() * detailDTO.getQuantity());
            orderDetailsList.add(orderDetail);
        }

        newOrder.setTotalPrice(totalPrice);
        newOrder.setOrderDetails(orderDetailsList);

        // Save order first to get the ID
        Order savedOrder = orderRepository.save(newOrder);

        // Save all order details
        orderDetailsRepository.saveAll(orderDetailsList);

        return savedOrder;
    }

    public ResultPaginationDTO getAllOrder(Specification<Order> spec, Pageable pageable) {
       Page<Order> pageOrder=orderRepository.findAll(spec,pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageOrder.getTotalPages());
        mt.setTotal(pageOrder.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pageOrder.getContent());
        return rs;
    }
    public Order updateOrderStatus(String id, String status) {
        Order currentOrder=orderRepository.findById(Long.parseLong(id)).orElse(null);
        if(currentOrder!=null){
            currentOrder.setOrderStatus(OrderStatus.valueOf(status));
        }
        return orderRepository.save(currentOrder);
    }
}
