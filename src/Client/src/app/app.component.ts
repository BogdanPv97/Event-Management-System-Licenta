import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserDetails } from './common/user-details';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { SharedService } from './service/shared.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'angular-management-system';
  loggedUser: boolean = false;

  constructor(
    private router: Router,
    private cookie: CookieService,
    private shared: SharedService
  ) {
    console.log('sadasdasdasdasd ' + cookie.get('username'));
    // if (cookie.get('username') !== null) {
    //   this.loggedUser = true;
    // }

    if (shared.isLoggedIn) {
      this.loggedUser = true;
    }
  }

  searchEvent(input: string) {
    this.router.navigateByUrl(`/events/search/${input}`);
  }
}
