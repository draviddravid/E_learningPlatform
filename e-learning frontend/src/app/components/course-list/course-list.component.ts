import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CourseService } from '../../service/course/course.service';
import { Router } from '@angular/router';
import { Course } from '../model/course/course.model';

@Component({
  selector: 'app-course-list',
  imports: [ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courses: any[] = [];
  enrolledCourses: any[] = [];
  studentId = 0;
  student: any; // Example: Get studentId dynamically (from localStorage after login)

  constructor(private courseService: CourseService, private router: Router) {
    const storedUser = localStorage.getItem('loggedInUser');
    if (storedUser) {
      this.student = JSON.parse(storedUser);
      this.studentId = Number(this.student.id); // Display name or ID
    } else {
      alert('Session expired, Please login again.');
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.courseService.getCourses().subscribe({
      next: (data) => {
        this.courses = data;
        console.log(this.courses);
      },
      error: (err) => console.error('Error fetching courses:', err)
    });
    this.fetchEnrolledCourses();
  }

  fetchEnrolledCourses(): void {
    if (this.studentId !== null) {
      this.courseService.getEnrolledCourses(this.studentId).subscribe({
        next: (data) => {
          this.enrolledCourses = data.map(course => course.courseId);
        },
        error: (err) => console.error('Error fetching enrolled courses:', err)
      });
    } else {
      console.error('Student ID is null, cannot fetch enrolled courses.');
    }
  }

  enroll(courseId: number) {
    this.courseService.enrollCourse(this.studentId, courseId).subscribe({
      next: (response) => {
        this.enrolledCourses.push(courseId);
        alert(response.message); // Show alert with the message from the response
        console.log('Enrollment successful');
      },
      error: (err) => {
        if (err.status === 400 && err.error.error === 'Student is already enrolled') {
          alert('You are already enrolled in this course.');
        } else {
          console.error('Enrollment failed:', err);
          alert('Enrollment failed. Please try again later.');
        }
      }
    });
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourses.includes(courseId);
  }
}