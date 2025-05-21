package com.example.EnrollmentService.service.impl;

import com.example.EnrollmentService.dto.CourseDto;
import com.example.EnrollmentService.dto.EnrollmentDto;
import com.example.EnrollmentService.dto.UserDto;
import com.example.EnrollmentService.entity.Enrollment;
import com.example.EnrollmentService.repository.EnrollmentRepository;
import com.example.EnrollmentService.service.CourseFeignClient;
import com.example.EnrollmentService.service.EnrollmentService;
import com.example.EnrollmentService.service.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private CourseFeignClient courseFeignClient;


    @Override
    public void enrollment(Long studentId, Long courseId) {
        UserDto student=userFeignClient.getUserById(studentId);
        if(student==null){
            throw new RuntimeException("student not found");
        }
        CourseDto course=courseFeignClient.getCourseById(courseId);
        if(course==null){
            throw new RuntimeException("Course not found");
        }

        Enrollment existingEnrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (existingEnrollment != null) {
            throw new RuntimeException("Student is already enrolled");
        }

        // Create a new enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollment.setProgress(0);
        // Save the enrollment
        Enrollment savedEnrollment=enrollmentRepository.save(enrollment);
        new EnrollmentDto(savedEnrollment.getEnrollmentId(), savedEnrollment.getStudentId(), savedEnrollment.getCourseId(), savedEnrollment.getProgress());
    }

    @Override
    public String updateProgress(Long studentId, Long courseId, int progress) {
        Enrollment enrolledCourse=enrollmentRepository.findByStudentIdAndCourseId(studentId,courseId);
        if(enrolledCourse!=null){
            enrolledCourse.setProgress(progress);
            enrollmentRepository.save(enrolledCourse);
            return "Progress updated: " + progress + "%";
        }else{
            throw new RuntimeException("Enrollment not found");
        }

    }

    @Override
    public Integer getProgress(Long studentId, Long courseId) {
        Enrollment enrolledCourse=enrollmentRepository.findByStudentIdAndCourseId(studentId,courseId);
        if(enrolledCourse!=null){
            return enrolledCourse.getProgress();
        }else{
            throw new RuntimeException("Enrollment not found");
        }

    }

    @Override
    public boolean isStudentEnrolled(Long studentId, Long courseId) {
        return enrollmentRepository.findByStudentIdAndCourseId(studentId,courseId)!=null;
    }

    @Override
    public List<CourseDto> getEnrolledCourses(Long studentId) {
        // Fetch course IDs for the given student
        List<Long> courseIds = enrollmentRepository.findByStudentId(studentId).stream()
                .map(Enrollment::getCourseId)
                .collect(Collectors.toList());

        // Fetch course details for each course ID and remove duplicates
        return courseIds.stream()
                .distinct() // Remove duplicate course IDs
                .map(courseFeignClient::getCourseById) // Fetch course details using Feign client
                .collect(Collectors.toList());
    }


}
