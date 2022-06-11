import { Component, OnInit } from '@angular/core';
import { Event } from 'src/app/common/event';
import { EventCategory } from 'src/app/common/event-category';
import { EventDetails } from 'src/app/common/event-details';
import { UserDetails } from 'src/app/common/user-details';
import { EventService } from 'src/app/service/event.service';
import { UserService } from 'src/app/service/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  allEvents: EventDetails[];
  usersDetails: UserDetails[];

  selectedEvent: number;

  constructor(
    private eventService: EventService,
    private userService: UserService,
    private modalService: NgbModal,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllEvents();
    this.getUsersDetails();
  }

  getAllEvents() {
    this.eventService.getAllEventsDetails().subscribe((result) => {
      this.allEvents = result;
    });
  }

  getUsersDetails() {
    this.userService.getAllUsersDetails().subscribe((result) => {
      this.usersDetails = result;
    });
  }

  deleteEvent() {
    this.eventService.deleteEventRequest(this.selectedEvent);
    this.selectedEvent = null;
    this.getAllEvents();

    this.router.navigate(['/admin/dashboard']).then(() => {
      window.location.reload();
    });
  }

  openVerticallyCentered(content: any, eventId: number) {
    this.selectedEvent = eventId;
    this.modalService.open(content, { centered: true });
  }
}
