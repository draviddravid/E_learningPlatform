package com.example.EnrollmentService.service;

import com.example.EnrollmentService.dto.CourseDto;

import java.util.List;

public interface EnrollmentService {
    void enrollment(Long studentId, Long courseId);
    String updateProgress (Long studentId, Long courseId, int progress);
    Integer getProgress(Long studentId,Long courseId);
    boolean isStudentEnrolled(Long studentId,Long courseId);
    List<CourseDto> getEnrolledCourses(Long studentId);

}
