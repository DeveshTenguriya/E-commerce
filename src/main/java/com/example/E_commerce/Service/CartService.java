package com.example.E_commerce.Service;

import com.example.E_commerce.Entity.Cart;
import com.example.E_commerce.Entity.CartItem;
import com.example.E_commerce.Entity.Product;
import com.example.E_commerce.Entity.User;
import com.example.E_commerce.Repository.CartRepository;
import com.example.E_commerce.Repository.ProductRepository;
import com.example.E_commerce.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart getCart(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    public Cart addItem(Long productId, Integer quantity, String username){

        Cart cart= getCart(username);

        Product product= productRepository.findById(productId)
                .orElseThrow(()->
                        new RuntimeException("product not found"));

        CartItem item= new CartItem();

        item.setProduct(product);
        item.setQuantity(quantity);
        item.setCart(cart);

        cart.getItems().add(item);

        return  cartRepository.save(cart);
    }
}
