import { HttpClient } from '@angular/common/http';
import { Injectable, ViewChild } from '@angular/core';
import { Account } from '../models/account';
import { Observable } from 'rxjs';

// export interface Account{
//   id: number;
//   name: string;
//   currency: string;
//   balance: number;
// }

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private url = 'http://localhost:8080/api/accounts';

  // newAccount!:Account;
  constructor(private http: HttpClient) { }

  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(this.url);
  }
  
  addAccount(newAccount: Account): Observable<Account> {
    return this.http.post<Account>(this.url, newAccount);
  }
}
