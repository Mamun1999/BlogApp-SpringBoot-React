package com.mamun.blog.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mamun.blog.entities.User;
import com.mamun.blog.payloads.UserDto;
import com.mamun.blog.repositories.UserRepo;
import com.mamun.blog.services.UserService;

public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user= this.dtoToUser(userDto);

        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateuser(UserDto user, Integer userId) {
     
        return null;
    }

    @Override
    public UserDto getUserById(Integer userId) {
       
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
     
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {
    
        
    }
    
    public User dtoToUser(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPasssword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPasssword());
        userDto.setAbout(user.getAbout());

        return userDto;
    }
}
