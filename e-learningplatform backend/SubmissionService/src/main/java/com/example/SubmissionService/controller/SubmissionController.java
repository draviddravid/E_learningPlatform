package com.example.SubmissionService.controller;

import com.example.SubmissionService.dto.SubmissionDto;
import com.example.SubmissionService.entity.Submission;
import com.example.SubmissionService.service.impl.SubmissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
@CrossOrigin(value = "http://localhost:4200/")
public class SubmissionController {
    @Autowired
    private SubmissionServiceImpl submissionServiceImpl;

    @PostMapping("/submit")
    public ResponseEntity<?> submitSubmission(@RequestBody SubmissionDto submissionDto){
        try{
            Submission submission=submissionServiceImpl.submitSubmission(submissionDto);
            return ResponseEntity.ok(submission);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/getSubmission/{studentId}")
    public ResponseEntity<?> getSubmissionByStudent(@PathVariable Long studentId){
        try{
            List<Submission> submissions=submissionServiceImpl.getSubmissionByStudent(studentId);
            return ResponseEntity.ok(submissions);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
