import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { SharedService } from 'src/app/service/shared.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  loggedUser: boolean = false;

  constructor(
    private router: Router,
    private sharedService: SharedService,
    private cookie: CookieService
  ) {}

  ngOnInit(): void {
    if (this.sharedService.getLoggedUser) {
      this.loggedUser = true;
    } else {
      this.loggedUser = false;
    }
    this.print();
  }

  print() {
    console.log('logged:' + this.loggedUser);
  }

  searchEvent(input: string) {
    this.router.navigateByUrl(`/events/search/${input}`);
  }
}
