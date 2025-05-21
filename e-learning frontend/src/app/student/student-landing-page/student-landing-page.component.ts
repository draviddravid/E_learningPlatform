import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { CourseListComponent } from '../../components/course-list/course-list.component';

@Component({
  selector: 'app-student-landing-page',
  imports: [NavbarComponent,CourseListComponent],
  templateUrl: './student-landing-page.component.html',
  styleUrls: ['./student-landing-page.component.css']
})
export class StudentLandingPageComponent implements OnInit {
  studentName: string | null = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    const loggedInUser = localStorage.getItem('loggedInUser');
    if (loggedInUser) {
      const user = JSON.parse(loggedInUser);
      this.studentName = user.name; // Assuming the user object has a 'name' property
    }
  }

  navigateToEnrolledCourses() {
    this.router.navigate(['enrolledcourse']);
  }
}