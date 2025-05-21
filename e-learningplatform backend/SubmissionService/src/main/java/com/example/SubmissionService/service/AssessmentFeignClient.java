package com.example.SubmissionService.service;

import com.example.SubmissionService.dto.AssessmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:4004",name="Assessment-service")
public interface AssessmentFeignClient {
    @GetMapping("assessment/{id}")
    AssessmentDto getAssessmentById(@PathVariable Long id);
}
