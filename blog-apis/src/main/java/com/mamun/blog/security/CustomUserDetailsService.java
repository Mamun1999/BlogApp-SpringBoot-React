package com.mamun.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mamun.blog.entities.User;
import com.mamun.blog.exceptions.ResourceNotFoundException;
import com.mamun.blog.repositories.UserRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //loading data from databbase by username
      User user=  this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email " +username , 0));
        return user;
    }
    
}
