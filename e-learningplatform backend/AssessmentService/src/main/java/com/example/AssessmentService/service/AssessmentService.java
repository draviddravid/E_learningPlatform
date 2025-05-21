package com.example.AssessmentService.service;

import com.example.AssessmentService.dto.AssessmentDto;
import com.example.AssessmentService.entity.Assessment;

import java.util.List;

public interface AssessmentService {
    Assessment createAssessment(Assessment assessment, Long userId, Long courseId);
    List<Assessment> getAssessmentByCourse(Long courseId);
    void deleteAssessmentById(Long userId, Long assessmentId);
    AssessmentDto getAssessmentById(Long assessmentId);
}
