package com.example.E_commerce.Controller;

import com.example.E_commerce.Dto.Order.orderRequest;
import com.example.E_commerce.Dto.Order.orderResponse;
import com.example.E_commerce.Entity.Order;
import com.example.E_commerce.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
@PreAuthorize("hasRole('CUSTOMER')")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<orderResponse> place(Authentication auth,@Valid @RequestBody orderRequest request) {
        return ResponseEntity.ok(orderService.placeOrder(auth.getName(),request));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<orderResponse> getOrder(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

}
