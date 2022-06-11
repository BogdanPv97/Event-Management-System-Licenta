import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListEventsComponent } from './components/list-events/list-events.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { EventService } from './service/event.service';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { MasterEventComponent } from './components/master-event/master-event.component';
import { ListCategoryEventsComponent } from './components/list-category-events/list-category-events.component';
import { UserUpcomingEventsComponent } from './components/user-upcoming-events/user-upcoming-events.component';
import { ListCategoriesComponent } from './components/list-categories/list-categories.component';
import { ListTicketsComponent } from './components/list-tickets/list-tickets.component';
import { ListBillsComponent } from './components/list-bills/list-bills.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CreateEventComponent } from './components/dashboard/create-event/create-event.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule } from '@angular/material/tabs';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { CheckoutPaymentComponent } from './components/checkout-payment/checkout-payment.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
//import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

const routes: Routes = [
  {
    path: 'events/categories/:categoryId',
    component: ListCategoryEventsComponent,
  },
  { path: 'events/search/:keyword', component: ListEventsComponent },
  { path: 'events/browse', component: ListEventsComponent },
  { path: 'events/categories', component: ListCategoriesComponent },
  { path: 'events/:eventId', component: MasterEventComponent },
  { path: 'user/tickets/:userId', component: ListTicketsComponent },
  { path: 'user/bills/:userId', component: ListBillsComponent },
  { path: 'user/login', component: LoginPageComponent },
  { path: 'user/register', component: RegisterPageComponent },
  { path: 'dashboard/createEvent', component: CreateEventComponent },
  { path: 'checkout/payment', component: CheckoutPaymentComponent },
  { path: 'admin/dashboard', component: DashboardComponent },
  { path: '', component: HomePageComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    ListEventsComponent,
    LoginPageComponent,

    HomePageComponent,
    MasterEventComponent,
    ListCategoryEventsComponent,
    UserUpcomingEventsComponent,
    ListCategoriesComponent,
    ListTicketsComponent,
    ListBillsComponent,
    DashboardComponent,
    CreateEventComponent,
    RegisterPageComponent,
    CheckoutPaymentComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatTabsModule,
    NgbModule,
  ],
  providers: [
    EventService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],

  bootstrap: [AppComponent],
})
export class AppModule {}
