package com.mamun.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        
        return null;
    }

    @Override
    public PostDto getPostById(Integer postId) {

      Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
       
     
     
      return this.modelMapper.map(post, PostDto.class);
       
    }

    @Override
    public List<PostDto> getAllPost() {
      List<Post> posts= this.postRepo.findAll();
     List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
      Category category= this.categoryRepo.findById(categoryId).orElseThrow(()->
       new ResourceNotFoundException("category", "Id", categoryId));
      
      List<Post> posts= this.postRepo.findByCategory(category);

     List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());




        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
         List<Post> posts= this.postRepo.findByUser(user);

       List<PostDto> postDtos=  posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
