package com.example.E_commerce.Dto.Order;

import com.example.E_commerce.Entity.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class orderRequest {

    @NotNull
    private Long orderId;

    @NotEmpty
    private List<orderItemRequest> items;
}
