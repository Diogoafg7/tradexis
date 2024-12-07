import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AssetService {
  private apiUrl = 'http://localhost:8080/assets'; 

  constructor(private http: HttpClient) {}

  getAssets(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/list`);
  }
}
