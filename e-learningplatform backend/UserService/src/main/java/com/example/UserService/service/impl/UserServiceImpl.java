package com.example.UserService.service.impl;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto registerUser(User user) {
        User savedUser=userRepository.save(user);
        return new UserDto(savedUser.getId(),savedUser.getName(),savedUser.getEmail(),savedUser.getRole());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user=userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    @Override
    public List<UserDto> getAllUser(){
        List<User> users=userRepository.findAll();
        List<UserDto> userDtos=new ArrayList<>();

        for(User user:users){
            userDtos.add(new UserDto(user.getId(),user.getName(),user.getEmail(),user.getRole()));
        }
        return userDtos;
    }

    @Override
    public UserDto update(User user) {
        Optional<User> existingUserOptional = userRepository.findById(user.getId());
        if(existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setName(user.getName());
            existingUser.setRole(user.getRole());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            User updatedUser= userRepository.save(existingUser);
            return new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getRole());
        }
        else {
            throw new RuntimeException("User not found with id "+user.getId());
        }
    }

    @Override
    public void deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("User not found with id:"+ id);
        }
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
