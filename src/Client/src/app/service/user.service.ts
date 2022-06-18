import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { ApiUrl } from '../common/api-url';
import { User } from '../common/user';
import { UserDetails } from '../common/user-details';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiKey = new ApiUrl();

  loggedUser: UserDetails;

  constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService,
    private cookie: CookieService
  ) {}

  getAllUsersDetails(): Observable<UserDetails[]> {
    return this.http.get<UserDetails[]>(this.apiKey.getAllUsersDetails());
  }

  registerUser(userToSave: User): Observable<User> {
    return this.http.post<User>(this.apiKey.registerUser(), userToSave);
  }

  getLoggedUser() {
    return this.cookie.get('username');
  }
}
