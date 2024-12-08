import { CommonModule } from '@angular/common';
import { Component, Input, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { StockServiceService } from '../../stock-service.service';
import { StockData } from '../../models/StockData';

@Component({
  selector: 'app-rodape-acoes',
  imports: [CommonModule],
  templateUrl: './rodape-acoes.component.html',
  styleUrl: './rodape-acoes.component.scss'
})
export class RodapeAcoesComponent {
  acoes: StockData[] = [];

  constructor(private stockService: StockServiceService) {}

  ngOnInit(): void {
    this.fetchStockData();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['acoes']) {
      this.fetchStockData();
    }
  }

  fetchStockData(): void {
    this.stockService.getStockData().subscribe(
      (data: StockData[]) => {
        this.acoes = data;
        console.log('Ações carregadas:', data);
      },
      (error) => {
        console.error('Erro ao buscar dados das ações:', error);
      }
    );
  }
  
  
  
}


