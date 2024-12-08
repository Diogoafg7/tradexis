import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CommonModule, NgClass, NgFor, NgIf } from '@angular/common';
import { StockServiceService } from '../../stock-service.service';
import { Trade } from '../../models/trade';
import { TradeService } from '../../trade.service';
import { FormsModule } from '@angular/forms';
import { RodapeAcoesComponent } from '../../components/rodape-acoes/rodape-acoes.component';

@Component({
  selector: 'app-transaction-history',
  imports: [HeaderComponent, NgFor, NgIf, NgClass, CommonModule, FormsModule, RodapeAcoesComponent],
  templateUrl: './transaction-history.component.html',
  styleUrl: './transaction-history.component.scss'
})

export class TransactionHistoryComponent {
  trades: Trade[] = [];
  tempData: any[] = [];
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



  loadAllStockHistories(): void {
    this.tradeService.getTradesByType(2).subscribe(
      (data) => {
        this.tempData = data.filter(
          (trade) => trade.user.id === 10
        );
        this.stockHistories = this.tempData; 
        this.filteredStockHistories = [...this.tempData]; 
        console.log('Filtered trades loaded:', this.tempData);
      },
      (error) => {
        console.error('Error loading trades:', error);
        alert('Error loading transactions.');
      }
    );
  }

 
  getStockDetails(id: number): void {
    this.stockHistoryService.getStockHistoryById(id).subscribe((data) => {
      this.stockDetails = data;
    });
  }


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


  clearFilters(): void {
    this.searchQuery = '';
    this.assetTypeFilter = '';
    this.startDate = '';
    this.endDate = '';
    this.filteredStockHistories = [...this.stockHistories];
  }


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
