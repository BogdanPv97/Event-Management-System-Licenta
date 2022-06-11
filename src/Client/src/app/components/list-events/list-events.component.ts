import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { City } from 'src/app/common/city';
import { Event } from 'src/app/common/event';
import { EventCategory } from 'src/app/common/event-category';
import { EventDetails } from 'src/app/common/event-details';
import { CityService } from 'src/app/service/city.service';
import { EventService } from 'src/app/service/event.service';
import { ListCategoryEventsComponent } from '../list-category-events/list-category-events.component';

@Component({
  selector: 'app-list-events',
  templateUrl: './list-events.component.html',
  styleUrls: ['./list-events.component.css'],
})
export class ListEventsComponent implements OnInit {
  events: EventDetails[];
  cities: City[];
  categories: EventCategory[];
  searchMode: boolean = false;

  selectedCity: string;
  selectedCategory: string;

  constructor(
    private eventService: EventService,
    private route: ActivatedRoute,
    private cityService: CityService
  ) {}

  ngOnInit(): void {
    this.populateCities();
    this.populateCategories();

    this.route.paramMap.subscribe(() => {
      this.handleListEvents();
    });
  }

  handleListEvents() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if (this.searchMode) {
      this.handleSearchEvents();
    } else {
      this.listAllEvents();
    }
  }

  handleSearchEvents() {
    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');

    this.eventService.getAllEventsDetails().subscribe((result) => {
      this.events = result.filter((event) => event.name.includes(theKeyword));
    });
  }

  listAllEvents() {
    this.eventService.getAllEventsDetails().subscribe((data) => {
      this.events = data;
    });
  }

  populateCities() {
    this.cityService.getAllCities().subscribe((result) => {
      this.cities = result;
    });
  }

  populateCategories() {
    this.eventService.getEventCategories().subscribe((result) => {
      this.categories = result;
    });
  }

  handleCityChange() {
    console.log(this.events);

    const cityId = this.getCityIdByName(this.selectedCity);

    this.eventService.getAllEventsDetails().subscribe((result) => {
      this.events = result.filter((event) => event.city === this.selectedCity);
    });
  }

  handleCategoryChange() {
    console.log(this.events);

    this.eventService.getAllEventsDetails().subscribe((result) => {
      this.events = result.filter(
        (event) => event.category === this.selectedCategory
      );
    });
  }

  getCityIdByName(cityName: string) {
    let cityId: number;

    for (let city of this.cities) {
      if (city.name == cityName) {
        return city.cityId;
      }
    }

    return cityId;
  }
}
