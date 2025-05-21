package com.example.AssessmentService.dto;

import com.example.AssessmentService.entity.Assessment;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private Long questionId;
    private Long assessmentId;
    private String questionText;
    private List<String> options;
    private String correctAns;

}
