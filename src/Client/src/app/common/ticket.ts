export class Ticket {
  constructor(
    public ticketId: number,
    public eventId: number,
    public userId: number,
    public billId: number
  ) {}
}

export interface TicketInfo {
  ticketId: number;
  userId: number;
  eventId: number;
  billId: number;
}
