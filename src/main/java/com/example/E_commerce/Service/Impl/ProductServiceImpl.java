package com.example.E_commerce.Service.Impl;

import com.example.E_commerce.Dto.request.productRequest;
import com.example.E_commerce.Dto.response.productResponse;
import com.example.E_commerce.Entity.Category;
import com.example.E_commerce.Entity.Product;
import com.example.E_commerce.Repository.CategoryRepository;
import com.example.E_commerce.Repository.ProductRepository;
import com.example.E_commerce.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public productResponse create(productRequest request, Long categoryId){

        Product product = modelMapper.map(request,Product.class);

        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(()->
                        new RuntimeException("Category not found"));

        product.setCategory(category);

        Product saveProduct= productRepository.save(product);
       return mapToResponse(saveProduct);
    }

    @Override
    public productResponse update(Long Id , productRequest request){
        Product product= productRepository.findById(Id)
                .orElseThrow(()->
                        new RuntimeException("Product not found"));

        modelMapper.map(request,product);// this line is equal to all these lines
        //product.setName(request.getName());
        //product.setPrice(request.getPrice());
        //product.setStock(request.getStock());
        //product.setDescription(request.getDescription());

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        Product updated = productRepository.save(product);
         return  mapToResponse(updated);
    }

    @Override
    public void delete(Long Id){

        Product product = productRepository.findById(Id)
                .orElseThrow(()->
                        new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    private productResponse mapToResponse(Product product) {
        productResponse response = modelMapper.map(product, productResponse.class);
        response.setCategory(product.getCategory().getName());
        return response;
    }
}
