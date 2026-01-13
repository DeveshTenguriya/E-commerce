package com.example.E_commerce.Repository;

import com.example.E_commerce.Entity.User;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
