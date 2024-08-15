import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Account } from './models/account';
import { AccountService } from './services/account.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title='MyBudget';

  constructor(private router: Router){
    // this.router.navigate(['/api/accounts']);
  }
  
  // navigateTo(route: string): void {
  //   this.router.navigate(['/${route}']);
  // }
}

// accounts: Account[] = [];

  // // constructor(private http: HttpClient){}

  // constructor(private accountService: AccountService){
  //   this.loadAccounts();
  // }

  // loadAccounts(){
  //   this.accountService.getAccounts().subscribe((data:Account[]) => {
  //     this.accounts = data;
  //   })
  // }

  // ngOnInit(): void{
  //   // this.http.get<Account[]>(
  //   //   "http://localhost:8080/api/accounts"
  //   // ).subscribe(data => this.accounts = data);  // response cuvam u accounts field
  // }

  // appendNewAccount(newAccount: any): void{
  //   this.accounts.push(newAccount);
  // }