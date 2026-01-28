package com.example.E_commerce.Dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    @NotBlank(message = "username is required")
    @NotNull
    private String username;

    @NotBlank(message = "Email is required")
    @NotNull
    private String email;

    @NotBlank(message ="password is required")
    @NotNull
    private String password;
}
