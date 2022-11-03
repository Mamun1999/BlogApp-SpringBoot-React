package com.mamun.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mamun.blog.entities.Category;
import com.mamun.blog.entities.Post;
import com.mamun.blog.entities.User;
import com.mamun.blog.exceptions.ResourceNotFoundException;
import com.mamun.blog.payloads.PostDto;
import com.mamun.blog.payloads.PostPageInfo;
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
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

       Post savedPost= this.postRepo.save(post);
       
        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {

      Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
       
     
     
      return this.modelMapper.map(post, PostDto.class);
       
    }

    //
    @Override
    public PostPageInfo getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
//   Integer pageNumber=1;
//       Integer  pageSize=5;
    // Sort sort=null;

Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//     if(sortDir.equalsIgnoreCase("asc")){
//         sort=Sort.by(sortBy).ascending();
//     }
//    else{
//     sort=Sort.by(sortBy).descending();
//    }

    //   Pageable p= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending() );
    Pageable p= PageRequest.of(pageNumber, pageSize, sort );
      Page<Post> pages= this.postRepo.findAll(p); //here we get pages according to our pagenumber and page Size
      // then we will show page content (pages )
      //page number
      //page size
      //totalElements
      //total pages
      //lastPage

      List<Post> posts= pages.getContent();

      

    //   List<Post> posts= this.postRepo.findAll();


     List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
  
     PostPageInfo postPageInfo=new PostPageInfo();

     postPageInfo.setContents(postDtos);//number of posts
     postPageInfo.setPageSize(pages.getSize());
     postPageInfo.setPageNumber(pages.getNumber());
     postPageInfo.setTotalElements(pages.getNumberOfElements());
     postPageInfo.setTotalpages(pages.getTotalPages());
     postPageInfo.setLastPage(pages.isLast());
        return postPageInfo;
    }
    //

//     @Override
//     public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
// //   Integer pageNumber=1;
// //       Integer  pageSize=5;

//       Pageable p= PageRequest.of(pageNumber, pageSize);

//       Page<Post> pages= this.postRepo.findAll(p); //here we get pages according to our pagenumber and page Size
//       // then we will show page content (pages )
//       //page number
//       //page size
//       //totalElements
//       //total pages
//       //lastPage

//       List<Post> posts= pages.getContent();

      

//     //   List<Post> posts= this.postRepo.findAll();


//      List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

//         return postDtos;
//     }

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
    public List<PostDto> searchPosts(String keyword) {
        // List<Post> posts= this.postRepo.findByTitleContaining(keyword);//for build in query
        List<Post> posts=this.postRepo.searchByTitle("%"+keyword+"%");

       List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }
    
    public void deletePost(Integer postId){
        Post post= this.postRepo.findById(postId).orElseThrow(()->  new ResourceNotFoundException("Post", "id", postId));
        this.postRepo.delete(post);
    }
}
