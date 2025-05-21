import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
// import { Submission } from '../../components/model/submission/submission.model';
import { Observable } from 'rxjs';
import { Submission } from '../../components/model/submission.model';

@Injectable({
  providedIn: 'root'
})
export class SubmissionService {
  private apiUrl = 'http://localhost:4005/submission';

  constructor(private http: HttpClient) {}

  submitSubmission(submission: Submission): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/submit`, submission);
  }
}
