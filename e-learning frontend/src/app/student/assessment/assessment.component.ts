import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../../components/model/course/course.model';
import { AssessmentService } from '../../service/assessment/assessment.service';
import { Assessment } from '../../components/model/assessment/assessment.model';
import { NavbarComponent } from '../../components/navbar/navbar.component';

@Component({
  selector: 'app-assessment',
  imports: [CommonModule,NavbarComponent],
  templateUrl: './assessment.component.html',
  styleUrls: ['./assessment.component.css']
})
export class AssessmentComponent implements OnInit {
  course?: Course;
  assessments: any[] = [];

  constructor(private router: Router, private assessmentService: AssessmentService) {
    const navigation = this.router.getCurrentNavigation();
    this.course = navigation?.extras.state?.['course'];
    console.log('Received course:', this.course); // Debugging line
  }

  ngOnInit(): void {
    if (this.course && this.course.courseId) {
      this.assessmentService.getAssessmentsByCourse(Number(this.course.courseId)).subscribe({
        next: (data: any[]) => {
          this.assessments = data;
          console.log(this.assessments);
        },
        error: (err) => console.error('Error fetching assessments:', err)
      });
    } else {
      console.error('Course ID is not available.');
    }
  }

  takeAssessment(assessment:Assessment): void {
    this.router.navigate(['assessmentdetail'], { state: { assessment} });
  }
}