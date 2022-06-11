import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserUpcomingEventsComponent } from './user-upcoming-events.component';

describe('UserUpcomingEventsComponent', () => {
  let component: UserUpcomingEventsComponent;
  let fixture: ComponentFixture<UserUpcomingEventsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserUpcomingEventsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserUpcomingEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
