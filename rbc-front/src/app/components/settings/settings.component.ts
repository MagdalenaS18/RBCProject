import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { SettingsService } from '../../services/settings.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { Settings } from '../../models/settings';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
  standalone: true,
  imports: [MatCardModule, FormsModule, 
            MatFormFieldModule, MatInputModule, 
            MatButtonModule, MatDividerModule,
            MatSelectModule, CommonModule]
})
export class SettingsComponent implements OnInit {
  currencyType: string = '';
  date: string = '';
  settings!: Settings;

  currencies: string[] = [];
  selectedCurrency: string = '';

  @ViewChild('addCurrencyForm') addCurrencyForm!: NgForm;
  @Output() newDataEmit = new EventEmitter();

  constructor(private settingsService: SettingsService) { }

  ngOnInit() {
    this.getSettings();
    this.getDefaultCurrency();
    this.getConversionDate();
    this.fetchCurrencies();
  }

  // this.currencyService.getCurrencies().subscribe(data => {
  //   this.currencies = Object.keys(data.eur); // Adjust based on the base currency from the backend
  // });

  getSettings(): void {
    this.settingsService.getSettingsInfo().subscribe((data) => {
      this.settings = data;
    })
  }

  getDefaultCurrency(): void {
    this.settingsService.getDefaultCurrency().subscribe(currency => {
      this.currencyType = currency;
    });
  }

  getConversionDate(): void {
    this.settingsService.getConversionDate().subscribe((data:any) => {
      this.date = data;
    });
  }

  fetchCurrencies(): void {
    this.settingsService.fetchConversionRates().subscribe((data: any) => {
      this.currencies = Object.keys(data);
    })
  }

  onDefaultCurrencyChange(selectedCurrency: string): void {
    this.settingsService.setDefaultCurrency(selectedCurrency).subscribe(response => {
      this.currencyType = selectedCurrency;
    });

    // if(this.selectedCurrency){
    //   this.settingsService.setDefaultCurrency(this.selectedCurrency).subscribe(response => {
    //     this.currencyType = this.selectedCurrency;
    //   })
    // }
  }

  onDeleteData(): void {
    
  }



  // onSubmit(addCurrencyForm: NgForm): void {
  //   const defaultCurrency = addCurrencyForm.value.defaultCurrency;
  //   if(addCurrencyForm.valid){
  //     this.settingsService.setDefaultCurrency(defaultCurrency).subscribe({
  //       next: (response) => {
  //         console.log('Default currency set:', response);
  //       },
  //       error: (error) => {
  //         console.error('Error setting default currency:', error);
  //       }
  //     });
  //   }
  // }

}
