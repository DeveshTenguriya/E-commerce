package com.example.E_commerce.Controller;

import com.example.E_commerce.Entity.Product;
import com.example.E_commerce.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> create(@RequestBody Product product,@RequestParam Long categoryId) {

        return ResponseEntity.ok(productService.create(product,categoryId));
    }

    @PutMapping(path = "/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> update(@PathVariable Long Id,@RequestBody Product update){
       return ResponseEntity.ok(productService.update(Id, update));
    }

    @DeleteMapping(path = "/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long Id){
        productService.delete(Id);
    }
}
