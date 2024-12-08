import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WalletService {
  private apiUrl = 'http://localhost:8080/wallets'; 
  
  constructor(private http: HttpClient) { }

  getWallet(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${userId}`);
  }

  getBalanceByUserId(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/user/${userId}/balance`);
  }

  updateWalletById(userId: number, balance: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/user/${userId}/balance/${balance}`, {}); 
  }
}

