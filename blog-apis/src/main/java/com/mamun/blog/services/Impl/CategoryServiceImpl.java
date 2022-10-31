package com.mamun.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mamun.blog.entities.Category;
import com.mamun.blog.exceptions.ResourceNotFoundException;
import com.mamun.blog.payloads.CategoryDto;
import com.mamun.blog.repositories.CategoryRepo;
import com.mamun.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       Category category= this.dtoToCategory(categoryDto);
       Category savedCategory= this.categoryRepo.save(category);
        
        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->
        new ResourceNotFoundException("Category"," id", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

       Category savedCategory= this.categoryRepo.save(category);
        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
       Category category= this.categoryRepo.findById(categoryId).orElseThrow(()->
        
        new ResourceNotFoundException("Category", "Id", categoryId));

      

        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
      List<Category> categoryList= this.categoryRepo.findAll();
       List<CategoryDto> categoryDto= categoryList.stream().map(category->this.categoryToDto(category)).collect(Collectors.toList());
       return categoryDto;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
       Category category= this.categoryRepo.findById(categoryId).orElseThrow(()->
       new ResourceNotFoundException("Category"," id", categoryId));
        this.categoryRepo.delete(category);


        
    }

   public Category dtoToCategory(CategoryDto categoryDto){
       Category category= this.modelMapper.map(categoryDto, Category.class);

        return category;
    }
    public CategoryDto categoryToDto(Category category){
       CategoryDto categoryDto= this.modelMapper.map(category, CategoryDto.class);
     
       return categoryDto;

    }
}
