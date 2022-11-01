package com.mamun.blog.controllers;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mamun.blog.entities.Post;
import com.mamun.blog.payloads.PostDto;
import com.mamun.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
    
    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
       @PathVariable Integer userId, @PathVariable Integer categoryId ,@RequestBody PostDto postDto){
        
       PostDto created= this.postService.createPost(postDto, categoryId, userId);

        return new ResponseEntity<PostDto>(created,HttpStatus.CREATED);
        
        

    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        
       List<PostDto> postDtos=this.postService.getPostByUser(userId);
       return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer userId,
    @PathVariable Integer categoryId){
       List<PostDto> postDtos= this.postService.getPostByCategory(categoryId);

       return new ResponseEntity<>(postDtos, HttpStatus.OK);

    }


    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(){
       List<PostDto> postDtos= this.postService.getAllPost();

       return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSingelPost(@PathVariable Integer postId){
       PostDto postDto= this.postService.getPostById(postId);

       return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }
}
