import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Assessment } from '../../components/model/assessment/assessment.model';
import { AssessmentService } from '../../service/assessment/assessment.service';
// import { Submission } from '../../components/model/submission/submission.model';
import { SubmissionService } from '../../service/submission/submission.service';
import { Submission } from '../../components/model/submission.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../../components/navbar/navbar.component';

@Component({
  selector: 'app-assessment-detail',
  imports: [FormsModule,CommonModule,NavbarComponent],
  templateUrl: './assessment-detail.component.html',
  styleUrls: ['./assessment-detail.component.css']
})
export class AssessmentDetailComponent implements OnInit {
  assessment?: Assessment;
  answers: { [key: number]: string } = {};
  grade?: number;
  submissionSuccess = false;

  constructor(
    private route: ActivatedRoute,
    private assessmentService: AssessmentService,
    private router: Router,
    private submissionService: SubmissionService
  ) {
    const navigation = this.router.getCurrentNavigation();
    this.assessment = navigation?.extras.state?.['assessment'];
  }

  ngOnInit(): void {
    if (this.assessment?.assessmentId) {
      this.assessmentService.getAssessmentById(this.assessment.assessmentId).subscribe({
        next: (data: Assessment) => {
          this.assessment = data;
        },
        error: (err) => console.error('Error fetching assessment:', err)
      });
    }
  }

  submitAssessment(): void {
    if (this.assessment) {
      const submission: Submission = {
        assessmentId: Number(this.assessment.assessmentId),
        studentId: 1, 
        ans: this.answers
      };

      this.submissionService.submitSubmission(submission).subscribe({
        next: (submission) => {
          this.grade = submission.score;
          this.submissionSuccess = true;
          console.log('Submission successful:', submission);
        },
        error: (err) => {
          console.error('Error submitting assessment:', err);
          this.submissionSuccess = false;
        }
      });
    }
  }

  onAnswerChange(questionId: any, answer: string): void {
    this.answers[questionId] = answer;
  }
}
