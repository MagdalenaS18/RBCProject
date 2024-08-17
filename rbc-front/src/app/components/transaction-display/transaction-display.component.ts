import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { Transaction } from '../../models/transaction';
import { TransactionService } from '../../services/transaction.service';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { CommonModule } from '@angular/common';
import { Account } from '../../models/account';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { AccountService } from '../../services/account.service';
import { Settings } from '../../models/settings';
import { SettingsService } from '../../services/settings.service';

@Component({
  selector: 'app-transaction-display',
  templateUrl: './transaction-display.component.html',
  styleUrls: ['./transaction-display.component.css'],
  standalone: true,
  imports: [CommonModule, FooterComponent, 
            MatCardModule, MatDividerModule, 
            MatFormFieldModule, MatSelectModule]
})
export class TransactionDisplayComponent implements OnInit {
  transactions: Transaction[] = [];
  // account!: Account;
  accounts: Account[] = [];
  defaultCurrency!: string;
  conversionRates: { [key: string]: number } = {};

  constructor(private transactionService: TransactionService, 
              private accountService: AccountService,
              private settingsService: SettingsService) { }

  ngOnInit(): void {
    this.getAllTransactions();  // BEZ OVOG NIJE RADILO!!
    this.loadAllAccounts();
    this.fetchDefaultCurrency();
  }

  fetchDefaultCurrency(): void {
    this.settingsService.getDefaultCurrency().subscribe(data => {
      this.defaultCurrency = data;
      this.fetchCurrencies();
    })
  }

  fetchCurrencies(): void {
    this.settingsService.fetchConversionRates().subscribe(data => {
      this.conversionRates = data;
      console.log('Conversion rates:', this.conversionRates);
    });
  }

  convertToDefaultCurrency(amount: number, currency: string): number {
    if(!this.conversionRates || !this.conversionRates[currency]){
      return amount;
    }
    console.log(`Converting amount: ${amount} from currency: ${currency}`);
    if (currency === this.defaultCurrency) {
      return amount;
    }
    const rate = this.conversionRates[currency];
    console.log('Amount:', amount, 'Currency:', currency, 'Rate:', this.conversionRates[currency]);

    return amount/rate;
  }

  getAllTransactions(): void {
    this.transactionService.getTransactions().subscribe((data: Transaction[]) => {
      this.transactions = data;
    })
  }

  loadAllAccounts(): void {
    this.accountService.getAccounts().subscribe((data) => {
      this.accounts = data;
    });
  }

  onSelectedAccount(account: number | null): void {
    if(account){
      this.transactionService.getTransactionsByAccountId(account).subscribe((data) => {
        this.transactions = data;
      });
    } else {
      this.getAllTransactions();
    }
  }

}
