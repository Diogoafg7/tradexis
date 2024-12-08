import { HeaderComponent } from '../../components/header/header.component';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ProfileServiceService } from '../../profile-service.service';
import { Profile } from '../../models/profile';
import { RodapeAcoesComponent } from '../../components/rodape-acoes/rodape-acoes.component';

@Component({
  selector: 'app-profile-details',
  imports: [HeaderComponent, RodapeAcoesComponent],
  templateUrl: './profile-details.component.html',
  styleUrl: './profile-details.component.scss'
})
export class ProfileDetailsComponent {

  profile: Profile | null = null;  
  id: string | null = null;  

  constructor(
    private route: ActivatedRoute,
    private profileService: ProfileServiceService
  ) {}

  ngOnInit(): void {
    
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
      if (this.id) {
        
        this.profileService.getCurrentUser().subscribe({
          next: (data) => {
            this.profile = data;
            //console.log('Profile Data:', this.profile);
          },
          error: (error) => {
            console.error('Error fetching profile:', error);
          }
        });
      }
    });
  }

  focusInput(inputElement: HTMLInputElement) {
    inputElement.focus();
  }
}
