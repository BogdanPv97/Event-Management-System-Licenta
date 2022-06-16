import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { User } from 'src/app/common/user';
import { UserCredentials } from 'src/app/common/user-credentials';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {
  loginFormGroup: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private route: Router
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
    this.authenticationService.loginUser(credentials).subscribe(
      (response: HttpResponse<any>) => {
        console.log('sunt in login function');
        console.log('response ' + response);
        const token = response.headers.get('Authorization');
        this.authenticationService.saveToken(token);
        //this.authenticationService.addUserToLocalCache(response.body);
        this.route.navigateByUrl('');
        console.log(token);
      },
      (errorResponse: HttpErrorResponse) => {
        console.log('error');
      }
    );
  }

  userRegistration() {}
}
