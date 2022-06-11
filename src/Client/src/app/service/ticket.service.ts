import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiUrl } from '../common/api-url';
import { Ticket, TicketInfo } from '../common/ticket';

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  private apiKey = new ApiUrl();

  constructor(private http: HttpClient) {}

  getAllTicketsForUser(userId: number): Observable<TicketInfo[]> {
    return this.http.get<TicketInfo[]>(
      `http://localhost:9191/tickets/${userId}`
    );
  }
}
