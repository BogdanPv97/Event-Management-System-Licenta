import { Event } from "./event";

export class EventCategory {

    constructor(public eventCategoryId:number,
                public categoryName: string,
                public events: Event[]
                ){}
}
