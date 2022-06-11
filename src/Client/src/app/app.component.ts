import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'angular-management-system';

  constructor(private router: Router) {}

  searchEvent(input: string) {
    this.router.navigateByUrl(`/events/search/${input}`);
  }
}
