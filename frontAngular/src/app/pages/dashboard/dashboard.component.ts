import { Component, ChangeDetectionStrategy } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
import { ApexChart, ChartType, ApexAxisChartSeries, ApexTitleSubtitle, ApexXAxis } from 'ngx-apexcharts';
import { NgxApexchartsModule } from 'ngx-apexcharts';

@Component({
  selector: 'app-dashboard',
  imports: [NgFor, NgIf, FormsModule, NgClass, NgxApexchartsModule, HeaderComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush, // Melhora a performance
})
export class DashboardComponent {
  stocks = [
    {
      name: 'TESLA',
      symbol: 'TSLA',
      price: 336.09,
      change: 0.07,
      daily: '0.21%',
      chartData: [10, 41, 35, 51, 49, 62, 69, 91, 148],
    },
    {
      name: 'AMAZON',
      symbol: 'AMZN',
      price: 3250.10,
      change: -0.15,
      daily: '1.08%',
      chartData: [20, 50, 45, 60, 70, 80, 90, 110, 140],
    },
  ];

  selectedStock: any = null;
  buyAmount: number | null = null;

  // Alternar exibição da seção de compra
  toggleBuySection(stock: any) {
    this.selectedStock = this.selectedStock === stock ? null : stock;
  }

  // Confirmação de compra
  confirmPurchase(stock: any) {
    if (this.buyAmount && this.buyAmount > 0) {
      alert(`Compra confirmada: ${this.buyAmount} ações de ${stock.name}`);
      this.buyAmount = null;
      this.selectedStock = null;
    } else {
      alert('Por favor, insira um valor válido.');
    }
  }

  // Configuração de gráfico estático (uma vez por ação)
  getChartOptions(): {
    series: ApexAxisChartSeries;
    chart: ApexChart;
    title: ApexTitleSubtitle;
    xaxis: ApexXAxis;
  } {
    return {
      series: [
        {
          name: 'Exemplo',
          data: [10, 20, 30, 40], // Dados estáticos para teste
        },
      ],
      chart: {
        type: 'line',
        height: 300,
        animations: { enabled: false },
      },
      title: {
        text: 'Gráfico de Teste',
        align: 'left',
      },
      xaxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr'],
      },
    };
  }
}
