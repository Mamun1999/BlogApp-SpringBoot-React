package com.mamun.blog.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mamun.blog.entities.User;
import com.mamun.blog.exceptions.ApiException;
import com.mamun.blog.payloads.JwtAuthRequest;
import com.mamun.blog.payloads.JwtAuthResponse;
import com.mamun.blog.payloads.UserDto;
import com.mamun.blog.security.JwtTokenHelper;
import com.mamun.blog.services.UserService;

@RestController
@RequestMapping("/api/auth/")
// @CrossOrigin("*")
public class AuthController {
    
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authententicationManager;
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{
               
        this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());

        //after authentication done then we have to generate token

       UserDetails userDetails= this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

      String token=  this.jwtTokenHelper.generateToken(userDetails); //we have to pass user details(username) for generating token

      JwtAuthResponse response=new JwtAuthResponse();
      response.setToken(token);
      response.setUser(this.modelMapper.map((User)userDetails, UserDto.class));// by this we will call user and token in frontend local storage
      return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);


    }
     @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@Valid @RequestBody UserDto userDto){

       UserDto createdUser= this.userService.registerUser(userDto);

       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    public void authenticate(String username, String password) throws Exception{


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username, password);

       try {
        this.authententicationManager.authenticate(usernamePasswordAuthenticationToken);

       } catch (BadCredentialsException e) {
        System.out.println("Invalid details");
        throw new ApiException("Invalid username or password");
        // TODO: handle exception
       }
    }
}
