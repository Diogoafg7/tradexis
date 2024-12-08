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
  trades: any[] = []; // Todas as transações
  filteredTrades: any[] = []; // Transações filtradas
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
    this.tradeService.getAllTrades().subscribe(
      (data) => {
        this.trades = data;
        this.filteredTrades = data; // Inicializa a lista filtrada com todas as transações
        console.log('Trades carregadas:', data);
      },
      (error) => {
        console.error('Erro ao carregar trades:', error);
        alert('Erro ao carregar as transações.');
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

  // Função para "vender" uma transação
  sellTrade(tradeId: number): void {
    const tradeToSell = this.trades.find((trade) => trade.id === tradeId);
    if (!tradeToSell) return;

    // Calcula o preço médio de compra
    const totalQuantity = this.trades
      .filter((trade) => trade.assetId === tradeToSell.assetId)
      .reduce((acc, trade) => acc + trade.quantity, 0);

    const totalCost = this.trades
      .filter((trade) => trade.assetId === tradeToSell.assetId)
      .reduce((acc, trade) => acc + trade.price * trade.quantity, 0);

    const averagePrice = totalCost / totalQuantity;

    // Lucro ou prejuízo
    const sellPrice = tradeToSell.price;
    const quantitySold = tradeToSell.quantity;
    const profitOrLoss = (sellPrice - averagePrice) * quantitySold;

    // Grava no histórico
    this.stockHistoryService
      .addHistory({
        assetId: tradeToSell.assetId,
        assetName: tradeToSell.asset.name,
        assetSymbol: tradeToSell.asset.symbol,
        assetTypeName: tradeToSell.asset.typeName,
        price: sellPrice,
        timestamp: new Date(),
        tradeType: 'Sell',
        profitOrLoss: profitOrLoss,
        quantity: quantitySold,
      })
      .subscribe(
        (historyData) => {
          console.log('Histórico de transação gravado:', historyData);
        },
        (error) => {
          console.error('Erro ao gravar no histórico:', error);
        }
      );

    // Remove a transação vendida
    this.tradeService.deleteTrade(tradeId).subscribe(
      () => {
        this.trades = this.trades.filter((trade) => trade.id !== tradeId);
        this.filterTrades(); // Atualiza a lista filtrada após a venda
        console.log(`Trade com ID ${tradeId} vendida com sucesso!`);
      },
      (error) => {
        console.error(`Erro ao vender trade com ID ${tradeId}:`, error);
        alert('Erro ao vender a transação.');
      }
    );
  }

}
