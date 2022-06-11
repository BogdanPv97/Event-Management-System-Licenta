import { Component, OnInit } from '@angular/core';
import { EventCategory } from 'src/app/common/event-category';
import { EventService } from 'src/app/service/event.service';

@Component({
  selector: 'app-list-categories',
  templateUrl: './list-categories.component.html',
  styleUrls: ['./list-categories.component.css']
})
export class ListCategoriesComponent implements OnInit {

  categories: EventCategory[];

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.listCategories();
  }

  listCategories(){
    this.eventService.getEventCategories().subscribe( (result) => {
      console.log(result);
      this.categories = result;
    })
  }

}
