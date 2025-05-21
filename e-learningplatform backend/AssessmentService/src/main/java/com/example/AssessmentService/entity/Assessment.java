package com.example.AssessmentService.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "assessment")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assessmentId;
    private Long courseId;
    private Long instructorId;
    private int maxScore;
    @OneToMany(mappedBy = "assessment",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Question> questions;


}
