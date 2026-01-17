package com.example.E_commerce.Service;

import com.example.E_commerce.Entity.Category;
import com.example.E_commerce.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(String name){

        Category category= new Category();

        category.setName(name);

        return categoryRepository.save(category);
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
}
