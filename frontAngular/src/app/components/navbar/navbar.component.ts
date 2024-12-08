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

  balance: number = 0;
  amountToAdd: number = 0;
  showAddFundsPopup: boolean = false;

  constructor(private router: Router, private authService: AuthService, private walletService: WalletService, private cdRef: ChangeDetectorRef) {
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

    const userId = Number(userIdString);
    if (isNaN(userId)) {
      throw new Error('User ID inválido');
    }

    this.walletService.getBalanceByUserId(userId).subscribe({
      next: (data) => {
        this.balance = data.balance;
        console.log('Balance:', this.balance);
        this.cdRef.detectChanges();
      },
      error: (err) => {
        console.error('Erro ao procurar o balance:', err);
      },
    });
  }

  openAddFundsPopup(): void {
    this.showAddFundsPopup = true;
  }

  closeAddFundsPopup(): void {
    this.showAddFundsPopup = false;
  }

  addFunds(): void {
    if (this.amountToAdd <= 0) {
      alert('Por favor, insira um valor válido');
      return;
    }

    const userIdString = localStorage.getItem('user_id');
    if (!userIdString) {
      alert('Usuário não identificado. Por favor, faça login novamente.');
      return;
    }

    const userId = Number(userIdString);
    if (isNaN(userId)) {
      alert('ID do usuário inválido.');
      return;
    }

    const newBalance = this.balance + this.amountToAdd;

    this.walletService.updateWalletById(userId, newBalance).subscribe({
      next: () => {
        alert('Fundos adicionados com sucesso!');
        this.balance = newBalance;
        this.amountToAdd = 0;
        this.closeAddFundsPopup();
      },
      error: (err) => {
        console.error('Erro ao adicionar fundos:', err);
        alert('Não foi possível adicionar fundos. Tente novamente mais tarde.');
      },
    });
  }
}
