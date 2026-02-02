package com.example.E_commerce.Service;

import com.example.E_commerce.Dto.Order.orderItemResponse;
import com.example.E_commerce.Dto.Order.orderRequest;
import com.example.E_commerce.Dto.Order.orderResponse;
import com.example.E_commerce.Entity.*;
import com.example.E_commerce.Repository.CartRepository;
import com.example.E_commerce.Repository.OrderRepository;
import com.example.E_commerce.Repository.ProductRepository;
import com.example.E_commerce.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public OrderService(CartRepository cartRepository, OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public orderResponse placeOrder(String email, orderRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart empty"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(orderStatus.PLACED);
        order.setOrderItems(new ArrayList<>());

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem ci : cart.getItems()) {

            Product product = ci.getProduct();

            if (product.getStock() < ci.getQuantity())
                throw new RuntimeException("Insufficient stock");

            product.setStock(product.getStock() - ci.getQuantity());

            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(product);
            oi.setQuantity(ci.getQuantity());
            oi.setPriceAtPurchase(product.getPrice());

            order.getOrderItems().add(oi);

            total = total.add(
                    product.getPrice().multiply(
                            BigDecimal.valueOf(ci.getQuantity())));
        }

        order.setTotalAmount(total);

        cart.getItems().clear(); // Empty cart after order

        Order saveOrder=  orderRepository.save(order);

        return mapToOrderResponse(saveOrder);
    }

    private orderItemResponse mapToOrderItemResponse(OrderItem item) {

        orderItemResponse dto = new orderItemResponse();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setPriceAtPurchase(item.getPriceAtPurchase());
        dto.setQuantity(item.getQuantity());

        dto.setSubTotal(
                item.getPriceAtPurchase()
                        .multiply(BigDecimal.valueOf(item.getQuantity()))
        );

        return dto;
    }

    private orderResponse mapToOrderResponse(Order order) {

        orderResponse response = new orderResponse();
        response.setOrderId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());

        response.setItems(
                order.getOrderItems()
                        .stream()
                        .map(this::mapToOrderItemResponse)
                        .toList()
        );

        return response;
    }

}
