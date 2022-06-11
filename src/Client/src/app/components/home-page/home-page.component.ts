import { Component, OnInit } from '@angular/core';
import { EventService } from 'src/app/service/event.service';
import { Event } from 'src/app/common/event';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  topEvents: Event[];

  constructor(private eventService: EventService) {}

  ngOnInit(): void {
    this.getTop20Events();
  }

  getTop20Events() {
    this.eventService.getTop20Events().subscribe((response) => {
      this.topEvents = response;
    });
  }
}
