package com.example.SubmissionService.dto;

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
