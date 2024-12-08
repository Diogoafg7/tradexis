import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-rodape-acoes',
  imports: [CommonModule],
  templateUrl: './rodape-acoes.component.html',
  styleUrl: './rodape-acoes.component.scss'
})
export class RodapeAcoesComponent {
  acoes: Top10[] = [];

  ngOnInit(): void {
    // Gera dados dummy para simulação
    this.generateDummyData();
  }

  // Função para gerar dados fictícios
  generateDummyData(): void {
    this.acoes = [
      { api1: 'AAPL', api2: '', api3: 0.025, api4: 174.55, api5: 0, api6: 0, api7: 0 },
      { api1: 'MSFT', api2: '', api3: -0.014, api4: 318.33, api5: 0, api6: 0, api7: 0 },
      { api1: 'GOOGL', api2: '', api3: 0.012, api4: 138.45, api5: 0, api6: 0, api7: 0 },
      { api1: 'AMZN', api2: '', api3: -0.009, api4: 144.55, api5: 0, api6: 0, api7: 0 },
      { api1: 'TSLA', api2: '', api3: 0.041, api4: 265.56, api5: 0, api6: 0, api7: 0 },
      { api1: 'META', api2: '', api3: 0.023, api4: 311.88, api5: 0, api6: 0, api7: 0 },
      { api1: 'NVDA', api2: '', api3: 0.056, api4: 473.02, api5: 0, api6: 0, api7: 0 },
      { api1: 'NFLX', api2: '', api3: -0.007, api4: 410.22, api5: 0, api6: 0, api7: 0 },
      { api1: 'ADBE', api2: '', api3: 0.017, api4: 530.50, api5: 0, api6: 0, api7: 0 },
      { api1: 'ORCL', api2: '', api3: -0.003, api4: 114.22, api5: 0, api6: 0, api7: 0 }
    ];
  }
}

// Interface para tipar as ações
interface Top10 {
  api1: string; // Símbolo
  api2: string; // Não utilizado no exemplo
  api3: number; // Porcentagem de variação
  api4: number; // Preço atual
  api5: number; // Não utilizado no exemplo
  api6: number; // Não utilizado no exemplo
  api7: number; // Não utilizado no exemplo
}

