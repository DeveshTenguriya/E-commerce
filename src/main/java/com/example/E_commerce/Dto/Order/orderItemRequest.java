package com.example.E_commerce.Dto.Order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class orderItemRequest {

    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;
}

//Client only sends IDs + quantity
//Price comes from backend (never trust frontend)
