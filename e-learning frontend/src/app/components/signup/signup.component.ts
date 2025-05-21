import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user/user.model';
import { UserService } from '../../service/user/user.service';

@Component({
  selector: 'app-signup',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;

  constructor(private router: Router,
              private fb: FormBuilder,
              private service: UserService) { }

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['STUDENT', Validators.required] // Default to student
    });
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      return;
    }

    const user: User = this.signupForm.value;

    this.service.userExists(String(user.email)).subscribe({
      next: (exists) => {
        if (exists) {
          alert('User already exists. Please use a different email.');
        } else {
          this.service.register(user).subscribe({
            next: () => {
              alert('Sign-up successful!');
              this.router.navigate(['login']);
            },
            error: (err) => {
              console.error('Error during registration:', err);
              alert('Registration failed. Please try again later.');
            }
          });
        }
      },
      // error: () => {
      //   existence:', err);
      //   alert('An error occurred while checking user existence. Please try again later.');
      // }
    });
  }
}