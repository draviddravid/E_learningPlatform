import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CourseService } from '../../service/course/course.service';
import { Router } from '@angular/router';
import { NavbarComponent } from '../../components/navbar/navbar.component';


@Component({
  selector: 'app-create-course',
  imports:[ReactiveFormsModule,CommonModule,NavbarComponent],
  templateUrl: './create-course.component.html',
  styleUrl: './create-course.component.css'
})
export class CreateCourseComponent implements OnInit {
  courseForm!: FormGroup;
  instructorId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private courseService: CourseService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Retrieve instructor ID from localStorage
    const storedInstructorId = localStorage.getItem('instructorId');

    if (storedInstructorId) {
      this.instructorId = Number(storedInstructorId);
      console.log('Instructor ID:', this.instructorId);
    } else {
      alert('Session expired, Please login again.');
      this.router.navigate(['/login']);
      return;
    }

    // Initialize form
    this.courseForm = this.fb.group({
      title: ['', Validators.required], // Fixed: courseName -> title
      description: ['', Validators.required], // Fixed: courseDescription -> description
      contentURL: ['', [Validators.required, Validators.pattern('https?://.+')]] // Fixed: courseUrl -> contentURL
    });
  }

  onSubmit() {
    if (this.courseForm.invalid || this.instructorId === null) {
      console.error('Form is invalid or instructor ID is missing.');
      return;
    }

    const courseData = this.courseForm.value;
    console.log('Submitting Course:', courseData, 'Instructor ID:', this.instructorId);

    this.courseService.createCourse(courseData, this.instructorId).subscribe({
      next: () => {
        alert('Course created successfully!');
        this.router.navigate(['instructor-landing-page']);
      },
      error: (error) => {
        console.error('Error creating course:', error);
      }
    });
  }
}
