import { Component, EventEmitter, Inject, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, NgForm, Validators } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient } from '@angular/common/http';
import { Account } from '../../models/account';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { AccountService } from '../../services/account.service';
import { SettingsService } from '../../services/settings.service';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-account-input',
  templateUrl: './account-input.component.html',
  styleUrls: ['./account-input.component.css'],
  standalone: true,
  imports: [MatInputModule, MatFormFieldModule, 
            FormsModule, MatIconModule, 
            MatButtonModule, MatDialogModule,
            MatSelectModule, CommonModule
  ]
})
export class AccountInputComponent implements OnInit {
  @ViewChild("addAccountForm") addAccountForm!: NgForm; // ovdje smjestimo sve podatke sto dobijemo preko forme
  @Output() newDataEvent = new EventEmitter();  // kad se kreira novi account da se prikaze na main strani
  // takodje da obavijesti roditelja komponente
  account: Account = {
    name: '',
    currency: '',
    balance: 0
  };
  accounts!: Account[];
  defaultCurrency!: string;
  currencies: string[] = [];
  
  constructor(private accountService: AccountService,
              private settingsService: SettingsService, 
              private dialogRef: MatDialogRef<AccountInputComponent>) { }

  ngOnInit() {
    this.getDefaultCurrency();
  }

  getAccounts(): void {
    this.accountService.getAccounts().subscribe(data => {
      this.accounts = data;
    });
  }

  getDefaultCurrency(): void {
    this.settingsService.getDefaultCurrency().subscribe(data => {
      this.defaultCurrency = data;
      this.fetchCurrencies();
    })
  }

  fetchCurrencies(): void {
    this.settingsService.fetchConversionRates().subscribe(data => {
      this.currencies = Object.keys(data);
    })
  }

  onSubmit(): void {
    if(this.addAccountForm.valid){
      const selectedCurrency = this.currencies.find(currency => this.account.currency === currency);
      this.account.currency = selectedCurrency as string;

      this.accountService.addAccount(this.account).subscribe((newAccount: Account) => {
        this.newDataEvent.emit(newAccount);  // account se kreira samo kad se klikne Create, a ne i kad kliknem Cancel
        console.log(this.addAccountForm.value);
        this.dialogRef.close(newAccount);
      });
      
    }
  }
  
  onCancel(){
    this.dialogRef.close(null); // zatvori bez da se ista emituje
  }

}
