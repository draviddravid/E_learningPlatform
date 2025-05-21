import { Routes } from '@angular/router';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { CourseListComponent } from './components/course-list/course-list.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { InstructorLandingComponent } from './instructor/instructor-landing-page/instructor-landing-page.component';
import { CreateCourseComponent } from './instructor/create-course/create-course.component';
import { instructorGuard } from './guard/instructor.guard';
import { CreatedCoursesComponent } from './instructor/coursecreated/coursecreated.component';
import { CreateassessmentComponent } from './instructor/createassessment/createassessment.component';
import { StudentLandingPageComponent } from './student/student-landing-page/student-landing-page.component';
import { EnrolledCourseComponent } from './student/enrolled-course/enrolled-course.component';
import { AssessmentComponent } from './student/assessment/assessment.component';
import { AssessmentDetailComponent } from './student/assessment-detail/assessment-detail.component';
import { studentGuard } from './guard/student.guard';

export const routes: Routes = [
    {path:'',component:HomepageComponent},
    {path:'login',component:LoginComponent},
    {path:'signup',component:SignupComponent},
    {path:'courselist',component:CourseListComponent},
    {path:'navbar',component:NavbarComponent},
    {path:'instructor-landing-page',component:InstructorLandingComponent,canActivate:[instructorGuard]},
    {path:'createcourse',component:CreateCourseComponent,canActivate:[instructorGuard]},
    {path:'instructor',component:InstructorLandingComponent,canActivate:[instructorGuard]},
    { path: 'createdcourses', component: CreatedCoursesComponent,canActivate:[instructorGuard]},
    {path:'createassessment',component:CreateassessmentComponent,canActivate:[instructorGuard]},
    {path:'student-landing-page',component:StudentLandingPageComponent,canActivate:[studentGuard]},
    {path:'enrolledcourse',component:EnrolledCourseComponent,canActivate:[studentGuard]},
    {path:'assessment',component:AssessmentComponent,canActivate:[studentGuard]},
    {path:'assessmentdetail',component:AssessmentDetailComponent,canActivate:[studentGuard]}
    

    
    

];
