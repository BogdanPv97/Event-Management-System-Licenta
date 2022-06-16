import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiUrl } from '../common/api-url';
import { PaymentInfo } from '../common/payment-info';

@Injectable({
  providedIn: 'root',
})
export class CheckoutService {
  private apiKey = new ApiUrl();

  constructor(private http: HttpClient) {}

  createPaymentIntent(paymentInfo: PaymentInfo): Observable<any> {
    return this.http.post<PaymentInfo>(
      this.apiKey.createPaymentIntent(),
      paymentInfo
    );
  }

  placeOrder(eventId: number, userId: number): Observable<any> {
    return this.http.post<any>(this.apiKey.placeOrder(eventId, userId), {
      eventId: eventId,
      userId: userId,
    });
  }
}
