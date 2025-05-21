import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../../components/model/course/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private baseUrl = 'http://localhost:4002/course';
  private enrollemetUrl="http://localhost:4003/enrollment"
  private assessmentUrl="http://localhost:4004/assessment"

  constructor(private http: HttpClient) {}

  createCourse(course: Course, instructorId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/createCourse/${instructorId}`, course);
  }

  getCourses(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/getAllCourse`);
  }
  

  getCoursesByInstructorId(instructorId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/instructor/${instructorId}`);
  }

  deleteCourse(courseId: number, userId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deleteCourse/${courseId}?userId=${userId}`);
  }

  createAssessment(assessment: any, userId: number, courseId: number): Observable<any> {
    return this.http.post(`${this.assessmentUrl}/createAssessment?userId=${userId}&courseId=${courseId}`, assessment);
  }

  getEnrolledCourses(studentId: number): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.enrollemetUrl}/${studentId}/enrolled-course`);
  }

  enrollCourse(studentId: number, courseId: number): Observable<any> {
    const params = new HttpParams()
      .set('studentId', studentId.toString())
      .set('courseId', courseId.toString());
    return this.http.post(`${this.enrollemetUrl}/enrollCourse`, null, { params });
  }
}
  