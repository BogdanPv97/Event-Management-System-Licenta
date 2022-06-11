import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { City } from 'src/app/common/city';
import { User } from 'src/app/common/user';
import { CityService } from 'src/app/service/city.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css'],
})
export class RegisterPageComponent implements OnInit {
  registerFormGroup: FormGroup;
  cities: City[];

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private cityService: CityService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.populateCities();
    this.registerFormGroup = this.formBuilder.group({
      user: this.formBuilder.group({
        firstName: [''],
        lastName: [''],
        email: [''],
        username: [''],
        password: [''],
        age: [''],
        newsletter: [''],
        city: [''],
      }),
    });
  }

  populateCities() {
    this.cityService.getAllCities().subscribe((result) => {
      this.cities = result;
    });
  }

  onSubmit(content: any) {
    let user = new User(
      this.registerFormGroup.get('user').value.firstName,
      this.registerFormGroup.get('user').value.lastName,
      this.registerFormGroup.get('user').value.email,
      this.registerFormGroup.get('user').value.username,
      this.registerFormGroup.get('user').value.password,
      'user',
      this.registerFormGroup.get('user').value.age,
      this.registerFormGroup.get('user').value.newsletter,
      this.registerFormGroup.get('user').value.city
    );

    this.registerUser(user);

    this.modalService.open(content, { centered: true });
  }

  registerUser(userToSave: User) {
    this.userService.registerUser(userToSave).subscribe();
  }

  getCityByName(cityName: string) {
    for (let tempcity of this.cities) {
      if (tempcity.name == cityName) {
        return tempcity;
      }
    }

    return null;
  }
}
