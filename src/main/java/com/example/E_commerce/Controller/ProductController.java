package com.example.E_commerce.Controller;

import com.example.E_commerce.Entity.Product;
import com.example.E_commerce.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    //Why ResponseEntity is Important
    //1️ Control HTTP Status Codes
    //Correct REST behavior:
    //
    //POST → 201 CREATED
    //
    //PUT → 200 OK
    //
    //DELETE → 204 NO CONTENT
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER','ADMIN')")
    public ResponseEntity<List<Product>> getAllProducts(){
       return ResponseEntity.ok(productService.getAll());
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
