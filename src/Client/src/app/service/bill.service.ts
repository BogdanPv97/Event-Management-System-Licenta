import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiUrl } from '../common/api-url';
import { Bill } from '../common/bill';

@Injectable({
  providedIn: 'root',
})
export class BillService {
  private apiKey = new ApiUrl();

  constructor(private http: HttpClient) {}

  getAllBillsForUser(userId: number): Observable<Bill[]> {
    return this.http.get<Bill[]>(this.apiKey.getAllBillsForUser(userId));
  }
}
