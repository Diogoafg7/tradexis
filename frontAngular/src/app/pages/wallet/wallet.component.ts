import { Component } from '@angular/core';
import { WalletService } from '../../wallet.service';
import { HttpClient } from '@angular/common/http';
import { ProfileComponent } from '../profile/profile.component';
import { ProfileDetailsComponent } from '../profile-details/profile-details.component';
import { ProfileServiceService } from '../../profile-service.service';

@Component({
  selector: 'app-wallet',
  imports: [],
  providers: [WalletService],
  templateUrl: './wallet.component.html',
  styleUrl: './wallet.component.scss'
})

export class WalletComponent {
  balance: any;

  constructor(private walletService: WalletService) {}

  ngOnInit(): void {
    const userIdString = localStorage.getItem('user_id');
    if (!userIdString) {
      throw new Error('User ID não encontrado no localStorage');
    }
    
    const userId = Number(userIdString); // Converte string para number
    if (isNaN(userId)) {
      throw new Error('User ID inválido');
    }
    
    this.walletService.getBalanceByUserId(userId).subscribe({
      next: (data) => {
        this.balance = data;
        console.log('Balance:', data);
      },
      error: (err) => {
        console.error('Erro ao procurar o balance:', err);
      },
    });
  }
}
