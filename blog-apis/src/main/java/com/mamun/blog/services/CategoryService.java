package com.mamun.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mamun.blog.payloads.CategoryDto;

@Service
public interface CategoryService {
    
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer catgoryId);

    CategoryDto getCategoryById(Integer categoryId);

    List<CategoryDto> getAllCategory();

    void deleteCategory(Integer categoryId);
}
