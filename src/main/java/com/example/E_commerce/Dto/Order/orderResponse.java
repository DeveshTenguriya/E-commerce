package com.example.E_commerce.Dto.Order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class orderResponse {

    private Long oderId;

    private Long userId;

    private String status;

    private BigDecimal totalAmount;

    private List<orderItemResponse> items;
}
