import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterModule } from '@angular/router';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterLink, RouterModule, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  bHorseProfile: string =
    'https://media.istockphoto.com/id/538856809/photo/horse-black.jpg?s=612x612&w=0&k=20&c=jyyhsoMjVB7xvX1-EtMddROQYlmCCkpSzM_Dh5wrSgo=';
  userProfileImage = this.bHorseProfile;
  isLoggedIn: boolean = false;

  constructor(private router: Router, private authService: AuthService) {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  navigateToUpdateProfile() {
    this.router.navigate(['profile']);
  }

  logout() {
    this.authService.logout();
    this.isLoggedIn = false;
    this.router.navigate(['/login']); 
  }
}
