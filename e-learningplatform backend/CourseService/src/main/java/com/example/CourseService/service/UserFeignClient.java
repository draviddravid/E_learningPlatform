package com.example.CourseService.service;

import com.example.CourseService.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:4001",name="user-service")
public interface UserFeignClient {
    @GetMapping("user/{id}")
    UserDto getUserById(@PathVariable Long id);
}
