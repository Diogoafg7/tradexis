import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile-details',
  imports: [HeaderComponent],
  templateUrl: './profile-details.component.html',
  styleUrl: './profile-details.component.scss'
})
export class ProfileDetailsComponent {

  id: string | null = null;  // Para armazenar o id do parâmetro da URL

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Captura o id da URL
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id'); // 'id' deve corresponder ao parâmetro da rota '/profile-details/:id'
      console.log('Perfil ID:', this.id); // Aqui você pode usar o ID para buscar os dados do perfil
    });
  }
  focusInput(inputElement: HTMLInputElement) {
    inputElement.focus();
  }
}
