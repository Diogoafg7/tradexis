import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { NgClass, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-transaction-history',
  imports: [HeaderComponent, NgFor, NgIf, NgClass],
  templateUrl: './transaction-history.component.html',
  styleUrl: './transaction-history.component.scss'
})
export class TransactionHistoryComponent {
  // Lista de Ações
  acoes = [
    { 
      nome: 'TESLA', 
      sigla: 'TSLA', 
      preco: '$336.09', 
      diferenca: '+0.07%', 
      percentual: '(0.21%)', 
      detalhes: {
        tipo: 'Stock',
        status: 'Concluído',
        nome: 'Tesla (TSLA)',
        valorInvestido: '$1,200.00',
        precoExecucao: '$1,200.00',
        quantidade: '3 ações',
        submetidaEm: '02/12/2024',
        resultado: '$20,000'
      }
    },
    { 
      nome: 'AMAZON', 
      sigla: 'AMZN', 
      preco: '$3,250.10', 
      diferenca: '-0.15%', 
      percentual: '(1.08%)', 
      detalhes: {
        tipo: 'Stock',
        status: 'Concluído',
        nome: 'Amazon (AMZN)',
        valorInvestido: '$2,500.00',
        precoExecucao: '$2,450.00',
        quantidade: '5 ações',
        submetidaEm: '03/12/2024',
        resultado: '$-500'
      }
    }
  ];

  // Variável para armazenar os detalhes da transação a ser exibido
  detalhesTransacao: any = null;
  // Variável para armazenar o índice da ação selecionada
  selectedIndex: number | null = null;

  // Função para mostrar detalhes da transação
  mostrarDetalhes(index: number) {
    this.detalhesTransacao = this.acoes[index].detalhes;
  }
}
