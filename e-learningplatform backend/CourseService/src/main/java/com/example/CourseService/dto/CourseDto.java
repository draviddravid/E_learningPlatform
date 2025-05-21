package com.example.CourseService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Long courseId;
    private String title;
    private String description;
    private String contentURL;
    private Long instructorId;
}
