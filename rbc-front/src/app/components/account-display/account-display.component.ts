import { Component, Input, OnInit } from '@angular/core';
import { Account } from '../../models/account';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { AccountService } from '../../services/account.service';
import { CommonModule } from '@angular/common';
import {MatListModule} from '@angular/material/list';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AccountInputComponent } from '../account-input/account-input.component';

@Component({
  selector: 'app-account-display',
  templateUrl: './account-display.component.html',
  styleUrls: ['./account-display.component.css'],
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatCardModule, MatDividerModule, MatIconModule, AccountInputComponent, MatDialogModule]
})
export class AccountDisplayComponent implements OnInit {
  accounts: Account[] = [];
  //@Input() account: Account = new Account(0, "", "", 0);

  constructor(private accountService: AccountService, private dialog: MatDialog) { }

  ngOnInit(): void{
    // this.accountService.getAccounts().subscribe((data) => {
    //   this.accounts = data
    // });  // response cuvam u accounts field

    this.getAllAccounts();
  }

  getAllAccounts(): void {
    this.accountService.getAccounts().subscribe((data: Account[]) => {
      this.accounts = data;
    });
  }

  openAddAccountDialog(): void {
    const dialogRef = this.dialog.open(AccountInputComponent, {
      width: '400px',
      // height: '300px',
      enterAnimationDuration: '250ms',
      exitAnimationDuration: '200ms'
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.accountService.addAccount(result).subscribe(() => {
          this.getAllAccounts();
        });
      }
      // this.getAllAccounts();
    });
  }

}
