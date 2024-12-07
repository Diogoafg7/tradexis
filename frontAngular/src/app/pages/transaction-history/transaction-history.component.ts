import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CommonModule, NgClass, NgFor, NgIf } from '@angular/common';
import { StockServiceService } from '../../stock-service.service';

@Component({
  selector: 'app-transaction-history',
  imports: [HeaderComponent, NgFor, NgIf, NgClass, CommonModule],
  templateUrl: './transaction-history.component.html',
  styleUrl: './transaction-history.component.scss'
})

export class TransactionHistoryComponent {

  stockHistories: any[] = [];
  stockDetails: any | null = null;

  constructor(private stockHistoryService: StockServiceService) {}

  ngOnInit(): void {
    this.loadAllStockHistories();
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

}
