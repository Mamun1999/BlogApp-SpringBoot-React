package com.mamun.blog.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.mamun.blog.payloads.UserDto;

@Service
public interface UserService {

    UserDto registerUser(UserDto userDto);// for register new normal user

    UserDto createUser(UserDto user);

    UserDto updateuser(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
    
}
