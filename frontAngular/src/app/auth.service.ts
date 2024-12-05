import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';



interface SigninRequest {
  username: string;
  password: string;
}


interface JwtAuthenticationResponse {
  token: string;
  user: {
    id: number;
    username: string;
    email: string;
    password: string;  
    createdAt: string;
    updatedAt: string;
    enabled: boolean;
    authorities: Array<{ authority: string }>;
    credentialsNonExpired: boolean;
    accountNonExpired: boolean;
    accountNonLocked: boolean;
  };
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth/signin';  
  private apiSingupUrl = 'http://localhost:8080/auth';

  constructor(private httpClient: HttpClient, private router: Router) {}


  login(username: string, password: string): Observable<JwtAuthenticationResponse> {
    const signinRequest: SigninRequest = { username, password };
    return this.httpClient.post<JwtAuthenticationResponse>(this.apiUrl, signinRequest);
  }

  register(userData: any): Observable<any> {
    return this.httpClient.post(`${this.apiSingupUrl}/signup`, userData);
  }

 
  saveToken(token: string): void {
    localStorage.setItem('auth_token', token);
  }

  
  isLoggedIn(): boolean {
    return !!localStorage.getItem('auth_token');
  }


  redirectToDashboard(): void {
    this.router.navigate(['/dashboard']);
  }


  logout(): void {
    localStorage.removeItem('auth_token');
    this.router.navigate(['/']);
  }

  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }
}
