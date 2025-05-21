package com.example.EnrollmentService.controller;

import com.example.EnrollmentService.dto.CourseDto;
import com.example.EnrollmentService.service.EnrollmentService;
import com.example.EnrollmentService.service.impl.EnrollmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollment")
@CrossOrigin(value = "http://localhost:4200/")
public class EnrollmentController {
    @Autowired
    private EnrollmentServiceImpl enrollmentServiceImpl;

    @PostMapping("/enrollCourse")
    public ResponseEntity<?> enrollCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        try {
            enrollmentServiceImpl.enrollment(studentId, courseId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Student enrolled successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/{studentId}/{courseId}")
    public ResponseEntity<?> updateProgress(@PathVariable Long studentId, @PathVariable Long courseId, @RequestParam int progress){
        try {
            String result = enrollmentServiceImpl.updateProgress(studentId, courseId, progress);
            return ResponseEntity.ok(result);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{studentId}/{courseId}/progress")
    public ResponseEntity<?> getProgress(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            Integer progress = enrollmentServiceImpl.getProgress(studentId, courseId);
            return ResponseEntity.ok(progress);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{studentId}/enrolled-course")
    public ResponseEntity<List<CourseDto>> getEnrolledCourses(@PathVariable Long studentId) {
        List<CourseDto> enrolledCourses = enrollmentServiceImpl.getEnrolledCourses(studentId);
        return ResponseEntity.ok(enrolledCourses);
    }



}
