import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Constants } from '../common/constants';

@Injectable({
  providedIn: 'root'
})
export class AuditorService {

   constructor(private http: HttpClient) {

  }

  getAuditorInfo() {
    return this.http.get(Constants.endpoint + Constants.auditorUrl);
  }
}
