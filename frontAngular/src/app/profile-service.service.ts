import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Profile } from './models/profile';


@Injectable({
  providedIn: 'root'
})
export class ProfileServiceService {

  // constructor(private httpClient: HttpClient) {
  // }

  // getProfile(): Observable<any>{
  //   return this.httpClient.get('http://localhost:8080/user?username=user1')
  // }

  // updateProfile(profile: Profile): Observable<any>{
  //   return this.httpClient.put('http://localhost:8080/user?username=user1', profile)
  // }
}
