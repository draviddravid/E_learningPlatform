package com.example.SubmissionService.service;


import com.example.SubmissionService.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:4002",name="course-service")
public interface CourseFeignClient {
    @GetMapping("course/{id}")
    CourseDto getCourseById(@PathVariable Long id) ;
}
