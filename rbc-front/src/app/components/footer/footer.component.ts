import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatToolbar } from '@angular/material/toolbar';
import { TransactionService } from '../../services/transaction.service';
import { TransactionInputComponent } from '../transaction-input/transaction-input.component';
import { Transaction } from '../../models/transaction';
import { AccountService } from '../../services/account.service';
import { Account } from '../../models/account';
import { CommonModule } from '@angular/common';
import { SettingsService } from '../../services/settings.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css'],
  standalone: true,
  imports: [MatToolbar, MatDialogModule, 
            MatButtonModule, TransactionInputComponent,
            CommonModule]
})
export class FooterComponent implements OnInit {
  accounts: Account[] = [];
  transactions: Transaction[] = [];
  availableAmount: number = 0;
  defaultCurrency!: string;
  conversionRates: { [ key: string]: number } = {};


  constructor(private accountService: AccountService,
              private transactionService: TransactionService,
              private settingsService: SettingsService,
              private dialog: MatDialog) { }

  ngOnInit() {
    // this.getAvailableAmount();
    this.loadAccounts();
    this.getDefaultCurrency();
  }

  loadAccounts(): void {
    this.accountService.getAccounts().subscribe((data: Account[]) => {
      this.accounts = data;
      this.calculateAvailableAmount();
    })
  }

  getAllTransactions(): void {
    this.transactionService.getTransactions().subscribe((data: Transaction[]) => {
      this.transactions = data;
    })
  }

  getDefaultCurrency(): void {
    this.settingsService.getDefaultCurrency().subscribe(data => {
      this.defaultCurrency = data;
      this.fetchCurrencyRates();
    });
  }

  fetchCurrencyRates(): void {
    this.settingsService.fetchConversionRates().subscribe(data => {
      this.conversionRates = data;
    })
  }

  calculateAvailableAmount(): void {
    this.availableAmount = this.accounts.reduce((sum, account) => sum + account.balance, 0);
    // moram uvesti logiku za konvertovanje na default valutu pa onda da sumira sve
  }
  
  openNewTransactionDialog(){
    const dialogRef = this.dialog.open(TransactionInputComponent, {
      width: '400px',
      enterAnimationDuration: '250ms',
      exitAnimationDuration: '200ms'
    });
    const selectAccountDialog = dialogRef.componentInstance;
    selectAccountDialog.setAccounts(this.accounts);

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.transactionService.addTransaction(result).subscribe(() => {
          this.getAllTransactions();
        });
      }
    });
  }

}
