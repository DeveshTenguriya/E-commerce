package com.example.E_commerce.Service.Impl;

import com.example.E_commerce.Dto.request.productRequest;
import com.example.E_commerce.Dto.response.productResponse;
import com.example.E_commerce.Entity.Category;
import com.example.E_commerce.Entity.Product;
import com.example.E_commerce.Repository.CategoryRepository;
import com.example.E_commerce.Repository.ProductRepository;
import com.example.E_commerce.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<productResponse> getAll(){

        log.info("Fetching all products");

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public productResponse create(productRequest request, Long categoryId){

        log.info("Creating new product | name={}", request.getName());

        Product product = modelMapper.map(request,Product.class);

        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(()->{
                    log.error("Category not found | categoryId={}", categoryId);
                         return new RuntimeException("Category not found");
                });


        product.setCategory(category);

        Product saveProduct= productRepository.save(product);

        log.info("Product created successfully | productId={} | categoryId={}",
                saveProduct.getId(), categoryId);

       return mapToResponse(saveProduct);
    }

    @Override
    public productResponse update(Long Id , productRequest request){

        log.info("Updating product | productId={}", Id);

        Product product= productRepository.findById(Id)
                .orElseThrow(()->{
                    log.error("Product not found | productId={}", Id);
                       return new RuntimeException("Product not found");
                });

        modelMapper.map(request,product);// this line is equal to all these lines
        //product.setName(request.getName());
        //product.setPrice(request.getPrice());
        //product.setStock(request.getStock());
        //product.setDescription(request.getDescription());

        if (request.getCategoryId() != null) {
            log.debug("Updating category for product | productId={}", Id);

            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() ->{
                            log.error("Category not founded | categoryId={}",
                                    request.getCategoryId());
                           return new RuntimeException("Category not found");
                    });
            product.setCategory(category);
        }

        Product updated = productRepository.save(product);

        log.info("Product updated successfully | productId={}", Id);

         return  mapToResponse(updated);
    }

    @Override
    public void delete(Long Id){

        log.warn("Deleting product | productId={}", Id);

        Product product = productRepository.findById(Id)
                .orElseThrow(()->{
                    log.error("Product does not exists | productId={}", Id);
                       return new RuntimeException("Product not found");
                });

        productRepository.delete(product);
        log.info("Product deleted successfully | productId={}", Id);
    }

    private productResponse mapToResponse(Product product) {
        productResponse response = modelMapper.map(product, productResponse.class);
        response.setCategory(product.getCategory().getName());
        return response;
    }
}
