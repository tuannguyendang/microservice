import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs'; // RXJS 6.0 >
import { Constants } from '../common/constants';
import { AuditorService } from './auditor.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private AuditorService: AuditorService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authToken = Constants.token;
    let authReq = req;
    if (authToken) {
      const headers = new HttpHeaders({
        'x-access-token': authToken,
        // 'Content-Type': 'application/json',
        'Access-Control-Allow-Credentials': 'true',
        'Access-Control-Allow-Origin': '*',
      });
      authReq = req.clone({ headers });
    }
    return next.handle(authReq);
  }
}
