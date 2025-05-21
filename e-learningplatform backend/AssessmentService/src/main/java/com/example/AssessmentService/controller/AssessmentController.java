package com.example.AssessmentService.controller;

import com.example.AssessmentService.dto.AssessmentDto;
import com.example.AssessmentService.entity.Assessment;
import com.example.AssessmentService.service.impl.AssessmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessment")
@CrossOrigin(value = "http://localhost:4200")
public class AssessmentController {
    @Autowired
    private AssessmentServiceImpl assessmentServiceImpl;

    @PostMapping("/createAssessment")
    private ResponseEntity<?> createAssessment(@RequestBody Assessment assessment, @RequestParam Long userId, @RequestParam Long courseId){
        try {
            Assessment savedAssessment =  assessmentServiceImpl.createAssessment(assessment,userId,courseId);
            return ResponseEntity.ok(savedAssessment);
        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getAssessment")
    public ResponseEntity<?> getAssessmentByCourse(@RequestParam Long courseId) {
        try {
            List<Assessment> getAssessment = assessmentServiceImpl.getAssessmentByCourse(courseId);
            return ResponseEntity.ok(getAssessment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteAssessment")
    private ResponseEntity<?> deleteAssessment(@RequestParam Long userId,@RequestParam Long assessmentId){
        try {
            assessmentServiceImpl.deleteAssessmentById(userId,assessmentId);
            return ResponseEntity.ok("Assessment deleted successfully");
        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    private AssessmentDto getAssessmentById(@PathVariable Long id){
        return assessmentServiceImpl.getAssessmentById(id);
    }

}
