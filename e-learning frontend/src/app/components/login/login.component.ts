import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../service/user/user.service';
import { User } from '../model/user/user.model';

@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  userdata?: User;

  constructor(
    private fb: FormBuilder,
    private service: UserService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    // Initialize form with validation
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  loginUser() {
    if (this.loginForm.invalid) {
      return;
    }
  
    const credentials = this.loginForm.value;
    this.service.login(credentials).subscribe({
      next: (data) => {
        localStorage.setItem("loggedInUser", JSON.stringify(data));
        this.userdata = data;
        console.log(data);
        if (this.userdata?.role === "INSTRUCTOR") {
          localStorage.setItem("instructorId", String(this.userdata.id));
          this.router.navigate(['instructor-landing-page']);
        } else {
          this.router.navigate(['student-landing-page']);
        }
      },
      error: (error) => {
        console.error('Login error:', error);
        // Display a user-friendly error message
        alert('An error occurred while logging in. Please try again later.');
      }
    });
  }
}