package com.example.UserService.controller;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:4200/")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getAllUser")
    public List<UserDto> getAllUsers(){
        return userServiceImpl.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try{
            UserDto userDto=userServiceImpl.getUserById(id);
            return ResponseEntity.ok(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/users/exists")
    public ResponseEntity<Boolean> userExists(@RequestParam String email) {
        boolean exists = userServiceImpl.userExistsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> getUser=userRepository.findByEmail(user.getEmail());
        String password=user.getPassword();
        if (getUser.isPresent() && getUser.get().getPassword().equals(password)) {
            return ResponseEntity.ok(getUser);
        } else {
            return ResponseEntity.status(401).build();
        }


    }
    @PostMapping("/register")
    public UserDto registerUser(@RequestBody User user) {
        return userServiceImpl.registerUser(user);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> update(@RequestBody User user){
        try {
            UserDto updatedUser=userServiceImpl.update(user);
            return ResponseEntity.ok(updatedUser);
        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try {
            userServiceImpl.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
