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
    SettingsComponent,
    NavbarComponent,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatToolbarModule
],
  providers: [AccountService],
  bootstrap: [AppComponent]
})
export class AppModule { }
