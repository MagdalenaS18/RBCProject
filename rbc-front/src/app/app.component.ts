import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Account } from './models/account';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  accounts: Account[] = [];

  constructor(private http: HttpClient){}

  ngOnInit(): void{
    this.http.get<Account[]>(
      "http://localhost:8080/api/accounts"
    ).subscribe(data => this.accounts = data);  // response cuvam u accounts field
  }

  appendNewAccount(newAccount: any): void{
    this.accounts.push(newAccount);
  }
}
