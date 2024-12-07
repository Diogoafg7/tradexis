import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterModule } from '@angular/router';
import { AuthService } from '../../auth.service';
import { WalletService } from '../../wallet.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterLink, RouterModule, RouterLinkActive, FormsModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  bHorseProfile: string =
    'https://media.istockphoto.com/id/538856809/photo/horse-black.jpg?s=612x612&w=0&k=20&c=jyyhsoMjVB7xvX1-EtMddROQYlmCCkpSzM_Dh5wrSgo=';
  userProfileImage = this.bHorseProfile;
  isLoggedIn: boolean = false;

  balance: number = 0; // Inicialize a propriedade 'balance'
  amountToAdd: number = 0; // Valor a ser adicionado
  showAddFundsPopup: boolean = false; // Controle do popup
  
  constructor(private router: Router, private authService: AuthService, private walletService: WalletService, private cdRef: ChangeDetectorRef ) {
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

  ngOnInit(): void {
    const userIdString = localStorage.getItem('user_id');
    if (!userIdString) {
      throw new Error('User ID não encontrado no localStorage');
    }

    const userId = Number(userIdString); // Converte string para número
    if (isNaN(userId)) {
      throw new Error('User ID inválido');
    }

    this.walletService.getBalanceByUserId(userId).subscribe({
      next: (data) => {
        this.balance = data.balance; // Atualiza a propriedade 'balance'
        console.log('Balance:', this.balance);
        this.cdRef.detectChanges(); 
      },
      error: (err) => {
        console.error('Erro ao procurar o balance:', err);
      },
    });
  }

  // Função para abrir o popup
  openAddFundsPopup(): void {
    this.showAddFundsPopup = true;
  }

  // Função para fechar o popup
  closeAddFundsPopup(): void {
    this.showAddFundsPopup = false;
  }

  // Função para adicionar fundos
  addFunds(): void {
    if (this.amountToAdd <= 0) {
      alert('Por favor, insira um valor válido');
      return;
    }
  }
}
