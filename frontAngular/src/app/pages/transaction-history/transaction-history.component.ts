import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CommonModule, NgClass, NgFor, NgIf } from '@angular/common';
import { StockServiceService } from '../../stock-service.service';
import { Trade } from '../../models/trade';
import { TradeService } from '../../trade.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-transaction-history',
  imports: [HeaderComponent, NgFor, NgIf, NgClass, CommonModule, FormsModule],
  templateUrl: './transaction-history.component.html',
  styleUrl: './transaction-history.component.scss'
})

export class TransactionHistoryComponent {
  trades: Trade[] = [];
  stockHistories: any[] = [];
  filteredStockHistories: any[] = [];
  stockDetails: any | null = null;
  currentPrices: { [key: string]: number } = {};

  // Filtros
  searchQuery: string = '';
  assetTypeFilter: string = '';
  startDate: string = '';
  endDate: string = '';

  constructor(
    private stockHistoryService: StockServiceService,
    private tradeService: TradeService
  ) {}

  ngOnInit(): void {
    this.loadAllStockHistories();

    this.tradeService.getAllTrades().subscribe((data: Trade[]) => {
      this.trades = data;

      // Mock de preços atuais
      this.currentPrices = {
        BTC: 50.0,
        ETH: 4000.0
      };
    });
  }

  // Carregar todos os históricos
  loadAllStockHistories(): void {
    this.stockHistoryService.getAllStockHistories().subscribe((data) => {
      this.stockHistories = data;
      this.filteredStockHistories = [...data]; // Inicializa com todos os históricos
    });
  }

  // Obter detalhes de um histórico
  getStockDetails(id: number): void {
    this.stockHistoryService.getStockHistoryById(id).subscribe((data) => {
      this.stockDetails = data;
    });
  }

  // Filtrar históricos com base em critérios
  filterStockHistories(): void {
    this.filteredStockHistories = this.stockHistories.filter((history) => {
      const matchesSearchQuery =
        this.searchQuery.trim() === '' ||
        history.asset.name
          .toLowerCase()
          .includes(this.searchQuery.toLowerCase()) ||
        history.asset.symbol
          .toLowerCase()
          .includes(this.searchQuery.toLowerCase());

      const matchesAssetType =
        this.assetTypeFilter === '' || history.assetTypeName === this.assetTypeFilter;

      const matchesDateRange =
        (!this.startDate || new Date(history.timestamp) >= new Date(this.startDate)) &&
        (!this.endDate || new Date(history.timestamp) <= new Date(this.endDate));

      return matchesSearchQuery && matchesAssetType && matchesDateRange;
    });
  }

  // Limpar filtros
  clearFilters(): void {
    this.searchQuery = '';
    this.assetTypeFilter = '';
    this.startDate = '';
    this.endDate = '';
    this.filteredStockHistories = [...this.stockHistories];
  }

  // Cálculo de lucro/prejuízo
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
