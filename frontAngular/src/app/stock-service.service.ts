import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StockServiceService {
  private apiUrl = 'http://localhost:8080/stock-history'; // Substitua pela URL do seu backend

  constructor(private http: HttpClient) {}

  // 1. Obter um histórico de preço por ID
  getStockHistoryById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/get/${id}`);
  }

  // 2. Obter todos os históricos de preço
  getAllStockHistories(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/get-all`);
  }

  // 3. Obter históricos de preço por ID do ativo
  getStockHistoriesByAssetId(assetId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/get-by-asset-id/${assetId}`);
  }

  // 4. Adicionar ou atualizar histórico de preço para um ativo
  addStockHistory(symbol: string, price: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/add`, null, {
      params: { symbol, price: price.toString() },
    });
  }

  // 5. Adicionar ou atualizar histórico de preço com timestamp
  addStockHistoryWithTimestamp(
    symbol: string,
    price: number,
    timestamp: string
  ): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/add-with-timestamp`, null, {
      params: { symbol, price: price.toString(), timestamp },
    });
  }
}
