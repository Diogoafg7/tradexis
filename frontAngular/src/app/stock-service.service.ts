import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StockServiceService {

  private apiUrl = 'https://http://localhost:4200/stocks'; // Buscar stocks do histórico

  constructor(private http: HttpClient) {}

  getStockData(stockSymbol: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${stockSymbol}`);
  }
}
