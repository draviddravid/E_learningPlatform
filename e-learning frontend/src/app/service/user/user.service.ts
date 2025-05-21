import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../components/model/user/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:4001/user'; 

  constructor(private http:HttpClient) {}

  register(u:User): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, u);
  }

  login(credentials: { email: string, password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, credentials);
  }

  userExists(email: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/users/exists?email=${email}`);
  }

  
}
