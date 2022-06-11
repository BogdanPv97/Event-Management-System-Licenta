import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiUrl } from '../common/api-url';
import { City } from '../common/city';

@Injectable({
  providedIn: 'root',
})
export class CityService {
  private apiKey = new ApiUrl();

  constructor(private http: HttpClient) {}

  getAllCities(): Observable<City[]> {
    return this.http.get<City[]>(this.apiKey.getAllCities());
  }
}
