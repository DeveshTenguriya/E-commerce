package com.example.E_commerce.Dto.Order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class orderItemResponse {

    private Long productId;

    private String productName;

    private BigDecimal priceAtPurchase;

    private int quantity;

    private BigDecimal subTotal;
}

//No Product entity exposed
//No recursion risk
//Clean API response
