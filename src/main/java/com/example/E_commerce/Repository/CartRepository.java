package com.example.E_commerce.Repository;

import com.example.E_commerce.Entity.Cart;
import com.example.E_commerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
