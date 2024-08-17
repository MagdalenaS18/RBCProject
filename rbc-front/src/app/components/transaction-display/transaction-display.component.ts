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
  settings!: Settings;

  constructor(private transactionService: TransactionService, 
              private accountService: AccountService) { }

  ngOnInit(): void {
    this.getAllTransactions();  // BEZ OVOG NIJE RADILO!!
    this.loadAllAccounts();
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
