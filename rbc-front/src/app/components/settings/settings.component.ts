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

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
  standalone: true,
  imports: [MatFormFieldModule, FormsModule, MatInputModule, MatButtonModule, MatDividerModule]
})
export class SettingsComponent implements OnInit {
  currencyType: string = '';
  settings!: Settings;

  @ViewChild('addCurrencyForm') addCurrencyForm!: NgForm;
  @Output() newDataEmit = new EventEmitter();

  constructor(private settingsService: SettingsService) { }

  ngOnInit() {
    this.getSettings();
    this.settingsService.getDefaultCurrency().subscribe(currency => {
      this.currencyType = currency;
    })
  }

  getSettings(): void {
    this.settingsService.getSettingsInfo().subscribe((data) => {
      this.settings = data;
    })
  }

  // onSelectedCurrency(obj: string) {
  //   this.settingsService.setDefaultCurrency(obj);
  // }

  onSubmit(form: NgForm): void {
    const defaultCurrency = form.value.defaultCurrency;
    if(form.valid){
      this.settingsService.setDefaultCurrency(defaultCurrency).subscribe({
        next: (response) => {
          console.log('Default currency set:', response);
        },
        error: (error) => {
          console.error('Error setting default currency:', error);
        }
      });
    }

    // this.settingsService.setDefaultCurrency(this.setCurrency.value);
    // .subscribe(data => {
      // this.newDataEmit.emit(this.setCurrency.value);
    // })
  }

}
