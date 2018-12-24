import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { environment } from '../../environments/environment';
import { User } from '../model/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  http: HttpClient;
  userHttpUrl: string = environment.apiUrl + '/users/';
  constructor(http: HttpClient) {
    this.http = http;
  }

  getAllUsers(): Promise<any> {
    return this.http.get<User>(this.userHttpUrl).toPromise().then(value => value);
  }

  addUser(t: User): Promise<any> {
    return this.http.post(this.userHttpUrl, t).toPromise().then(value => value);
  }
}
