package com.example.SubmissionService.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SubmissionDto {
    private Long submissionId;
    private Long assessmentId;
    private Long studentId;
    private Map<Long,String> ans;

}
