package com.mamun.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mamun.blog.entities.Comment;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    
}
