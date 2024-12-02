import { Component } from '@angular/core';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [CommonModule,FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    if (this.username && this.password) {
      this.authService.login(this.username, this.password).subscribe(
        (response) => {
          const token = response.token;
          if (token) {
            this.authService.saveToken(token);  
            this.authService.redirectToDashboard();  
          }
        },
        (error) => {
          this.errorMessage = 'Invalid credentials, please try again.';
        }
      );
    } else {
      this.errorMessage = 'Please enter both username and password.';
    }
  }
}
