import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AccountDisplayComponent } from './components/account-display/account-display.component';
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import { AccountInputComponent } from './components/account-input/account-input.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import { CommonModule } from '@angular/common';
import { AccountService } from './services/account.service';
import { MatCardModule } from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatDialogModule} from '@angular/material/dialog';
import {MatToolbarModule} from '@angular/material/toolbar';
import { TransactionDisplayComponent } from './components/transaction-display/transaction-display.component';
import { SettingsComponent } from './components/settings/settings.component';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { TransactionService } from './services/transaction.service';
import { TransactionInputComponent } from './components/transaction-input/transaction-input.component';
import { FooterComponent } from './components/footer/footer.component';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { SettingsService } from './services/settings.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    RouterOutlet,
    RouterLink,
    CommonModule,
    HttpClientModule,
    AccountDisplayComponent,
    AccountInputComponent,
    TransactionDisplayComponent,
    TransactionInputComponent,
    SettingsComponent,
    NavbarComponent,
    FooterComponent,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatToolbarModule,
    // MatSelectModule,
    // MatInputModule
],
  providers: [AccountService, TransactionService, SettingsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
