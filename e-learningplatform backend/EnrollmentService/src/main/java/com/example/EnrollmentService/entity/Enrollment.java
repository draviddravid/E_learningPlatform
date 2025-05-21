package com.example.EnrollmentService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;
    private Long studentId;
    private Long courseId;
    private int progress;
}
