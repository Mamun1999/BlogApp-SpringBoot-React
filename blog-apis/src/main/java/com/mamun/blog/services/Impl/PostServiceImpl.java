package com.mamun.blog.services.Impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mamun.blog.entities.Category;
import com.mamun.blog.entities.Post;
import com.mamun.blog.entities.User;
import com.mamun.blog.exceptions.ResourceNotFoundException;
import com.mamun.blog.payloads.PostDto;
import com.mamun.blog.repositories.CategoryRepo;
import com.mamun.blog.repositories.PostRepo;
import com.mamun.blog.repositories.UserRepo;
import com.mamun.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {

       Category category= this.categoryRepo.findById(categoryId).orElseThrow(()->
            new ResourceNotFoundException("Category", "categoryId", categoryId));
        
       User user=this.userRepo.findById(userId).orElseThrow(() -> 
            new ResourceNotFoundException("User"," Id ",userId));

        Post post=this.modelMapper.map(postDto, Post.class);
        
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);


       Post newPost= this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getAllPost() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostByCategory(Integer categoryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostByUser(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
