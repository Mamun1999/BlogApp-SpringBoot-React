package com.mamun.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mamun.blog.entities.Comment;
import com.mamun.blog.entities.Post;
import com.mamun.blog.exceptions.ResourceNotFoundException;
import com.mamun.blog.payloads.CommentDto;
import com.mamun.blog.repositories.CommentRepo;
import com.mamun.blog.repositories.PostRepo;
import com.mamun.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
     
    @Autowired
     private PostRepo postRepo;

     @Autowired
     private CommentRepo commentRepo;

     @Autowired
     private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
       
   Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
   
   Comment comment= this.modelMapper.map(commentDto, Comment.class);
       comment.setContent(commentDto.getContent());
       comment.setPost(post);
      Comment savedComment= this.commentRepo.save(comment);
    return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
     
       Comment comment= this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
        
       this.commentRepo.delete(comment);
    }
    
   
}
