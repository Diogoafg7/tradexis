import { Component, ChangeDetectionStrategy } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
import { ApexChart, ChartType, ApexAxisChartSeries, ApexTitleSubtitle, ApexXAxis } from 'ngx-apexcharts';
import { NgxApexchartsModule } from 'ngx-apexcharts';

@Component({
  selector: 'app-dashboard',
  imports: [NgFor, FormsModule, NgClass, NgxApexchartsModule, HeaderComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush, // Melhora a performance
})
export class DashboardComponent {
  stocks: any[] = []; // Lista completa de ações disponíveis
  filteredStocks: any[] = []; // Ações filtradas com base na pesquisa e filtros
  searchQuery: string = ''; // Termo de pesquisa
  priceMin: number | null = null; // Filtro de preço mínimo
  priceMax: number | null = null; // Filtro de preço máximo
  variationFilter: string = ''; // Filtro de variação (positiva, negativa)

  selectedStock: any = null; // Ação selecionada para compra
  buyAmount: number = 0; // Quantidade a comprar

  constructor() {
      // Exemplo de dados de ações
      this.stocks = [
        { name: 'Apple Inc.', symbol: 'AAPL', price: 175.64, change: 0.52, daily: '↑' },
        { name: 'Tesla Inc.', symbol: 'TSLA', price: 687.45, change: -1.13, daily: '↓' },
        { name: 'Microsoft Corp.', symbol: 'MSFT', price: 305.23, change: 1.34, daily: '↑' },
        { name: 'Amazon.com Inc.', symbol: 'AMZN', price: 125.30, change: -0.21, daily: '↓' }
      ];
      this.filteredStocks = [...this.stocks]; // Inicializa com todas as ações
    }
  
    // Filtra as ações com base na pesquisa e nos filtros
    filterStocks(): void {
      const query = this.searchQuery.trim().toLowerCase();
  
      this.filteredStocks = this.stocks.filter((stock) => {
        const matchesSearch =
          !query ||
          stock.name.toLowerCase().includes(query) ||
          stock.symbol.toLowerCase().includes(query);
  
        const matchesPriceMin = this.priceMin == null || stock.price >= this.priceMin;
        const matchesPriceMax = this.priceMax == null || stock.price <= this.priceMax;
  
        const matchesVariation =
          !this.variationFilter ||
          (this.variationFilter === 'positive' && stock.change > 0) ||
          (this.variationFilter === 'negative' && stock.change < 0);
  
        return matchesSearch && matchesPriceMin && matchesPriceMax && matchesVariation;
      });
    }

   // Limpa os campos de filtro e pesquisa
   clearFilters(): void {
    this.searchQuery = '';
    this.priceMin = null;
    this.priceMax = null;
    this.variationFilter = '';
    this.filteredStocks = [...this.stocks];
  }

  // Alterna a seção de compra
  toggleBuySection(stock: any): void {
    this.selectedStock = this.selectedStock === stock ? null : stock;
  }

  // Confirma a compra
  confirmPurchase(stock: any): void {
    console.log(`Comprou ${this.buyAmount} de ${stock.name} (${stock.symbol})`);
    this.buyAmount = 0; // Reseta o montante
    this.selectedStock = null; // Fecha a seção de compra
  }
}
