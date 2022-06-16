import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket, TicketInfo } from 'src/app/common/ticket';
import { TicketService } from 'src/app/service/ticket.service';

@Component({
  selector: 'app-list-tickets',
  templateUrl: './list-tickets.component.html',
  styleUrls: ['./list-tickets.component.css'],
})
export class ListTicketsComponent implements OnInit {
  tickets: TicketInfo[];

  constructor(private ticketService: TicketService, private route: Router) {}

  ngOnInit(): void {
    this.getAllTicketsForUser();
  }

  getAllTicketsForUser() {
    const userId: number = +39;

    this.ticketService.getAllTicketsForUser(userId).subscribe((result) => {
      this.tickets = result;

      console.log(result);
    });
  }

  goToEvent(eventId: number) {
    this.route.navigateByUrl(`/events/${eventId}`);
  }
}
