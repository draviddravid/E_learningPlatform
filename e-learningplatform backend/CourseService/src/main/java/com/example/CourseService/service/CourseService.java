package com.example.CourseService.service;

import com.example.CourseService.dto.CourseDto;
import com.example.CourseService.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    CourseDto createCourse(Course course,Long userId);
    List<CourseDto> getAllCourse();
    CourseDto getCourseById(Long id);
    void deleteCourse(Long id,Long userId);
    List<CourseDto> getCoursesByInstructorId(Long instructorId);

}
