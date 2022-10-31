package com.mamun.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
       @PathVariable Integer userId, @PathVariable Integer categoryId ,@RequestBody PostDto postDto){
        
       PostDto created= this.postService.createPost(postDto, categoryId, userId);

        return new ResponseEntity<PostDto>(created,HttpStatus.CREATED);
        
        

    }
}
