package com.example.E_commerce.Service;

import com.example.E_commerce.Dto.request.productRequest;
import com.example.E_commerce.Dto.response.productResponse;

import java.util.List;

public interface ProductService {

    List<productResponse> getAll();

    productResponse create(productRequest request , Long categoryId);

    productResponse update(Long id, productRequest request);

    void delete(Long id);
}
