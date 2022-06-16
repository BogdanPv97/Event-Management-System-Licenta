import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventCategory } from 'src/app/common/event-category';
import { EventDetails } from 'src/app/common/event-details';
import { EventService } from 'src/app/service/event.service';

@Component({
  selector: 'app-master-event',
  templateUrl: './master-event.component.html',
  styleUrls: ['./master-event.component.css'],
})
export class MasterEventComponent implements OnInit {
  // title: string;
  // location: string;
  // startDate: Date;
  // price: number;
  // going: number;
  // interested: number;
  // imageUrl: string;
  // description: string;
  // category: string;
  // city: string;
  eventDetails: EventDetails;

  constructor(
    private eventService: EventService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.populateEventData();
    });
  }

  populateEventData() {
    const eventId: number = +this.route.snapshot.paramMap.get('eventId');
    console.log(eventId);

    this.eventService.getEventDetailsById(eventId).subscribe((result) => {
      this.eventDetails = result;
      console.log(result);
    });
    console.log(this.eventDetails);
  }

  setAmount() {
    this.eventService.setTicketEvent(this.eventDetails);
  }
}
