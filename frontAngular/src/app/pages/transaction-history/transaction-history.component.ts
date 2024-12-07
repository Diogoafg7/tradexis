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
  trades: Trade[] = []; // Transações do endpoint de Trade
  currentPrices: { [key: string]: number } = {}; // Preços atuais dos ativos
  stockHistories: any[] = []; // Histórico de ações
  stockDetails: any | null = null; // Detalhes do histórico selecionado

  constructor(
    private stockHistoryService: StockServiceService,
    private tradeService: TradeService
  ) {}

  ngOnInit(): void {
    this.loadAllStockHistories();

    this.tradeService.getAllTrades().subscribe((data: Trade[]) => {
      this.trades = data;

      // Mock: Preços atuais dos ativos (você pode substituir por uma API real)
      this.currentPrices = {
        BTC: 50.0, // Preço atual do Bitcoin
        ETH: 4000.0, // Preço atual do Ethereum
      };
    });
  }

  // Carregar todos os históricos de ações
  loadAllStockHistories(): void {
    this.stockHistoryService.getAllStockHistories().subscribe((data) => {
      this.stockHistories = data;
      console.log(data, 'AllStocks');
    });
  }

  // Obter detalhes de um histórico específico
  getStockDetails(id: number): void {
    this.stockHistoryService.getStockHistoryById(id).subscribe((data) => {
      this.stockDetails = data;
      console.log(data, 'AllStocksById');
    });
  }

  // Método para calcular lucro ou prejuízo de uma transação
  calculateProfitOrLoss(stockHistory: any): string {
    const currentPrice = this.currentPrices[stockHistory.asset.symbol];
    if (!currentPrice) return 'Preço atual não disponível';

    // Encontrar as transações relacionadas ao ativo selecionado
    const relatedTrades = this.trades.filter(
      (trade) => trade.asset.symbol === stockHistory.asset.symbol
    );

    let totalProfitOrLoss = 0;
    relatedTrades.forEach((trade) => {
      const purchasePrice = trade.asset.price;
      const quantity = trade.quantity;
      const tradeType = trade.tradeType.name;

      let result: number;
      if (tradeType === 'Buy') {
        result = (currentPrice - purchasePrice) * quantity;
      } else if (tradeType === 'Sell') {
        result = (purchasePrice - currentPrice) * quantity;
      } else {
        return; // Caso o tipo de transação seja inválido
      }

      totalProfitOrLoss += result;
    });

    return totalProfitOrLoss >= 0
      ? `Lucro: ${totalProfitOrLoss.toFixed(2)}`
      : `Prejuízo: ${totalProfitOrLoss.toFixed(2)}`;
  }
}
