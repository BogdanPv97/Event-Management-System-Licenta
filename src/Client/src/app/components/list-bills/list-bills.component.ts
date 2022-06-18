import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Bill } from 'src/app/common/bill';
import { UserDetails } from 'src/app/common/user-details';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { BillService } from 'src/app/service/bill.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-list-bills',
  templateUrl: './list-bills.component.html',
  styleUrls: ['./list-bills.component.css'],
})
export class ListBillsComponent implements OnInit {
  bills: Bill[];
  loggedUser: UserDetails;

  constructor(
    private billService: BillService,
    private auth: AuthenticationService,
    private cookie: CookieService
  ) {}

  ngOnInit(): void {
    this.populateUser();

    setTimeout(() => this.getAllBillsForUser(), 500);
  }

  populateUser() {
    this.auth
      .getLoggedInUser(this.cookie.get('username'))
      .subscribe((result) => {
        this.loggedUser = result;
      });
  }

  getAllBillsForUser() {
    const userId = this.loggedUser.userId;
    console.log('aasda' + userId);
    this.billService.getAllBillsForUser(userId).subscribe((result) => {
      this.bills = result;
    });
  }
}
