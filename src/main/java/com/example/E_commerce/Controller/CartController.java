package com.example.E_commerce.Controller;


import com.example.E_commerce.Entity.Cart;
import com.example.E_commerce.Service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cart")
@PreAuthorize("hasRole('CUSTOMER')")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping
    public ResponseEntity<Cart> viewCart(Authentication auth) {
        return ResponseEntity.ok(cartService.getCart(auth.getName()));
    }



    @PostMapping(path = "/add/{productId}")
    public ResponseEntity<Cart> addItem(@PathVariable Long productId, @RequestParam Integer quantity, Authentication auth){

      return   ResponseEntity.ok(cartService.addItem(productId,quantity,auth.getName()));

    }
}
