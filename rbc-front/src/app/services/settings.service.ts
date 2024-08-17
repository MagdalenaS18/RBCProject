import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Settings } from '../models/settings';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {
  private url = 'http://localhost:8080/api/settings';

  constructor(private http: HttpClient) { }

  getSettingsInfo(): Observable<Settings>{
    return this.http.get<Settings>(`${this.url}`);
  }

  getDefaultCurrency(): Observable<string>{
    return this.http.get(`${this.url}/default-currency`, { responseType: 'text' });  // kad stavim get<string> Angular misli da treba dobiti JSON
    // a dobije obican tekst
  }

  setDefaultCurrency(currency: string): Observable<any> {
    return this.http.patch<Settings>(`${this.url}/default-currency`, null, {
      params: { defaultCurrency: currency }
    });
  }

  fetchConversionRates(): Observable<{ [key: string]: number }>{
    return this.http.get< { [key: string]: number} >(`${this.url}/default-currency/rates`);
  }

  getCurrencyNames(): Observable<string[]>{
    return this.http.get<string[]>(`${this.url}/currency-names`);
  }

  getConversionDate(): Observable<string>{
    return this.http.get(`${this.url}/default-currency/date`, { responseType: 'text' });
  }
  
  
}
