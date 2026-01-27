package com.example.E_commerce.Service;

import com.example.E_commerce.Config.JwtServices;
import com.example.E_commerce.Dto.UserLoginRequest;
import com.example.E_commerce.Dto.UserRegisterRequest;
import com.example.E_commerce.Entity.Role;
import com.example.E_commerce.Entity.User;
import com.example.E_commerce.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final JwtServices jwtServices;
    private final UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtServices jwtServices, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.jwtServices = jwtServices;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public String register(UserRegisterRequest request){

        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        User user= new User();
        user.setEmail(request.getEmail());
        user.setName(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);

        return "User registered successfully";

    }

    //the login info is here
    public String login(UserLoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()));

        //userDetailServiceImpl executed in this step as this (userdetail) method was build in userDetailServiceImpl class
        UserDetails userDetails= new
                org.springframework.security.core.userdetails.User(
                request.getEmail(), "", List.of());

        return jwtServices.generateToken(userDetails);
    }
}
