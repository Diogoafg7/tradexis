import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CommonModule } from '@angular/common';
import { TradeService } from '../../trade.service';
import { StockServiceService } from '../../stock-service.service';
import { FormsModule } from '@angular/forms';
import { RodapeAcoesComponent } from '../../components/rodape-acoes/rodape-acoes.component';

@Component({
  selector: 'app-transactions',
  imports: [HeaderComponent, CommonModule, FormsModule, RodapeAcoesComponent],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.scss'
})
export class TransactionsComponent {
  trades: any[] = []; 
  filteredTrades: any[] = []; // Transações filtradas
  tempData: any[] = [];
  searchQuery: string = ''; // Texto da barra de pesquisa
  priceMin: number | null = null; // Filtro de preço mínimo
  priceMax: number | null = null; // Filtro de preço máximo
  variationFilter: string = ''; // Filtro de variação (positiva ou negativa)

  constructor(
    private tradeService: TradeService,
    private stockHistoryService: StockServiceService
  ) {}

  ngOnInit(): void {
    this.loadTrades();
  }

  // Carrega todas as transações e inicializa a lista filtrada
  loadTrades(): void {
    this.tradeService.getTradesByType(1).subscribe(
      (data) => {
        this.tempData = data.filter(
          (trade) => trade.user.id === 10
        );
        this.trades = this.tempData; 
        this.filteredTrades = this.tempData;
        console.log('Filtered trades loaded:', data);
      },
      (error) => {
        console.error('Error loading trades:', error);
        alert('Error loading transactions.');
      }
    );
  }
  

  // Filtra as transações com base na pesquisa e nos filtros
  filterTrades(): void {
    this.filteredTrades = this.trades.filter((trade) => {
      // Verifica se corresponde ao texto de pesquisa
      const matchesSearch =
        !this.searchQuery ||
        trade.asset.name
          .toLowerCase()
          .includes(this.searchQuery.toLowerCase()) ||
        trade.asset.symbol
          .toLowerCase()
          .includes(this.searchQuery.toLowerCase());

      // Verifica o preço mínimo e máximo
      const matchesPriceMin =
        this.priceMin === null || trade.price >= this.priceMin;
      const matchesPriceMax =
        this.priceMax === null || trade.price <= this.priceMax;

      // Verifica a variação
      const matchesVariation =
        !this.variationFilter ||
        (this.variationFilter === 'positive' && trade.changePercentage >= 0) ||
        (this.variationFilter === 'negative' && trade.changePercentage < 0);

      // Retorna true se todos os critérios forem atendidos
      return matchesSearch && matchesPriceMin && matchesPriceMax && matchesVariation;
    });
  }

  // Limpa os filtros
  clearFilters(): void {
    this.searchQuery = '';
    this.priceMin = null;
    this.priceMax = null;
    this.variationFilter = '';
    this.filterTrades(); // Atualiza a lista de transações filtradas
  }

  sellTrade(tradeId: number):void{
    // Find the trade to sell
  const trade = this.filteredTrades.find((t) => t.id === tradeId);
  if (!trade) {
    console.error('Trade not found:', tradeId);
    alert('Trade not found.');
    return;
  }

  const userId = trade.user.id;
  const assetId = trade.asset.id;
  const quantity = trade.quantity;
  const tradeTypeName = "SELL";

  // Add the new "SELL" trade
  this.tradeService.addTrade(userId, assetId, tradeTypeName, quantity).subscribe(
    (response) => {
      console.log('Sell trade added successfully:', response);
      this.tradeService.deleteTradeById(tradeId).subscribe(
        () => {
          console.log('Trade deleted successfully:', tradeId);
          this.filteredTrades = this.filteredTrades.filter((t) => t.id !== tradeId);
          alert('Trade sold successfully!');
        },
        (error) => {
          console.error('Error deleting trade:', error);
          alert('Failed to delete trade.');
        }
      );
    },
    (error) => {
      console.error('Error adding sell trade:', error);
      alert('Failed to add sell trade.');
    }
  );
  }

}
