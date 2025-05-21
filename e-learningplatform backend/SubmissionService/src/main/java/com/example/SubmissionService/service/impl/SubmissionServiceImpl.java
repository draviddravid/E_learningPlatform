package com.example.SubmissionService.service.impl;

import com.example.SubmissionService.dto.*;
import com.example.SubmissionService.entity.Submission;
import com.example.SubmissionService.repository.SubmissionRepository;
import com.example.SubmissionService.service.AssessmentFeignClient;
import com.example.SubmissionService.service.CourseFeignClient;
import com.example.SubmissionService.service.SubmissionService;
import com.example.SubmissionService.service.UserFeignClient;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {


    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private AssessmentFeignClient assessmentFeignClient;
//    @Autowired
//    private CourseFeignClient courseFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Submission submitSubmission(SubmissionDto submissionDto) {
        UserDto user=userFeignClient.getUserById(submissionDto.getStudentId());
        if(user==null){
            throw new RuntimeException("User not Found");
        }
        AssessmentDto assessment=assessmentFeignClient.getAssessmentById(submissionDto.getAssessmentId());
        if(assessment==null){
            throw new RuntimeException("Assessment not found");
        }
        int score=0;
        for(QuestionDto question:assessment.getQuestions()){
            String correctAns=question.getCorrectAns();
            String submittedAns=submissionDto.getAns().get(question.getQuestionId());
            if(submittedAns!=null && submittedAns.equals(correctAns)){
                score+=1;
            }
        }
        Submission submission=new Submission();
        submission.setAssessmentId(submissionDto.getAssessmentId());
        submission.setStudentId(submissionDto.getStudentId());
        submission.setScore(score);
        return submissionRepository.save(submission);
    }

    @Override
    public List<Submission> getSubmissionByStudent(Long studentId) {
        List<Submission> submissions= submissionRepository.findByStudentId(studentId);
        if(submissions.isEmpty()){
            throw new RuntimeException("No submission found!");
        }
        return submissions;
    }


}
