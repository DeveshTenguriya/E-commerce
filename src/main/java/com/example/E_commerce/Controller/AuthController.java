package com.example.E_commerce.Controller;

import com.example.E_commerce.Config.JwtServices;
import com.example.E_commerce.Dto.UserLoginRequest;
import com.example.E_commerce.Dto.UserRegisterRequest;
import com.example.E_commerce.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegisterRequest request){

        return  ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(path="/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginRequest request){

        return ResponseEntity.ok(authService.login(request));
    }
}
