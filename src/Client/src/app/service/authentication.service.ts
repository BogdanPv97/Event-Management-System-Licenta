import {
  HttpClient,
  HttpHeaderResponse,
  HttpHeaders,
  HttpResponse,
} from '@angular/common/http';
import { ThisReceiver } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { ApiUrl } from '../common/api-url';
import { User } from '../common/user';
import { UserCredentials } from '../common/user-credentials';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private apiKey = new ApiUrl();

  private token: string;

  constructor(private http: HttpClient) {}

  loginUser(credentials: UserCredentials): Observable<any> {
    return this.http.post<UserCredentials>(
      'http://localhost:9191/login',
      credentials
    );
  }

  loadToken(): void {
    //
    this.token = localStorage.getItem('jwtToken');
  }

  saveToken(token: string) {
    localStorage.setItem('jwtToken', token);
  }

  getToken() {
    return this.token;
  }

  addUserToLocalCache(user: any) {}
}
