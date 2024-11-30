import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  bHorseProfile: string = "https://media.istockphoto.com/id/538856809/photo/horse-black.jpg?s=612x612&w=0&k=20&c=jyyhsoMjVB7xvX1-EtMddROQYlmCCkpSzM_Dh5wrSgo=";

  userProfileImage = this.bHorseProfile;

  constructor( private router: Router) {}

  navigateToUpdateProfile() {
    this.router.navigate(['profile']);
  }

  
  
}
