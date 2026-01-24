package com.example.E_commerce.Controller;

import com.example.E_commerce.Entity.Order;
import com.example.E_commerce.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<Order> place(Authentication auth) {
        return ResponseEntity.ok(orderService.placeOrder(auth.getName()));
    }

}
