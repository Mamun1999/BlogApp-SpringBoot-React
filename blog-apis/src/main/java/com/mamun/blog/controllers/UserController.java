package com.mamun.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mamun.blog.payloads.ApiResponse;
import com.mamun.blog.payloads.UserDto;
import com.mamun.blog.repositories.UserRepo;
import com.mamun.blog.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    public UserService userService;

    @Autowired
    public UserRepo userRepo;
    
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){

       UserDto createdUser= this.userService.createUser(userDto);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }
    //PUT
  
    @PutMapping(value="/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
       
       UserDto updatedUser= this.userService.updateuser(userDto, userId);
        
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    

    //DELETE
    @PreAuthorize("hasRole('ADMIN')")  
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){

        this.userService.deleteUser(userId);


       return new ResponseEntity<>(new ApiResponse("User deleted successfully", true),HttpStatus.OK);
     

    }


    //gET

    @GetMapping(value="/")
    public ResponseEntity <List<UserDto>> getAllUsers() {
      
     return ResponseEntity.ok(this.userService.getAllUsers());
       
    
}

@GetMapping("/{userId}")
public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
  
 return ResponseEntity.ok(this.userService.getUserById(userId));
   

}

}