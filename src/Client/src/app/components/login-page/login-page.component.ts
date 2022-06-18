import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable, tap } from 'rxjs';
import { User } from 'src/app/common/user';
import { UserCredentials } from 'src/app/common/user-credentials';
import { UserDetails } from 'src/app/common/user-details';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {
  loginFormGroup: FormGroup;

  loggedUser: UserDetails;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private route: Router,
    private cookieService: CookieService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      credentials: this.formBuilder.group({
        username: [''],
        password: [''],
      }),
    });
  }

  handleLogin() {
    let userCredentials = new UserCredentials(
      this.loginFormGroup.get('credentials').value.username,
      this.loginFormGroup.get('credentials').value.password
    );

    this.userLogin(userCredentials);
  }

  userLogin(credentials: UserCredentials) {
    this.authenticationService
      .loginUser(credentials)
      .subscribe((response: HttpResponse<any>) => {
        const token = response.headers.get('Authorization');
        this.authenticationService.saveToken(token);
        this.route.navigateByUrl('');
      });
  }

  // getLoggedUser(credentials: UserCredentials) {
  //   this.authenticationService
  //     .getLoggedInUser(credentials)
  //     .subscribe((result) => {
  //       this.loggedUser = result;
  //     });
  // }

  getUser() {
    return this.loggedUser;
  }

  userRegistration() {}
}
