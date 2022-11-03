package com.mamun.blog.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mamun.blog.config.Constants;
import com.mamun.blog.payloads.ApiResponse;
import com.mamun.blog.payloads.PostDto;
import com.mamun.blog.payloads.PostPageInfo;
import com.mamun.blog.services.FileService;
import com.mamun.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
    
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ModelMapper modelMapper;
    
    @Value("${project.image}")//from application.properties file
    private String path;

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
       @RequestParam(value = "pageNumber" ,defaultValue = Constants.PAGE_NUMBER , required = false) Integer pageNumber,
       @RequestParam(value = "pageSize" ,defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
       @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
       @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir
    ){

     

       PostPageInfo postPageInfo= this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

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

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchbyTitle(@PathVariable String keyword){

     List<PostDto> postDtos= this.postService.searchPosts(keyword);

     return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);

    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
      @RequestParam("image") MultipartFile image, 
      @PathVariable Integer postId
    ) throws IOException
    {
      PostDto postDto= this.postService.getPostById(postId); // got id of the post
    String fileName= this.fileService.uploadImage(path, image);// uploadImage method return file name
      //got fie name

   postDto.setImageName(fileName); //setting the file name

  PostDto updatedPost =this.postService.updatePost(postDto, postId); //updated to database

   return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);



    }

    //method to serve files

    @GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
      @PathVariable String imageName, HttpServletResponse response

    ) throws IOException
    {
       InputStream resource= this.fileService.getResource(path, imageName);// getting full file path in inputstream
       response.setContentType(MediaType.IMAGE_JPEG_VALUE);// setting content type as a Image_jepeg_value

       StreamUtils.copy(resource, response.getOutputStream());// sending response using HttpServletResponse


    }


}
