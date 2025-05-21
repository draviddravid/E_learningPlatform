package com.example.AssessmentService.service.impl;

import com.example.AssessmentService.dto.AssessmentDto;
import com.example.AssessmentService.dto.CourseDto;
import com.example.AssessmentService.dto.QuestionDto;
import com.example.AssessmentService.dto.UserDto;
import com.example.AssessmentService.entity.Assessment;
import com.example.AssessmentService.entity.Question;
import com.example.AssessmentService.repository.AssessmentRepository;
import com.example.AssessmentService.service.AssessmentService;
import com.example.AssessmentService.service.CourseFeignClient;
import com.example.AssessmentService.service.UserFeignClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private CourseFeignClient courseFeignClient;

    @Override
    public Assessment createAssessment(Assessment assessment, Long userId, Long courseId) {
        // Fetch user details using FeignClient
        UserDto user = userFeignClient.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Fetch course details using FeignClient
        CourseDto course = courseFeignClient.getCourseById(courseId);
        if (course == null) {
            throw new RuntimeException("Course not found");
        }

        // Check if the user is an instructor and is the instructor of the course
        if (!user.getRole().equalsIgnoreCase("INSTRUCTOR") || !Objects.equals(course.getInstructorId(), userId)) {
            throw new RuntimeException("You're not allowed to create this assessment");
        }

        // Set courseId and instructorId for the assessment
        assessment.setCourseId(courseId);
        assessment.setInstructorId(userId);

        // Ensure each question is correctly mapped to the assessment
        if (assessment.getQuestions() != null) {
            for (Question question : assessment.getQuestions()) {
                question.setAssessment(assessment);  // Link question to assessment

                // Ensure correct answer is not null
                if (question.getCorrectAns() == null || question.getCorrectAns().isEmpty()) {
                    throw new RuntimeException("Correct answer cannot be null for question: " + question.getQuestionText());
                }

                // Ensure correct answer exists in options
                if (question.getOptions() == null || !question.getOptions().contains(question.getCorrectAns())) {
                    throw new RuntimeException("Correct answer must be one of the provided options for question: " + question.getQuestionText());
                }
            }
        }

        // Save the assessment along with its questions
        return assessmentRepository.save(assessment);
    }

    @Override
    public List<Assessment> getAssessmentByCourse(Long courseId) {
        List<Assessment> assessments = assessmentRepository.findByCourseId(courseId);
        if (assessments != null && !assessments.isEmpty()) {
            return assessments;
        } else {
            throw new RuntimeException("No assessments found for courseId: " + courseId);
        }
    }

    @Override
    public void deleteAssessmentById(Long userId, Long assessmentId) {
        Assessment assessment=assessmentRepository.findById(assessmentId).orElseThrow(()->new RuntimeException("Assessment not found"));
        if(assessment.getInstructorId().equals(userId)) {
            assessmentRepository.deleteById(assessmentId);
        }
        else {
            throw new RuntimeException("you're not allowed to delete this one.");
        }


    }

    @Override
    public AssessmentDto getAssessmentById(Long assessmentId) {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found with ID: " + assessmentId));

        AssessmentDto assessmentDto = new AssessmentDto();
        assessmentDto.setAssessmentId(assessment.getAssessmentId());
        assessmentDto.setCourseId(assessment.getCourseId());
        assessmentDto.setInstructorId(assessment.getInstructorId());
        assessmentDto.setMaxScore(assessment.getMaxScore());

        // Using a simple for loop to convert Question entity to QuestionDto
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question : assessment.getQuestions()) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setQuestionId(question.getQuestionId());
            questionDto.setQuestionText(question.getQuestionText());
            questionDto.setOptions(question.getOptions());
            questionDto.setCorrectAns(question.getCorrectAns());

            questionDtos.add(questionDto);
        }

        assessmentDto.setQuestions(questionDtos);
        return assessmentDto;

    }





}
