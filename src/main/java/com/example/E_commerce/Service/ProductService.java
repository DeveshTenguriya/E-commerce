package com.example.E_commerce.Service;

import com.example.E_commerce.Entity.Category;
import com.example.E_commerce.Entity.Product;
import com.example.E_commerce.Repository.CategoryRepository;
import com.example.E_commerce.Repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product create(Product product, Long categoryId){

        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(()->
                        new RuntimeException("Category not found"));

        product.setCategory(category);
       return productRepository.save(product);
    }

    public Product update(Long Id , Product update){
        Product product= productRepository.findById(Id)
                .orElseThrow(()->
                        new RuntimeException("Product not found"));

        product.setName(update.getName());
        product.setPrice(update.getPrice());
        product.setStock(update.getStock());
        product.setDescription(update.getDescription());

         return  productRepository.save(product);
    }

    public void delete(Long Id){

        Product product = productRepository.findById(Id)
                .orElseThrow(()->
                        new RuntimeException("Product not found"));

        productRepository.deleteById(Id);
    }
}
