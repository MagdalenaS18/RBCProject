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
    return this.http.get<string>(`${this.url}/default-currency`);
  }

  setDefaultCurrency(currency: string): Observable<any> {
    // const settings: Settings = { id:1, defaultCurrency: currency }
    return this.http.patch<Settings>(`${this.url}/default-currency`, null, {
      params: { defaultCurrency: currency }
    });
  }

  fetchConversionRates(): Observable<any>{
    return this.http.get<any>(`${this.url}/default-currency/rates`);
  }

  getCurrencyNames(): Observable<string[]>{
    return this.http.get<string[]>(`${this.url}/currency-names`);
  }

  getConversionDate(): Observable<string>{
    return this.http.get<string>(`${this.url}/default-currency/date`);
  }
  
  
}
