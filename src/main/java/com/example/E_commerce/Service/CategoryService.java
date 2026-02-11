package com.example.E_commerce.Service;

import com.example.E_commerce.Entity.Category;
import com.example.E_commerce.Repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(String name){

        log.info("Creating new category | name={}", name);

        Category category= new Category();

        category.setName(name);

        Category savedCategory= categoryRepository.save(category);

        log.info("Category created successfully | categoryId={} | name={}",
                savedCategory.getId(),
                savedCategory.getName());

        return savedCategory;
    }

    public List<Category> getAll(){

        log.info("Fetching all categories");

        List<Category> categories = categoryRepository.findAll();

        log.info("Total categories fetched: {}", categories.size());

        return categories;
    }
}
