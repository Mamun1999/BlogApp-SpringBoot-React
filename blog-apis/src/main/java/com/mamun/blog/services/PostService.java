package com.mamun.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mamun.blog.entities.Post;
import com.mamun.blog.payloads.PostDto;
import com.mamun.blog.payloads.PostPageInfo;

@Service
public interface PostService {
    PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

    PostDto updatePost(PostDto postDto, Integer postId);

    PostDto getPostById(Integer postId);

    PostPageInfo getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);
    void  deletePost(Integer postId);
    
}
