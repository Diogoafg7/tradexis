import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CommonModule } from '@angular/common';
import { TradeService } from '../../trade.service';

@Component({
  selector: 'app-transactions',
  imports: [HeaderComponent, CommonModule],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.scss'
})
export class TransactionsComponent {
  trades: any[] = []; // Armazena as trades ativas

  constructor(private tradeService: TradeService) {}

  ngOnInit(): void {
    this.loadTrades();
  }

  // Carrega todas as trades usando o serviço
  loadTrades(): void {
    this.tradeService.getAllTrades().subscribe(
      (data) => {
        this.trades = data;
        console.log('Trades carregadas:', data);
      },
      (error) => {
        console.error('Erro ao carregar trades:', error);
        // Exibição de mensagem de erro para o usuário (opcional)
        alert('Erro ao carregar as transações.');
      }
    );
  }

  // Função para "vender" uma trade
  sellTrade(tradeId: number): void {
    // Envia requisição DELETE para excluir a trade
    this.tradeService.deleteTrade(tradeId).subscribe(
      () => {
        // Remove a trade localmente após excluir
        this.trades = this.trades.filter((trade) => trade.id !== tradeId);
        console.log(`Trade com ID ${tradeId} vendida com sucesso!`);
      },
      (error) => {
        console.error(`Erro ao vender trade com ID ${tradeId}:`, error);
        // Exibição de mensagem de erro para o usuário (opcional)
        alert('Erro ao vender a transação.');
      }
    );
  }

}
