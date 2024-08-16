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


  constructor(private accountService: AccountService,
              private transactionService: TransactionService,
              private settingsService: SettingsService,
              private dialog: MatDialog) { }

  ngOnInit() {
    // this.getAvailableAmount();
  }

  loadAccounts(): void {
    this.accountService.getAccounts().subscribe((accounts) => {
      this.accounts = accounts;
    })
  }

  getAllTransactions(): void {
    this.transactionService.getTransactions().subscribe((data: Transaction[]) => {
      this.transactions = data;
    })
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
