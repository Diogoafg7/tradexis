import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profile } from './models/profile';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ProfileServiceService {
  private baseUrl = 'http://localhost:8080/users'; // Adjust base URL if needed

  constructor(private httpClient: HttpClient) {}

  
  getCurrentUser(): Observable<Profile> {
    const userId = localStorage.getItem('user_id');  // Retrieve user ID from localStorage
    if (userId) {
      return this.httpClient.get<Profile>(`${this.baseUrl}/${userId}`);
    } else {
      throw new Error('User ID not found in localStorage');
    }
  }

  getCurrentUserId(): any | null {
    const userId = localStorage.getItem('user_id'); // Retrieve user ID from localStorage
    return userId ? +userId : null; // Convert to number or return null if not found
  }

 
  updateCurrentUser(profile: Profile): Observable<Profile> {
    return this.httpClient.put<Profile>(`${this.baseUrl}/me`, profile, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    });
  }
}
