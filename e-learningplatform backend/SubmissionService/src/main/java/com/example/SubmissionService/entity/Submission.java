package com.example.SubmissionService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "submission")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;
    private Long assessmentId;
    private Long studentId;
    private int score;
}
