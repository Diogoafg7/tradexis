import { Component, OnInit, OnDestroy } from '@angular/core';
import { Chart } from 'chart.js/auto'; // Ensures all Chart.js modules are auto-imported
import { StockServiceService } from '../../stock-service.service';
import { StockData } from '../../models/StockData';

@Component({
  selector: 'app-graphic',
  templateUrl: './graphic.component.html',
  styleUrls: ['./graphic.component.scss'],
})
export class GraphicComponent implements OnInit, OnDestroy {
  private chart: any;

  constructor(private stockService: StockServiceService) {}

  ngOnInit(): void {
    this.loadChartData(); // Carrega os dados ao inicializar o componente
  }

  ngOnDestroy(): void {
    this.destroyChart(); // Destroi o gráfico quando o componente for destruído
  }

  private destroyChart(): void {
    if (this.chart) {
      this.chart.destroy(); // Destroi o gráfico para evitar problemas de memória
      this.chart = null;
    }
  }

  loadChartData(): void {
    this.stockService.getStockData().subscribe(
      (data: StockData[]) => { // Tipagem explícita do retorno
        if (data && data.length > 0) {
          console.log('Dados recebidos:', data);
          const labels = data.map((item) => new Date(item.createdAt).toLocaleDateString()); // Converte para formato legível
          const prices = data.map((item) => item.price);

          this.renderChart(labels, prices);
        } else {
          console.error('Nenhum dado foi recebido');
        }
      },
      (error) => {
        console.error('Erro ao buscar dados das ações:', error);
      }
    );
  }

  private renderChart(labels: string[], prices: number[]): void {
    this.destroyChart(); // Certifique-se de destruir o gráfico anterior

    const ctx = document.getElementById('stockChart') as HTMLCanvasElement;
    if (ctx) {
      this.chart = new Chart(ctx, {
        type: 'line',
        data: {
          labels,
          datasets: [
            {
              label: 'Preço das Ações',
              data: prices,
              borderColor: 'rgba(75, 192, 192, 1)',
              backgroundColor: 'rgba(75, 192, 192, 0.2)',
              borderWidth: 2,
              tension: 0.4, // Suaviza a linha
            },
          ],
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              display: true,
              position: 'top',
            },
          },
          scales: {
            x: {
              type: 'category', // Usamos 'category' para rótulos de data como texto
              title: {
                display: true,
                text: 'Data',
              },
            },
            y: {
              beginAtZero: false,
              title: {
                display: true,
                text: 'Preço',
              },
            },
          },
        },
      });
    } else {
      console.error('Elemento do gráfico não encontrado');
    }
  }
}

