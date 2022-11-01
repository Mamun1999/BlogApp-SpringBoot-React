package com.mamun.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mamun.blog.entities.Category;
import com.mamun.blog.entities.Post;
import com.mamun.blog.payloads.PostDto;

@Service
public interface PostService {
    PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

    Post updatePost(PostDto postDto, Integer postId);

    PostDto getPostById(Integer postId);

    List<PostDto> getAllPost();

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<Post> searchPosts(String keyword);
    
}
