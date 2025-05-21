package com.example.EnrollmentService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnrollmentDto {
    private Long enrollmentId;
    private Long studentId;
    private Long courseId;
    private int progress;


}
