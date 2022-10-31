package com.mamun.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mamun.blog.entities.Category;
import com.mamun.blog.entities.Post;
import com.mamun.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
