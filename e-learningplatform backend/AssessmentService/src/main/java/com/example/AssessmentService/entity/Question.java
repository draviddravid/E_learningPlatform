package com.example.AssessmentService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    private String questionText;
    private List<String> options;
    private String correctAns;


}
