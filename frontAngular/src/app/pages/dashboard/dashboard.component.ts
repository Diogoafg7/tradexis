import { Component,OnInit , ChangeDetectionStrategy } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
//import { ApexChart, ChartType, ApexAxisChartSeries, ApexTitleSubtitle, ApexXAxis } from 'ngx-apexcharts';
//import { NgxApexchartsModule } from 'ngx-apexcharts';
import { AssetService } from '../../asset.service';


@Component({
  selector: 'app-dashboard',
  imports: [NgFor, NgIf, FormsModule,  /*NgxApexchartsModule, */ HeaderComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
 // changeDetection: ChangeDetectionStrategy.OnPush, 
})
export class DashboardComponent  {
 stocks: any[] = [ 
  // {
  //   name: 'TESLA',
  //   symbol: 'TSLA',
  //   price: 336.09,
  //   change: 0.07,
  //   daily: '0.21%',
  //   chartData: [10, 41, 35, 51, 49, 62, 69, 91, 148],
  // }
];
  selectedStock: any = null;
  buyAmount: number = 0;


  constructor(private assetService: AssetService) {}

  ngOnInit(): void {
    this.loadAssets();
    console.log('oninit' );
  }

  
  loadAssets(): void {
    this.assetService.getAssets().subscribe(
      (data) => {
        this.stocks = data;
        console.log('Fetched dataIn:', data);
      },
      (error) => {
        console.error('Error fetching assets', error);
      }
    );
  }

  

  toggleBuySection(stock: any): void {
    this.selectedStock = this.selectedStock === stock ? null : stock;
  }

  confirmPurchase(stock: any): void {
    console.log('Purchasing', this.buyAmount, 'of', stock.symbol);
    // Add purchase logic here
  }



   // Configuração de gráfico estático (uma vez por ação)
  //  getChartOptions(): {
  //    series: ApexAxisChartSeries;
  //    chart: ApexChart;
  //    title: ApexTitleSubtitle;
  //    xaxis: ApexXAxis;
  //  } {
  //    return {
  //      series: [
  //        {
  //          name: 'Exemplo',
  //          data: [10, 20, 30, 40], // Dados estáticos para teste
  //        },
  //      ],
  //      chart: {
  //        type: 'line',
  //        height: 300,
  //        animations: { enabled: false },
  //      },
  //      title: {
  //        text: 'Gráfico de Teste',
  //        align: 'left',
  //      },
  //      xaxis: {
  //        categories: ['Jan', 'Feb', 'Mar', 'Apr'],
  //      },
  //    };
  //  }
}
