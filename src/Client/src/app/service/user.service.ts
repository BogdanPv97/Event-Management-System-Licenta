import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiUrl } from '../common/api-url';
import { User } from '../common/user';
import { UserDetails } from '../common/user-details';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiKey = new ApiUrl();

  constructor(private http: HttpClient) {}

  getAllUsersDetails(): Observable<UserDetails[]> {
    return this.http.get<UserDetails[]>(this.apiKey.getAllUsersDetails());
  }

  registerUser(userToSave: User): Observable<User> {
    return this.http.post<User>(this.apiKey.registerUser(), userToSave);
  }
}
