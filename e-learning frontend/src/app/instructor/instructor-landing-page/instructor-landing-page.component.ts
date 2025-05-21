import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { User } from '../../components/model/user/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-instructor-landing-page',
  imports: [NavbarComponent, CommonModule, FormsModule],
  templateUrl: './instructor-landing-page.component.html',
  styleUrls: ['./instructor-landing-page.component.css']
})
export class InstructorLandingComponent implements OnInit {
  user: User = {};
  instructorName?: string = '';

  constructor(private router: Router) {
    const storedUser = localStorage.getItem('loggedInUser');
    if (storedUser) {
      this.user = JSON.parse(storedUser);
      this.instructorName = this.user.name // Display name or ID
    } else {
      alert('Session expired, Please login again.');
      this.router.navigate(['/login']);
    }
  }

  ngOnInit(): void {
    // Any additional initialization logic can go here
  }

  createCourse() {
    console.log('Navigating with userID:', this.user.id); // Log the userID
    this.router.navigate(['createcourse']);
  }

  deleteCourse() {
    console.log('Navigating to delete course page...');
    // Add routing logic to navigate to delete course page
  }
  viewCreatedCourses() {
    console.log('Navigating to view created courses...');
    this.router.navigate(['createdcourses']);
  }
}
