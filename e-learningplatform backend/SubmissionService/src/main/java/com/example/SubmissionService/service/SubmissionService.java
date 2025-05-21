package com.example.SubmissionService.service;

import com.example.SubmissionService.dto.SubmissionDto;
import com.example.SubmissionService.entity.Submission;

import java.util.List;

public interface SubmissionService {
    Submission submitSubmission(SubmissionDto submissionDto);
    List<Submission> getSubmissionByStudent(Long studentId);

}
