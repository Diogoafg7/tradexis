import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CommonModule, NgClass, NgFor, NgIf } from '@angular/common';
import { StockServiceService } from '../../stock-service.service';
import { Trade } from '../../models/trade';
import { TradeService } from '../../trade.service';

@Component({
  selector: 'app-transaction-history',
  imports: [HeaderComponent, NgFor, NgIf, NgClass, CommonModule],
  templateUrl: './transaction-history.component.html',
  styleUrl: './transaction-history.component.scss'
})

export class TransactionHistoryComponent {
  trades: Trade[] = [];
  currentPrices: { [key: string]: number } = {}; // Armazena preços atuais dos ativos
  stockHistories: any[] = [];
  stockDetails: any | null = null;

  constructor(private stockHistoryService: StockServiceService, private tradeService: TradeService) {}

  ngOnInit(): void {
    this.loadAllStockHistories();

    this.tradeService.getAllTrades().subscribe((data: Trade[]) => {
      this.trades = data;

      // Mock: Preços atuais dos ativos (simula o retorno de uma API de preços)
      this.currentPrices = {
        BTC: 50.0, // Preço atual do Bitcoin
        ETH: 4000.0 // Adicione outros ativos, se necessário
      };
    });
  }
  
  // Carregar todos os históricos de preço
  loadAllStockHistories(): void {
    this.stockHistoryService.getAllStockHistories().subscribe((data) => {
      this.stockHistories = data;
      console.log(data, 'AllStocks');
    });
  }
  
  
  // Obter detalhes de um único histórico
  getStockDetails(id: number): void {
    this.stockHistoryService.getStockHistoryById(id).subscribe((data) => {
      this.stockDetails = data;
      console.log(data, 'AllStocksById');
    });
  }

  // Método para calcular lucro ou prejuízo
  calculateProfitOrLoss(trade: Trade): string {
    const currentPrice = this.currentPrices[trade.asset.symbol];
    if (!currentPrice) return 'Preço atual não disponível';

    const purchasePrice = trade.asset.price;
    const quantity = trade.quantity;
    const tradeType = trade.tradeType.name;

    let result: number;
    if (tradeType === 'Buy') {
      result = (currentPrice - purchasePrice) * quantity;
    } else if (tradeType === 'Sell') {
      result = (purchasePrice - currentPrice) * quantity;
    } else {
      return 'Tipo de transação inválido';
    }

    return result >= 0
      ? `Lucro: ${result.toFixed(2)}`
      : `Prejuízo: ${result.toFixed(2)}`;
  }
}
