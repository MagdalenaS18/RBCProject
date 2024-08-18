import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Transaction } from '../../models/transaction';
import { MatIconModule } from '@angular/material/icon';
import { Account } from '../../models/account';
import { CommonModule } from '@angular/common';
import { TransactionService } from '../../services/transaction.service';
import { AccountService } from '../../services/account.service';
import { SettingsService } from '../../services/settings.service';

@Component({
  selector: 'app-transaction-input',
  templateUrl: './transaction-input.component.html',
  styleUrls: ['./transaction-input.component.css'],
  standalone: true,
  imports: [CommonModule, MatInputModule,
            FormsModule, MatFormFieldModule,
            MatDialogModule, MatSelectModule,
            MatIconModule, MatButtonModule
  ]
})
export class TransactionInputComponent implements OnInit {
  @ViewChild("addTransactionForm") addTransactionForm!: NgForm;
  @Output() newDataEvent = new EventEmitter();
  accounts: Account[] = [];
  transaction: any = {
    description: '',
    type: 'EXPENSE',
    amount: 0,
    currency: '',
    account: null
  };  // da mogu koristiti ngModel jer to i jest 2-way-binding
  defaultCurrency!: string;
  currencies!: string[];
  transactions!: Transaction[];

  constructor(private transactionService: TransactionService, 
              private accountService: AccountService, 
              private settingsService: SettingsService,
              private dialogRef: MatDialogRef<TransactionInputComponent>) { }

  ngOnInit() {
    this.getAccounts();
    this.fetchDefaultCurrency();
  }

  getTransactions(): void {
    this.transactionService.getTransactions().subscribe(data => {
      this.transactions = data;
    })
  }

  getAccounts(): void {
    this.accountService.getAccounts().subscribe((data: Account[]) => {
      this.accounts = data;
    });
  }

  fetchDefaultCurrency(): void {
    this.settingsService.getDefaultCurrency().subscribe((data: string) => {
      this.defaultCurrency = data;
      this.fetchCurrencyRates();
    });
  }

  fetchCurrencyRates(): void {
    this.settingsService.fetchConversionRates().subscribe(data => {
      this.currencies = Object.keys(data);
    });
  }

  onSubmit(): void {
    if (this.addTransactionForm.valid && (this.transaction.amount > 1)) {
      // this.newDataEvent.emit(this.addTransactionForm.value);
      // this.dialogRef.close();

      const selectedAccount = this.accounts.find(account => account.id === +this.transaction.account);

      const transactionData = {
        ...this.transaction,
        account: selectedAccount
      };

      this.transactionService.addTransaction(transactionData).subscribe(() => {
        this.newDataEvent.emit(this.addTransactionForm.value); // Emit event to notify parent component
        this.dialogRef.close();
      });
    }
    // this.getTransactions();
  }

  onCancel(): void {
    this.dialogRef.close(null);
  }

  setAccounts(accounts: Account[]): void {
    this.accounts = accounts;
  }

}
