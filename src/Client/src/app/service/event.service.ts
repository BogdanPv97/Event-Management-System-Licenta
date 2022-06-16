import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, catchError } from 'rxjs';
import { Event } from '../common/event';
import { EventCategory } from '../common/event-category';
import { User } from '../common/user';
import { ApiUrl } from '../common/api-url';
import { EventDetails } from '../common/event-details';
import { EventSaveDTO } from '../common/event-save-dto';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  private apiKey = new ApiUrl();
  private response: string;

  private ticketToBeBought: EventDetails;

  constructor(private http: HttpClient) {}

  getEventCategories(): Observable<EventCategory[]> {
    return this.http.get<EventCategory[]>(this.apiKey.getAllCategories());
  }

  getEventsByCategory(categoryId: number): Observable<Event[]> {
    return this.http.get<Event[]>(this.apiKey.getEventsByCategory(categoryId));
  }

  getAllEventsDetails(): Observable<EventDetails[]> {
    return this.http.get<EventDetails[]>(this.apiKey.getAllEventsDetails());
  }

  getEventDetailsById(eventId: number): Observable<EventDetails> {
    return this.http.get<EventDetails>(
      this.apiKey.getEventDetailsById(eventId)
    );
  }

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.apiKey.getAllEvents());
  }

  getAllEventsFromCity(cityId: number): Observable<Event[]> {
    return this.http.get<Event[]>(this.apiKey.getAllEventsFromCity(cityId));
  }

  getTop20Events(): Observable<Event[]> {
    return this.http.get<Event[]>('http://localhost:9191/events/top20');
  }

  getEventById(eventId: number): Observable<Event> {
    return this.http.get<Event>(this.apiKey.getEventById(eventId));
  }

  getEventsFromCategory(categoryId: number): Observable<Event[]> {
    return this.http.get<Event[]>(this.apiKey.getEventsByCategory(categoryId));
  }

  getCategoryByEventId(eventId: number): Observable<EventCategory> {
    return this.http.get<EventCategory>(
      this.apiKey.getCategoryNameForEvent(eventId)
    );
  }

  deleteEventRequest(eventId: number) {
    return this.http.delete(this.apiKey.deleteEvent(eventId)).subscribe();
  }

  addEvent(event: EventSaveDTO): Observable<EventSaveDTO> {
    return this.http.post<EventSaveDTO>(this.apiKey.saveEvent(), event);
  }

  setTicketEvent(event: EventDetails){
    this.ticketToBeBought = event;
  }

  getEventForTicket(){
    return this.ticketToBeBought;
  }
}
