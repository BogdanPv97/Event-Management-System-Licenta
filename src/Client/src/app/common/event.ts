import { City } from './city';
import { EventCategory } from './event-category';

export class Event {
  constructor(
    public eventId: number,
    public name: string,
    public location: string,
    public description: string,
    public imageUrl: string,
    public sizeLimit: number,
    public going: number,
    public interested: number,
    public price: number,
    public ageRestriction: boolean,
    public startDate: Date,
    public lastUpdate: Date,
    public city: City,
    public eventCategory: EventCategory
  ) {}
}
