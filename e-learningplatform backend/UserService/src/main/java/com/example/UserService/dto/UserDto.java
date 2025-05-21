package com.example.UserService.dto;

import com.example.UserService.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Setter;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    @JsonIgnore
    private String password;

    public UserDto() {
    }

    public UserDto(Long id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }


    public UserDto(Long id, String name, String email, Role role, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}
