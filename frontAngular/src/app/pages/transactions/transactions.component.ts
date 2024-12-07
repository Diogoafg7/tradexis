import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CommonModule } from '@angular/common';
import { TradeService } from '../../trade.service';
import { StockServiceService } from '../../stock-service.service';

@Component({
  selector: 'app-transactions',
  imports: [HeaderComponent, CommonModule],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.scss'
})
export class TransactionsComponent {
  trades: any[] = [];  // Transações ativas de compra
  stockHistories: any[] = [];  // Histórico de transações

  constructor(
    private tradeService: TradeService,
    private stockHistoryService: StockServiceService  // Serviço para histórico
  ) {}

  ngOnInit(): void {
    this.loadTrades();
  }

  // Carrega todas as trades usando o serviço
  loadTrades(): void {
    this.tradeService.getAllTrades().subscribe(
      (data) => {
        this.trades = data;
        console.log('Trades carregadas:', data);
      },
      (error) => {
        console.error('Erro ao carregar trades:', error);
        alert('Erro ao carregar as transações.');
      }
    );
  }

  // Função para "vender" uma trade
  sellTrade(tradeId: number): void {
    const tradeToSell = this.trades.find((trade) => trade.id === tradeId);
    if (!tradeToSell) return;

    // Calcular o preço médio de compra de todas as compras anteriores do ativo
    const totalQuantity = this.trades
      .filter((trade) => trade.assetId === tradeToSell.assetId)
      .reduce((acc, trade) => acc + trade.quantity, 0);

    const totalCost = this.trades
      .filter((trade) => trade.assetId === tradeToSell.assetId)
      .reduce((acc, trade) => acc + trade.price * trade.quantity, 0);

    const averagePrice = totalCost / totalQuantity;

    // Agora calculamos o lucro ou prejuízo com base no preço da venda
    const sellPrice = tradeToSell.price;  // Preço de venda
    const quantitySold = tradeToSell.quantity;

    const profitOrLoss = (sellPrice - averagePrice) * quantitySold;

    // Gravar no histórico
    this.stockHistoryService.addHistory({
      assetId: tradeToSell.assetd,
      assetName: tradeToSell.assetName,
      assetSymbol: tradeToSell.assetSymbol,
      assetTypeName: tradeToSell.assetTypeName,
      price: sellPrice,
      timestamp: new Date(),
      tradeType: 'Sell',
      profitOrLoss: profitOrLoss,
      quantity: quantitySold
    }).subscribe(
      (historyData) => {
        console.log('Histórico de transação gravado:', historyData);
      },
      (error) => {
        console.error('Erro ao gravar no histórico:', error);
      }
    );

    // Excluir a trade
    this.tradeService.deleteTrade(tradeId).subscribe(
      () => {
        this.trades = this.trades.filter((trade) => trade.id !== tradeId);
        console.log(`Trade com ID ${tradeId} vendida com sucesso!`);
      },
      (error) => {
        console.error(`Erro ao vender trade com ID ${tradeId}:`, error);
        alert('Erro ao vender a transação.');
      }
    );
  }

}
