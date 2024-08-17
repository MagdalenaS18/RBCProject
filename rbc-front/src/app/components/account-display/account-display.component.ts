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
import { FooterComponent } from '../footer/footer.component';
import { NavbarComponent } from "../navbar/navbar.component";
import { SettingsService } from '../../services/settings.service';

@Component({
  selector: 'app-account-display',
  templateUrl: './account-display.component.html',
  styleUrls: ['./account-display.component.css'],
  standalone: true,
  imports: [CommonModule, MatButtonModule,
    MatCardModule, MatDividerModule,
    MatIconModule, AccountInputComponent,
    MatDialogModule, FooterComponent]
})
export class AccountDisplayComponent implements OnInit {
  accounts: Account[] = [];
  defaultCurrency!: string;
  conversionRates: { [ key: string ]: number } = {};
  //@Input() account: Account = new Account(0, "", "", 0);

  constructor(private accountService: AccountService,
              private settingsService: SettingsService,
              private dialog: MatDialog) { }

  ngOnInit(): void{
    this.getAllAccounts();
    this.fetchDefaultCurrency();
  }

  getAllAccounts(): void {
    this.accountService.getAccounts().subscribe((data: Account[]) => {
      this.accounts = data;
    });
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
    })
  }

  convertToDefaultCurrency(amount: number, currency: string): number {
    if(!this.conversionRates || !this.conversionRates[currency]){
      return amount;
    }

    if(currency === this.defaultCurrency){
      return amount;
    }

    const rate = this.conversionRates[currency];

    return amount/rate;
  }

  openAddAccountDialog(): void {
    const dialogRef = this.dialog.open(AccountInputComponent, {
      width: '400px',
      enterAnimationDuration: '250ms',
      exitAnimationDuration: '200ms'
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.accountService.addAccount(result).subscribe(() => {
          this.getAllAccounts();
        });
      }
    });
  }

}
