package com.example.E_commerce.Service.Impl;

import com.example.E_commerce.Dto.request.productRequest;
import com.example.E_commerce.Dto.response.productResponse;

import java.util.List;

public interface productService {

    List<productResponse> getAllProducts();

    productResponse createProduct(productRequest request);

    productResponse updateProduct(Long id, productRequest request);

    void deleteProduct(Long id);
}
