package com.example.CourseService.service.impl;

import com.example.CourseService.dto.CourseDto;
import com.example.CourseService.dto.UserDto;
import com.example.CourseService.entity.Course;
import com.example.CourseService.repository.CourseRepository;
import com.example.CourseService.service.CourseService;
import com.example.CourseService.service.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public CourseDto createCourse(Course course,Long userId) {
        UserDto user=userFeignClient.getUserById(userId);
        if(!user.getRole().equalsIgnoreCase("INSTRUCTOR")){
            throw new RuntimeException("only instructor can create course");
        }
        course.setInstructorId(userId);
        Course savedCourse=courseRepository.save(course);
        return new CourseDto(savedCourse.getCourseId(),savedCourse.getTitle(),savedCourse.getDescription(),savedCourse.getContentURL(), savedCourse.getInstructorId());
    }

    @Override
    public List<CourseDto> getAllCourse() {
        List<Course> courses=courseRepository.findAll();
        List<CourseDto> courseDtos=new ArrayList<>();
        for(Course course:courses){
            courseDtos.add(new CourseDto(course.getCourseId(),course.getTitle(),course.getDescription(),course.getContentURL(),course.getInstructorId()));
        }
        return courseDtos;
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no course belongs to this id!"));

        // Convert Course entity to CourseDto
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(course.getCourseId()); // Correctly set the courseId
        courseDto.setTitle(course.getTitle());
        courseDto.setInstructorId(course.getInstructorId());
        courseDto.setDescription(course.getDescription());
        courseDto.setContentURL(course.getContentURL());
        return courseDto;
    }

    @Override
    public void deleteCourse(Long id, Long userId) {
        UserDto user=userFeignClient.getUserById(userId);
        if(user==null){
            throw new RuntimeException("user not found");
        }
        Course course=courseRepository.findById(id).orElseThrow(()->new RuntimeException("course not found"));
        if(!user.getRole().equalsIgnoreCase("INSTRUCTOR")||!course.getInstructorId().equals(userId)){
            throw new RuntimeException("you are not allowed to delete");
        }
        else{
            courseRepository.deleteById(id);
        }

    }

    @Override
    public List<CourseDto> getCoursesByInstructorId(Long instructorId) {
        List<Course> courses = courseRepository.findByInstructorId(instructorId);
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            courseDtos.add(new CourseDto(course.getCourseId(), course.getTitle(), course.getDescription(), course.getContentURL(), course.getInstructorId()));
        }
        return courseDtos;
    }


}
