package com.example.UserService.service;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;

import java.util.List;

public interface UserService {
    UserDto registerUser(User user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUser();
    UserDto update(User user);
    void deleteUser(Long id);
    boolean userExistsByEmail(String email);

}
