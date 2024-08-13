import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient } from '@angular/common/http';
import { Account } from '../../models/account';

@Component({
  selector: 'app-account-input',
  templateUrl: './account-input.component.html',
  styleUrls: ['./account-input.component.css'],
  standalone: true,
  imports: [MatInputModule, MatFormFieldModule, FormsModule, MatIconModule, MatDividerModule, MatButtonModule]
})
export class AccountInputComponent implements OnInit {
  @ViewChild("addAccountForm") addAccountForm!: NgForm; // ovdje smjestimo sve podatke sto dobijemo preko forme

  @Output() newDataEvent = new EventEmitter();  // kad se kreira novi account da se prikaze na main strani

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  addAccountSubmit(): void {
    this.http.post<Account>(
      "http://localhost:8080/api/accounts",
      this.addAccountForm.value
    ).subscribe(data => this.newDataEvent.emit(data));
  }
}
