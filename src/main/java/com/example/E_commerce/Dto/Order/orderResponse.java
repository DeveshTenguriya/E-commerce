package com.example.E_commerce.Dto.Order;

import com.example.E_commerce.Entity.orderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class orderResponse {

    private Long orderId;

    private Long userId;

    private orderStatus status;

    private BigDecimal totalAmount;

    private List<orderItemResponse> items;
}

//Used when fetching order details
