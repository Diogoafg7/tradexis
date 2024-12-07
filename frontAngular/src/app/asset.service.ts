import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AssetService {
  private apiUrl = 'http://localhost:8080'; // Base API URL

  constructor(private http: HttpClient) {}

  getAssets(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/assets/list`);
  }

  addTrade(userId: number, assetId: number, tradeTypeName: string, quantity: number): Observable<any> {
    const url = `http://localhost:8080/trades/add-details/${userId}/${assetId}/${tradeTypeName}/${quantity}`;
    return this.http.post(url, {});
  }
}
