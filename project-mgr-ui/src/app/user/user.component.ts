import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: User;
  users: User[];
  errorMsg: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService) {
      this.user = new User();
  }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getAllUsers().then(value => this.users = value);
  }

  onSubmit() {
    if (!this.validateForm()) {
      return false;
    }

    this.errorMsg = '';
    this.userService.addUser(this.user).then(
      value => {
        // this.router.navigate(['./view']);
        this.getUsers();
      }
    );
  }

  public validateForm() {
    const firstName = this.user.firstName;
    const lastName = this.user.lastName;
    const empId = this.user.employeeId;
    console.log(empId);

    if (isNullOrUndefined(firstName) || firstName.trim().length < 1) {
      this.errorMsg = `FirstName is mandatory`;
      return false;
    }
    if (isNullOrUndefined(this.user.lastName) || lastName.trim().length < 1) {
      this.errorMsg = `LastName is mandatory`;
      return false;
    }
    if (isNullOrUndefined(this.user.employeeId)) {
      this.errorMsg = `Employee Id is mandatory`;
      return false;
    }

    return true;
  }

  public reset() {
    this.errorMsg = '';
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

}
