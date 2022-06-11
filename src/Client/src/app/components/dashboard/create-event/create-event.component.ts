import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { City } from 'src/app/common/city';
import { EventCategory } from 'src/app/common/event-category';
import { EventSaveDTO } from 'src/app/common/event-save-dto';
import { CityService } from 'src/app/service/city.service';
import { EventService } from 'src/app/service/event.service';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css'],
})
export class CreateEventComponent implements OnInit {
  createEventFormGroup: FormGroup;
  // eventSave = new EventSaveDTO(
  //   'addTest',
  //   'locationTest',
  //   'descriptionTest',
  //   100,
  //   200,
  //   'asdasdad',
  //   true,
  //   new Date()
  // );

  categories: EventCategory[];
  cities: City[];

  constructor(
    private formBuilder: FormBuilder,
    private eventService: EventService,
    private cityService: CityService
  ) {}

  ngOnInit(): void {
    this.createEventFormGroup = this.formBuilder.group({
      event: this.formBuilder.group({
        name: [''],
        location: [''],
        eventDescription: [''],
        sizeLimit: [''],
        price: [''],
        imageUrl: [''],
        ageRestriction: [''],
        startDate: [''],
        category: [''],
        city: [''],
      }),
    });

    this.populateCategories();
    this.populateCities();
  }

  onSubmit() {
    console.log(this.createEventFormGroup.get('event'));

    let categoryId: number = this.getCategoryIdByName(
      this.createEventFormGroup.get('event').value.category
    );
    let cityId: number = this.getCategoryIdByName(
      this.createEventFormGroup.get('event').value.city
    );
    console.log('city Id:' + cityId);
    console.log('category ID: ' + categoryId);
    console.log(this.createEventFormGroup.get('event'));

    let eventSave = new EventSaveDTO(
      this.createEventFormGroup.get('event').value.name,
      this.createEventFormGroup.get('event').value.location,
      this.createEventFormGroup.get('event').value.eventDescription,
      this.createEventFormGroup.get('event').value.sizeLimit,
      this.createEventFormGroup.get('event').value.price,
      this.createEventFormGroup.get('event').value.imageUrl,
      this.createEventFormGroup.get('event').value.ageRestriction,
      new Date(),
      categoryId,
      1
    );
    this.saveEvent(eventSave);
  }

  saveEvent(event: EventSaveDTO) {
    this.eventService.addEvent(event).subscribe();
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

  getCityIdByName(cityName: string) {
    let cityId: number;

    for (let city of this.cities) {
      if (city.name == cityName) {
        return city.cityId;
      }
    }

    return cityId;
  }

  getCategoryIdByName(categoryName: string) {
    let categoryId: number;

    for (let category of this.categories) {
      if (category.categoryName == categoryName) {
        categoryId = category.eventCategoryId;
      }
    }

    return categoryId;
  }
}
