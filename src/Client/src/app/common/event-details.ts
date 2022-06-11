export class EventDetails {
  constructor(public eventId: number,
              public name: string,
              public location: string,
              public description: string,
              public imageUrl: string,
              public sizeLimit: number,
              public price: number,
              public ageRestriction: boolean,
              public going: number,
              public interested: number,
              public startDate: Date,
              public lastUpdate: Date,
              public city: string,
              public category: string
    ) {}
}
