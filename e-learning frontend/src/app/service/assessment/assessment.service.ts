import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssessmentService {

  private baseUrl = 'http://localhost:4004/assessment';

  constructor(private http: HttpClient) {}

  getAssessmentsByCourse(courseId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/getAssessment?courseId=${courseId}`);
  }

  getAssessmentById(assessmentId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${assessmentId}`);
  }
}
