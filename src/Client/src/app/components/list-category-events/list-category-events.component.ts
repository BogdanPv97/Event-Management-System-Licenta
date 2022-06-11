import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Event } from 'src/app/common/event';
import { EventCategory } from 'src/app/common/event-category';
import { EventService } from 'src/app/service/event.service';

@Component({
  selector: 'app-list-category-events',
  templateUrl: './list-category-events.component.html',
  styleUrls: ['./list-category-events.component.css'],
})
export class ListCategoryEventsComponent implements OnInit {
  eventsFromCategory: Event[];
  categoryTitle: string;

  constructor(
    private eventService: EventService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.getEventsFromCategory();
    });
  }

  getEventsFromCategory() {
    const categoryId: number = +this.route.snapshot.paramMap.get('categoryId');

    this.eventService.getEventsByCategory(categoryId).subscribe((result) => {
      this.eventsFromCategory = result;
    });
  }

  // changeView(categoryId: number) {
  //   this.router.navigate([`/events/categories/${categoryId}`]);
  // }

  handleIncrementInterested() {}

  handleIncrementGoing() {}
}
