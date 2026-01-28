package com.example.E_commerce.Dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class productRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Long categoryId;
}
