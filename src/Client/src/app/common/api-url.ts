import { settings } from './settings';

export class ApiUrl {
  constructor() {}

  //URL mapping for event controller

  getAllEventsDetails() {
    return settings.baseURL + '/events/eventDetails';
  }

  getEventDetailsById(eventId: number) {
    return settings.baseURL + `/events/eventDetails/${eventId}`;
  }

  getAllEvents() {
    return settings.baseURL + '/events';
  }

  getEventById(eventId: number) {
    return settings.baseURL + `/events/${eventId}`;
  }

  getAllCategories() {
    return settings.baseURL + `/events/categories`;
  }

  getEventsByCategory(categoryId: number) {
    return settings.baseURL + `/events/categories/${categoryId}`;
  }

  getAllEventsFromCity(cityId: number) {
    return settings.baseURL + `/events/city/${cityId}`;
  }

  incrementGoingCounter(eventId: number, userId: number) {
    return settings.baseURL + `/events/${eventId}/going/${userId}/increment`;
  }

  incrementInterestedCounter(eventId: number, userId: number) {
    return (
      settings.baseURL + `/events/${eventId}/interested/${userId}/increment`
    );
  }

  saveEvent() {
    return settings.baseURL + `/events/saveEvent`;
  }

  updateEvent(eventId: number) {
    return settings.baseURL + `/events/update/${eventId}`;
  }

  partialUpdateEvent(eventId: number) {
    return settings.baseURL + `/events/partialUpdate/${eventId}`;
  }

  deleteEvent(eventId: number) {
    return settings.baseURL + `/events/deleteEvent/${eventId}`;
  }

  getCategoryNameForEvent(eventId: number) {
    return settings.baseURL + `/categories/categoryName/${eventId}`;
  }

  //URL mapping for user controller

  loginUser() {
    return settings.baseURL + `/login`;
  }

  getAllUsers() {
    return settings.baseURL + `/users`;
  }

  getAllUsersDetails() {
    return settings.baseURL + '/users/userDetails';
  }

  getUserById(userId: number) {
    return settings.baseURL + `/users/${userId}`;
  }

  registerUser() {
    return settings.baseURL + `/users/registerUser`;
  }

  updateUser(userId: number) {
    return settings.baseURL + `/users/updateUser/${userId}`;
  }

  partialUpdateUser(userId: number) {
    return settings.baseURL + `/users/partialUpdate/${userId}`;
  }

  deleteUser(userId: number) {
    return settings.baseURL + `/users/deleteUser/${userId}`;
  }

  //URL mapping for Bill controller

  getAllBillsForUser(userId: number) {
    return settings.baseURL + `/bills/${userId}`;
  }

  getBillsForUserById(userId: number, billId: number) {
    return settings.baseURL + `/bills/${userId}/${billId}`;
  }

  saveBill() {
    return settings.baseURL + `/bills/saveBill`;
  }

  //URL mapping for ticket controller

  getTicketsFromUser(userId: number) {
    return settings.baseURL + `/tickets/${userId}`;
  }

  getTicketForUserById(userId: number, ticketId: number) {
    return settings.baseURL + `/tickets/${userId}/${ticketId}`;
  }

  saveTicket() {
    return settings.baseURL + `/tickets/saveTicket`;
  }

  deleteTicketById(ticketId: number) {
    return settings.baseURL + `/tickets/deleteTicket/${ticketId}`;
  }

  //URL mapping for city controller

  getAllCities() {
    return settings.baseURL + `/city`;
  }

  saveCity() {
    return settings.baseURL + 'city/saveCity';
  }
}
