export class Ticket {
  constructor(
    public ticketId: number,
    public eventId: number,
    public userId: number,
    public billId: number,
    public eventName: string,
    public eventStartDate: Date,
    public price: number
  ) {}
}

export interface TicketInfo {
  ticketId: number;
  userId: number;
  eventId: number;
  billId: number;
  eventName: string;
  eventStartDate: Date;
  price: number;
}
