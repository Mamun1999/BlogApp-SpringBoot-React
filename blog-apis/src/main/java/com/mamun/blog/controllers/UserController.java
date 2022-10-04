package com.mamun.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mamun.blog.payloads.UserDto;
import com.mamun.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    public UserService userService;
    
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){

       UserDto createdUser= this.userService.createUser(userDto);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }
}
