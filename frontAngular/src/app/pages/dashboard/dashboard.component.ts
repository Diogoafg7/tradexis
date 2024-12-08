import { Component, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
import { NgxApexchartsModule } from 'ngx-apexcharts';
import { RodapeAcoesComponent } from '../../components/rodape-acoes/rodape-acoes.component';
import { GraphicComponent } from "../../components/graphic/graphic.component";
import { StockServiceService } from '../../stock-service.service';
import { StockData } from '../../models/StockData';

@Component({
  selector: 'app-dashboard',
  imports: [NgFor, FormsModule, NgClass, NgxApexchartsModule, HeaderComponent, RodapeAcoesComponent, GraphicComponent, GraphicComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  /* changeDetection: ChangeDetectionStrategy.OnPush, // Melhora a performance */
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
  acoes: StockData[] = [];

  constructor(private cdRef: ChangeDetectorRef, private stockService: StockServiceService) {
    // Exemplo de dados de ações
    this.stocks = [
      { name: 'Apple Inc.', symbol: 'AAPL', price: 175.64, change: 0.52, daily: '↑' },
      { name: 'Tesla Inc.', symbol: 'TSLA', price: 687.45, change: -1.13, daily: '↓' },
      { name: 'Microsoft Corp.', symbol: 'MSFT', price: 305.23, change: 1.34, daily: '↑' },
      { name: 'Amazon.com Inc.', symbol: 'AMZN', price: 125.30, change: -0.21, daily: '↓' }
    ];
    this.filteredStocks = [...this.stocks]; // Inicializa com todas as ações
  }

  ngOnInit(): void {
    // Garantir que o conteúdo do componente seja atualizado e os filtros estejam aplicados
    this.updateRodape();
  }

  // Método para atualizar o rodapé ou realizar outras operações necessárias
  updateRodape(): void {
    // Recarregar ou atualizar o rodapé conforme necessário
  this.stockService.getStockData().subscribe(data => {
    this.acoes = data; // Atualizar o array de ações
    this.cdRef.detectChanges(); // Forçar detecção de mudanças
  });
  }
  
  
}
