import { Component, EventEmitter, Inject, OnInit, Output, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient } from '@angular/common/http';
import { Account } from '../../models/account';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { AccountService } from '../../services/account.service';

@Component({
  selector: 'app-account-input',
  templateUrl: './account-input.component.html',
  styleUrls: ['./account-input.component.css'],
  standalone: true,
  imports: [MatInputModule, 
            MatFormFieldModule, 
            FormsModule, 
            MatIconModule, 
            MatButtonModule, 
            MatDialogModule]
})
export class AccountInputComponent implements OnInit {
  // newAccount!: Account;
  @ViewChild("addAccountForm") addAccountForm!: NgForm; // ovdje smjestimo sve podatke sto dobijemo preko forme

  @Output() newDataEvent = new EventEmitter();  // kad se kreira novi account da se prikaze na main strani

  constructor(private dialogRef: MatDialogRef<AccountInputComponent>) { }

  ngOnInit() {
    // this.newAccount = this.data;
  }

  

  // onAdd(){
  //   if(this.addAccountForm.valid){}
  // }

  onSubmit(): void {
    if(this.addAccountForm.valid){
      this.newDataEvent.emit(this.addAccountForm.value);  // account se kreira samo kad se klikne Create, a ne i kad kliknem Cancel
      this.dialogRef.close(this.addAccountForm.value);

      // this.accountService.addAccount(this.addAccountForm.value).subscribe(data => {
      // this.newDataEvent.emit(data);
      // this.dialogRef.close();
    }
  }
  
  onCancel(){
    this.dialogRef.close(null); // zatvori bez da se ista emituje
  }

  // addAccountSubmit(): void {
  //   this.accountService.addAccount().subscribe(data => this.newDataEvent.emit(data));
  // }
}
