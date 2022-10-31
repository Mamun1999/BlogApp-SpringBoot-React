package com.mamun.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mamun.blog.entities.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    
}
