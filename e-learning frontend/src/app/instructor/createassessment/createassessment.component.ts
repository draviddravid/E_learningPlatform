import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CourseService } from '../../service/course/course.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NavbarComponent } from '../../components/navbar/navbar.component';


@Component({
  selector: 'app-createassessment',
  imports: [CommonModule,ReactiveFormsModule,NavbarComponent,],
  templateUrl: './createassessment.component.html',
  styleUrl: './createassessment.component.css'
})
export class CreateassessmentComponent implements OnInit {
  assessmentForm!: FormGroup;
  userId: number | null = null;
  courseId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private CourseService: CourseService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.userId = +params['userId'];
      this.courseId = +params['courseId'];
    });

    this.assessmentForm = this.fb.group({
      maxScore: ['', Validators.required],
      questions: this.fb.array([])
    });
  }

  get questions(): FormArray {
    return this.assessmentForm.get('questions') as FormArray;
  }

  addQuestion() {
    const questionGroup = this.fb.group({
      questionText: ['', Validators.required],
      options: ['', Validators.required],
      correctAns: ['', Validators.required]
    });
    this.questions.push(questionGroup);
  }

  removeQuestion(index: number) {
    this.questions.removeAt(index);
  }

  onSubmit() {
    if (this.assessmentForm.invalid || this.userId === null || this.courseId === null) {
      console.error('Form is invalid or user/course ID is missing.');
      return;
    }

    const assessmentData = this.assessmentForm.value;
    assessmentData.questions = assessmentData.questions.map((question: any) => ({
      ...question,
      options: question.options.split(',').map((option: string) => option.trim())
    }));

    this.CourseService.createAssessment(assessmentData, this.userId, this.courseId).subscribe({
      next: () => {
        alert('Assessment created successfully!');
        this.router.navigate(['createdcourses']);
      },
      error: (error) => {
        console.error('Error creating assessment:', error);
      }
    });
  }
}