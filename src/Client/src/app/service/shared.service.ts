import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { UserDetails } from '../common/user-details';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root',
})
export class SharedService {
  loggedUser: UserDetails;
  isLoggedIn: boolean = false;

  constructor(
    private auth: AuthenticationService,
    private cookie: CookieService
  ) {
    auth.getLoggedInUser(cookie.get('username')).subscribe((result) => {
      this.loggedUser = result;
    });
  }

  getLoggedUser() {
    return this.isLoggedIn;
  }

  setLoggeduser(state: boolean) {
    this.isLoggedIn = state;
  }
}
