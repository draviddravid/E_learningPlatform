package com.example.AssessmentService.repository;

import com.example.AssessmentService.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Long> {
    List<Assessment> findByCourseId(Long courseId);
}
