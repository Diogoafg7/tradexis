import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profile } from './models/profile';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ProfileServiceService {
  private baseUrl = 'http://localhost:8080/users';

  constructor(private httpClient: HttpClient) {}

  
  getCurrentUser(): Observable<Profile> {
    const userId = localStorage.getItem('user_id'); 
    if (userId) {
      return this.httpClient.get<Profile>(`${this.baseUrl}/${userId}`);
    } else {
      throw new Error('User ID not found in localStorage');
    }
  }

  getCurrentUserId(): any | null {
    const userId = localStorage.getItem('user_id'); 
    return userId ? +userId : null; 
  }

 
  updateCurrentUser(profile: Profile): Observable<Profile> {
    return this.httpClient.put<Profile>(`${this.baseUrl}/me`, profile, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    });
  }
}
