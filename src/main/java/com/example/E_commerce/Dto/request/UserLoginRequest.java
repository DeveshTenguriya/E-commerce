package com.example.E_commerce.Dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    @NotBlank(message = "Email is required")
    @NotNull
    private String email;

    @NotBlank(message ="password is required")
    @NotNull
    private String password;
}
