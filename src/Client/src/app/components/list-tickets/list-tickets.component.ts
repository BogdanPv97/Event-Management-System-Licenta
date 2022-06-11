import { Component, OnInit } from '@angular/core';
import { Ticket, TicketInfo } from 'src/app/common/ticket';
import { TicketService } from 'src/app/service/ticket.service';

@Component({
  selector: 'app-list-tickets',
  templateUrl: './list-tickets.component.html',
  styleUrls: ['./list-tickets.component.css'],
})
export class ListTicketsComponent implements OnInit {
  tickets: TicketInfo[];

  constructor(private ticketService: TicketService) {}

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
}
