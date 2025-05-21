import { Component, OnInit } from '@angular/core';
import { User } from '../../components/model/user/user.model';
import { CourseService } from '../../service/course/course.service';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-created-courses',
  imports: [NavbarComponent, CommonModule],
  templateUrl: './coursecreated.component.html',
  styleUrls: ['./coursecreated.component.css']
})
export class CreatedCoursesComponent implements OnInit {
  user: User = {};
  instructorName?: string = '';
  courses: any[] = [];

  constructor(
    private courseService: CourseService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
    const storedUser = localStorage.getItem('loggedInUser');
    if (storedUser) {
      this.user = JSON.parse(storedUser);
      this.instructorName = this.user.name; // Display name or ID
    } else {
      alert('Session expired, Please login again.');
      // Handle session expiration
    }
  }

  ngOnInit(): void {
    this.getCoursesByInstructor();
  }

  getCoursesByInstructor() {
    const instructorId = this.user.id;
    if (typeof instructorId === 'number') {
      this.courseService.getCoursesByInstructorId(instructorId)
        .subscribe(
          data => {
            this.courses = data;
            this.cdr.detectChanges(); // Trigger change detection
          },
          error => {
            console.error('Error fetching courses:', error);
          }
        );
    } else {
      console.error('Instructor ID is missing or invalid.');
    }
  }

  deleteCourse(courseId: number) {
    const userId = this.user.id;
    if (typeof userId === 'number') {
      this.courseService.deleteCourse(courseId, userId).subscribe({
        next: () => {
          alert('Course deleted successfully!');
          this.getCoursesByInstructor(); // Refresh the list
          this.cdr.detectChanges(); // Trigger change detection
        },
        error: (error) => {
          console.error('Error deleting course:', error);
        }
      });
    } else {
      console.error('User ID is missing or invalid.');
    }
  }

  createAssessment(courseId: number) {
    this.router.navigate(['createassessment'], { queryParams: { courseId, userId: this.user.id } });
  }
}