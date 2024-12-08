import { Component,OnInit , ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
import { NgxApexchartsModule } from 'ngx-apexcharts';
import { RodapeAcoesComponent } from '../../components/rodape-acoes/rodape-acoes.component';
import { GraphicComponent } from "../../components/graphic/graphic.component";
import { StockServiceService } from '../../stock-service.service';
import { StockData } from '../../models/StockData';
import { AssetService } from '../../asset.service';
import { ProfileServiceService } from '../../profile-service.service';


@Component({
  selector: 'app-dashboard',
  imports: [NgFor, NgIf, NgClass, FormsModule,  NgxApexchartsModule, HeaderComponent, RodapeAcoesComponent, GraphicComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  /* changeDetection: ChangeDetectionStrategy.OnPush, // Melhora a performance */

})
export class DashboardComponent  {
 stocks: any[] = [ ];
 acoes: StockData[] = [];
  selectedStock: any = null;
  buyAmount: number = 0;

  constructor(
    private assetService: AssetService,
    private profileService: ProfileServiceService,
    private stockService: StockServiceService ,
    private cdRef: ChangeDetectorRef
  ) {}



  loadAssets(): void {
    this.assetService.getAssets().subscribe(
      (data) => {
        this.stocks = data;
        // .map((stock) => ({
        //   ...stock,
        //   id: stock.assetId, // Map `assetId` from backend to `id` for consistency
        // }));
        console.log('Fetched data:', this.stocks);

      },
      (error) => {
        console.error('Error fetching assets', error);
      }
    );
  }

  ngOnInit(): void {
    this.loadAssets();
    // Garantir que o conteúdo do componente seja atualizado e os filtros estejam aplicados
    this.updateRodape();
    console.log('oninit' );
  }

  // Método para atualizar o rodapé ou realizar outras operações necessárias
  updateRodape(): void {
    // Recarregar ou atualizar o rodapé conforme necessário
  this.stockService.getStockData().subscribe(data => {
    this.acoes = data; // Atualizar o array de ações
    this.cdRef.detectChanges(); // Forçar detecção de mudanças
  });
  }


  toggleBuySection(stock: any): void {
    this.selectedStock = this.selectedStock === stock ? null : stock;
  }

  confirmPurchase(stock: any): void {
    const userId = this.profileService.getCurrentUserId();
    //console.log('userid', userId );
    const assetId = stock.id;
    //console.log('assetId', assetId );
    const tradeTypeName = 'BUY';
    const quantity = this.buyAmount;

    if (quantity <= 0) {
      console.error('Quantity must be greater than zero.');
      return;
    }

    this.assetService.addTrade(userId, assetId, tradeTypeName, quantity).subscribe(
      (response) => {
        console.log('Trade added successfully:', response);
        alert('Trade successfully submitted!');
        // Reset  page elements
        this.selectedStock = null;
        this.buyAmount = 0;
      },
      (error) => {
        console.error('Error adding trade:', error);
        alert('Failed to submit trade.');
      }
    );
      console.log('Purchasing', this.buyAmount, 'of', stock.symbol);

  }


}
