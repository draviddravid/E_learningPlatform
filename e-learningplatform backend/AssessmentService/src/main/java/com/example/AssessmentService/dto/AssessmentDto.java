package com.example.AssessmentService.dto;

import com.example.AssessmentService.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class AssessmentDto {
    private Long assessmentId;
    private Long courseId;
    private Long instructorId;
    private List<QuestionDto> questions;
    private int maxScore;

}
