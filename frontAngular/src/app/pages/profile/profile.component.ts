import { Component, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProfileServiceService } from '../../profile-service.service';
import { Profile } from '../../models/profile';
import { HeaderComponent } from '../../components/header/header.component';
import { ActivatedRoute, RouterLink } from '@angular/router';


@Component({
  selector: 'app-profile',
  imports: [ReactiveFormsModule, HeaderComponent, RouterLink],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {

  // profile: Profile | undefined;

  // form: FormGroup = new FormGroup({
  //   name: new FormControl('', Validators.required),
  //   email: new FormControl('', Validators.required),
  //   password: new FormControl('', Validators.required)
  // });

  // constructor(private profileService: ProfileServiceService) {}

  //  ngOnInit() {
  //    this.profileService.getProfile().subscribe({
  //      next: data => {
  //        this.profile = data;
  //        if (data) {
  //          this.form.controls['name'].setValue(data.name);
  //          this.form.controls['email'].setValue(data.email);
  //          this.form.controls['password'].setValue(data.password);
  //        }
  //      },
  //      error: error => {
  //        console.error(error);
  //      }
  //    });
  //  }

  //  updateProfile() {
  //    if (this.form.valid) {
  //      const updatedProfile: Profile = {
  //        ...this.profile,
  //        ...this.form.getRawValue()
  //      };
  //      this.profileService.updateProfile(updatedProfile).subscribe({
  //        next: data => {
  //          console.log('Profile updated successfully');
  //          console.log('Data: ' + data);
  //        },
  //        error: error => {
  //          console.error('Error updating profile', error);
  //        }
  //      });
  //    }
  //  }
  id: number | null = null;  // Inicializa a variável id

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Aqui você pode obter o id do usuário via um serviço de API ou diretamente de algum estado
    this.id = 1; // Exemplo: atribuindo um id fixo, substitua com lógica real
  }
  }
