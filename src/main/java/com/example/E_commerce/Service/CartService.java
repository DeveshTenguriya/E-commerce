package com.example.E_commerce.Service;

import com.example.E_commerce.Entity.Cart;
import com.example.E_commerce.Entity.CartItem;
import com.example.E_commerce.Entity.Product;
import com.example.E_commerce.Entity.User;
import com.example.E_commerce.Repository.CartRepository;
import com.example.E_commerce.Repository.ProductRepository;
import com.example.E_commerce.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

        log.info("Fetching cart for user | email={}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->{
                    log.error("User not found while fetching cart | email={}", email);
                     return new RuntimeException("User not found");
                });

        Cart cart= cartRepository.findByUser(user)
                .orElseGet(() -> {
                    log.info("Cart not found, creating new cart | email={}", email);
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        log.debug("Cart retrieved successfully | cartId={}", cart.getId());

        return cart;
    }


    public Cart addItem(Long productId, Integer quantity, String username){

        log.info("Adding item to cart | productId={} | quantity={} | user={}",
                productId, quantity, username);

        Cart cart= getCart(username);

        Product product= productRepository.findById(productId)
                .orElseThrow(()->{
                    log.error("Product not found while adding to cart | productId={}",
                            productId);
                    return new RuntimeException("product not found");
                });

        CartItem item= new CartItem();

        item.setProduct(product);
        item.setQuantity(quantity);
        item.setCart(cart);

        cart.getItems().add(item);

        return  cartRepository.save(cart);
    }
}
