package com.mamun.blog.services.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mamun.blog.entities.User;
import com.mamun.blog.exceptions.ResourceNotFoundException;
import com.mamun.blog.payloads.UserDto;
import com.mamun.blog.repositories.UserRepo;
import com.mamun.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user= this.dtoToUser(userDto);

        User savedUser=this.userRepo.save(user);//as we create entity User in db thats why we must convert userdto to user
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateuser(UserDto userDto, Integer userId) {
         User user=this.userRepo.findById(userId).orElseThrow(() -> 
         new ResourceNotFoundException("User"," Id ",userId));

         user.setName(userDto.getName());
         user.setEmail(userDto.getEmail());
         user.setPasssword(userDto.getPassword());
         user.setAbout(userDto.getAbout());
         
         //as we create entity User in db thats why we must convert userdto to user

         User updatedUser= this.userRepo.save(user);
         UserDto userDto2=this.userToDto(updatedUser);
        return userDto2;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(() -> 
        new ResourceNotFoundException("User"," Id ",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
     
        List<User> users=this.userRepo.findAll();
       List<UserDto> userDtos= users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(() -> 
        new ResourceNotFoundException("User"," Id ",userId));

        this.userRepo.delete(user);
    }
    
    public User dtoToUser(UserDto userDto){
        User user= this.modelMapper.map(userDto, User.class);
        // User user=new User();
        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPasssword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());

        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user, UserDto.class);
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setPassword(user.getPasssword());
        // userDto.setAbout(user.getAbout());

        return userDto;
    }
}
