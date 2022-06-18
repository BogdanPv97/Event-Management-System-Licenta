import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Ticket, TicketInfo } from 'src/app/common/ticket';
import { UserDetails } from 'src/app/common/user-details';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { SharedService } from 'src/app/service/shared.service';
import { TicketService } from 'src/app/service/ticket.service';

@Component({
  selector: 'app-list-tickets',
  templateUrl: './list-tickets.component.html',
  styleUrls: ['./list-tickets.component.css'],
})
export class ListTicketsComponent implements OnInit {
  tickets: TicketInfo[];
  loggedUser: UserDetails;

  constructor(
    private ticketService: TicketService,
    private route: Router,
    private cookie: CookieService,
    private auth: AuthenticationService,
    private shared: SharedService
  ) {}

  ngOnInit(): void {
    this.populateUser();
    setTimeout(() => this.getAllTicketsForUser(), 800);
  }

  populateUser() {
    this.auth
      .getLoggedInUser(this.cookie.get('username'))
      .subscribe((result) => {
        this.loggedUser = result;
      });
  }

  getAllTicketsForUser() {
    this.ticketService
      .getAllTicketsForUser(this.shared.loggedUser.userId)
      .subscribe((result) => {
        this.tickets = result;

        console.log(result);
      });
  }

  goToEvent(eventId: number) {
    this.route.navigateByUrl(`/events/${eventId}`);
  }
}
