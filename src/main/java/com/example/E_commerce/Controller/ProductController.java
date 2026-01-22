package com.example.E_commerce.Controller;

import com.example.E_commerce.Entity.Product;
import com.example.E_commerce.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/admin/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> create(@RequestBody Product product,@PathVariable Long categoryId) {

        return ResponseEntity.ok(productService.create(product,categoryId));
    }
}
