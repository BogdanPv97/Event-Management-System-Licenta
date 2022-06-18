import {
  HttpClient,
  HttpHeaderResponse,
  HttpHeaders,
  HttpResponse,
} from '@angular/common/http';
import { ThisReceiver } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable, tap } from 'rxjs';
import { ApiUrl } from '../common/api-url';
import { User } from '../common/user';
import { UserCredentials } from '../common/user-credentials';
import { UserDetails } from '../common/user-details';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private apiKey = new ApiUrl();

  loggedUser: UserDetails;

  private token: string;

  constructor(private http: HttpClient, private cookie: CookieService) {}

  loginUser(credentials: UserCredentials): Observable<HttpResponse<Object>> {
    console.log(credentials);
    return this.http.post<HttpResponse<Object>>(
      'http://localhost:9191/login',
      credentials,
      { observe: 'response' }
    );
  }

  getUsernameFromCookie() {
    return this.cookie.get('username');
  }

  getLoggedInUser(username: string): Observable<UserDetails> {
    return this.http.get<UserDetails>(this.apiKey.getLoggedUser(username));
  }

  loadToken(): void {
    //
    this.token = window.localStorage.getItem('jwtToken');
  }

  saveToken(token: string) {
    localStorage.setItem('jwtToken', token);
  }

  getToken() {
    return this.token;
  }

  addUserToLocalCache(user: any) {}
}
