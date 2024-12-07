import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TradeService {

  private baseUrl = 'http://localhost:8080/trades'; // Substitua pelo URL base correto da sua API

  constructor(private http: HttpClient) {}

  // Obter todas as trades
  getAllTrades(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/list`);
  }

  // Obter uma trade pelo ID
  getTradeById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  // Obter trades de um usuário pelo ID do usuário
  getTradesByUserId(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/user/${userId}`);
  }

  // Obter trades por ID do ativo
  getTradesByAssetId(assetId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/asset/${assetId}`);
  }

  // Obter trades por ID do tipo de trade
  getTradesByTradeTypeId(tradeTypeId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/type/${tradeTypeId}`);
  }

  // Adicionar uma nova trade
  addTrade(userId: number, assetId: number, tradeTypeName: string, quantity: number): Observable<any> {
    const payload = { userId, assetId, tradeTypeName, quantity };
    return this.http.post<any>(`${this.baseUrl}/add`, payload);
  }

  // Atualizar uma trade existente
  updateTrade(trade: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/update`, trade);
  }

  // Deletar uma trade pelo ID
  deleteTrade(id: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/delete/${id}`);
  }

  // Deletar todas as trades
  deleteAllTrades(): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/delete-all`);
  }

  // Adicionar uma trade com detalhes específicos
  addTradeWithDetails(userId: number, assetId: number, tradeTypeName: string, quantity: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/add-details/${userId}/${assetId}/${tradeTypeName}/${quantity}`, {});
  }
}
