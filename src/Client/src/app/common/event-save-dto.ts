export class EventSaveDTO {
  constructor(
    public name: string,
    public location: string,
    public description: string,
    public sizeLimit: number,
    public price: number,
    public imageUrl: string,
    public ageRestriction: boolean,
    public startDate: Date,
    public categoryId: number,
    public cityId: number
  ) {}
}
