import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../service/course/course.service';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-enrolled-course',
  imports: [CommonModule, ReactiveFormsModule, FormsModule, NavbarComponent],
  templateUrl: './enrolled-course.component.html',
  styleUrls: ['./enrolled-course.component.css']
})
export class EnrolledCourseComponent implements OnInit {
  enrolledCourses: any[] = [];
  studentId = 0;
  student: any;

  constructor(private courseService: CourseService, private router: Router) {
    const storedUser = localStorage.getItem('loggedInUser');
    if (storedUser) {
      this.student = JSON.parse(storedUser);
      this.studentId = Number(this.student.id); // Display name or ID
    } else {
      alert('Session expired, Please login again.');
    }
  }

  ngOnInit(): void {
    this.fetchEnrolledCourses();
  }

  fetchEnrolledCourses(): void {
    if (this.studentId !== null) {
      this.courseService.getEnrolledCourses(this.studentId).subscribe({
        next: (data) => { this.enrolledCourses = data; console.log(this.enrolledCourses); },
        error: (err) => console.error('Error fetching enrolled courses:', err)
      });
    } else {
      console.error('Student ID is null, cannot fetch enrolled courses.');
    }
  }

  takeAssessment(course: any): void {
    console.log('Navigating with course:', course); // Debugging line
    this.router.navigate(['assessment'], { state: { course } });
  }
}