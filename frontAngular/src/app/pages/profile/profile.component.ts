import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProfileServiceService } from '../../profile-service.service';
import { Profile } from '../../models/profile';
import { HeaderComponent } from '../../components/header/header.component';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { RodapeAcoesComponent } from '../../components/rodape-acoes/rodape-acoes.component';


@Component({
  selector: 'app-profile',
  imports: [ReactiveFormsModule, HeaderComponent, RouterLink, RodapeAcoesComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit {
  profile: Profile | null = null;  

  constructor(private profileService: ProfileServiceService) {}

  ngOnInit(): void {

    this.profileService.getCurrentUser().subscribe({
      next: (data) => {
        this.profile = data;  
        console.log('profile', this.profile)
        console.log(data);
      },
      error: (error) => {
        console.error('Error fetching profile:', error);
      },
    });
  }
}
