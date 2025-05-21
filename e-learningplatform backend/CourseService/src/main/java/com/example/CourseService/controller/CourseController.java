package com.example.CourseService.controller;

import com.example.CourseService.dto.CourseDto;
import com.example.CourseService.entity.Course;
import com.example.CourseService.service.CourseService;
import com.example.CourseService.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
@CrossOrigin(value = "http://localhost:4200/")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @PostMapping("/createCourse/{userId}")
    public ResponseEntity<?> createCourse(@RequestBody Course course,@PathVariable Long userId){
        try{
            CourseDto createdCourse=courseServiceImpl.createCourse(course,userId);
            return ResponseEntity.ok(createdCourse);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllCourse")
    public List<CourseDto> getAllCourse(){
        return courseServiceImpl.getAllCourse();
    }

    @GetMapping("/{id}")
    public CourseDto getCourseById(@PathVariable Long id){
        return courseServiceImpl.getCourseById(id);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId,@RequestParam Long userId){
        try{
            courseServiceImpl.deleteCourse(courseId,userId);
            return ResponseEntity.ok("Deleted successfully");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/instructor/{instructorId}")
    public List<CourseDto> getCoursesByInstructorId(@PathVariable Long instructorId) {
        return courseServiceImpl.getCoursesByInstructorId(instructorId);
    }
}
