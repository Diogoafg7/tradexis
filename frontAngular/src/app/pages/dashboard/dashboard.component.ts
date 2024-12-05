import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  imports: [NgFor, NgIf, HeaderComponent, FormsModule, NgClass],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
  // Lista de ações disponíveis
  stocks = [
    {
      name: 'TESLA',
      symbol: 'TSLA',
      price: '$336.09',
      change: 0.07,
      daily: '0.21%'
    },
    {
      name: 'AMAZON',
      symbol: 'AMZN',
      price: '$3,250.10',
      change: -0.15,
      daily: '1.08%'
    }
  ];

  // Ação selecionada para compra
  selectedStock: any = null;

  // Montante que o usuário quer comprar
  buyAmount: number | null = null;

  // Alternar exibição da seção de compra
  toggleBuySection(stock: any) {
    if (this.selectedStock === stock) {
      this.selectedStock = null; // Fecha a seção
    } else {
      this.selectedStock = stock; // Abre para o item clicado
    }
  }

  // Confirmação de compra
  confirmPurchase(stock: any) {
    if (this.buyAmount && this.buyAmount > 0) {
      console.log(`Você comprou ${this.buyAmount} ações de ${stock.name}`);
      alert(`Compra confirmada: ${this.buyAmount} ações de ${stock.name}`);
      this.buyAmount = null; // Reseta o valor do montante
      this.selectedStock = null; // Fecha a seção
    } else {
      alert('Por favor, insira um valor válido.');
    }
  }
}
