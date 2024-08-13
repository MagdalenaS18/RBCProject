import { Component, Input, OnInit } from '@angular/core';
import { Account } from '../../models/account';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-account-display',
  templateUrl: './account-display.component.html',
  styleUrls: ['./account-display.component.css'],
  standalone: true,
  imports: [MatButtonModule, MatCardModule, MatDividerModule, MatIconModule]
})
export class AccountDisplayComponent implements OnInit {
  @Input() account: Account = new Account(0, "", "", 0);

  constructor() { }

  ngOnInit() {
  }

}
