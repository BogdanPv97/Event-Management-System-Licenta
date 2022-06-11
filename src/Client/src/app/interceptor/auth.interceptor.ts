import { Injectable } from '@angular/core';
import { settings } from '../common/settings';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auhenticationService: AuthenticationService) {}

  intercept(
    request: HttpRequest<any>,
    handler: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (request.url.includes(`${settings.baseURL}/login`)) {
      console.log('cdececdas');
      return handler.handle(request);
    }
    if (request.url.includes(`${settings.baseURL}/users/registerUser`)) {
      return handler.handle(request);
    }

    this.auhenticationService.loadToken();
    const token = this.auhenticationService.getToken();
    const httpRequest = request.clone({
      setHeaders: { Authorization: `Bearer ${token}` },
    });
    return handler.handle(httpRequest);
  }
}
