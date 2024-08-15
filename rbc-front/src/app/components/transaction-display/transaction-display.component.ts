import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { Transaction } from '../../models/transaction';
import { TransactionService } from '../../services/transaction.service';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { CommonModule } from '@angular/common';
import { Account } from '../../models/account';

@Component({
  selector: 'app-transaction-display',
  templateUrl: './transaction-display.component.html',
  styleUrls: ['./transaction-display.component.css'],
  standalone: true,
  imports: [CommonModule, FooterComponent, 
            MatCardModule, MatDividerModule, ]
})
export class TransactionDisplayComponent implements OnInit {
  transactions: Transaction[] = [];
  account!: Account;

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.getAllTransactions();  // BEZ OVOG NIJE RADILO!!
  }

  getAllTransactions(): void {
    this.transactionService.getTransactions().subscribe((data: Transaction[]) => {
      this.transactions = data;
    })
  }

}
