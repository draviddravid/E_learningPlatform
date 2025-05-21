package com.example.EnrollmentService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDto {
    private Long courseId;
    private String title;
    private String description;
    private String contentURL;
    private Long instructorId;
}
