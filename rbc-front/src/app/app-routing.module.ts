import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountDisplayComponent } from './components/account-display/account-display.component';
import { TransactionDisplayComponent } from './components/transaction-display/transaction-display.component';
import { SettingsComponent } from './components/settings/settings.component';

const routes: Routes = [
  { path: 'api/accounts', component: AccountDisplayComponent, pathMatch: 'full' },
  { path: 'api/transactions', component: TransactionDisplayComponent, pathMatch: 'full' },
  { path: 'api/settings', component: SettingsComponent, pathMatch: 'full' },
  { path: '', redirectTo: 'api/accounts', pathMatch: 'full' }
  // { path: '**', }  IMPLEMENTIRATI NOT FOUND
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
