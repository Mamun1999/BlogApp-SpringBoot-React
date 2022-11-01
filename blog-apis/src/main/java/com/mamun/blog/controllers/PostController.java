package com.mamun.blog.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mamun.blog.payloads.ApiResponse;
import com.mamun.blog.payloads.PostDto;
import com.mamun.blog.payloads.PostPageInfo;
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
    public ResponseEntity<PostPageInfo> getAllPost(
       @RequestParam(value = "pageNumber" ,defaultValue = "0" , required = false) Integer pageNumber,
       @RequestParam(value = "pageSize" ,defaultValue = "10", required = false) Integer pageSize
    ){

     

       PostPageInfo postPageInfo= this.postService.getAllPost(pageNumber, pageSize);

       return new ResponseEntity<PostPageInfo>(postPageInfo, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSingelPost(@PathVariable Integer postId){
       PostDto postDto= this.postService.getPostById(postId);

       return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @RequestBody PostDto postDto){
            PostDto updatedPost= this.postService.updatePost(postDto, postId);

            return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
         
      this.postService.deletePost(postId);
     return new ResponseEntity<>(new ApiResponse("Post Deleted successfully", true),HttpStatus.OK);
    }
}
