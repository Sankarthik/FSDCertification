import { Component, OnInit } from '@angular/core';
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
  filteredUsers: User[];
  errorMsg: any;
  isUpdate: boolean;
  searchFilter: string;

  constructor(private userService: UserService) {
      this.user = new User();
  }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getAllUsers().then(value => {
      this.users = value;
      this.filteredUsers = value;
    });
  }

  update(u: User ): void {
    this.isUpdate = true;
    this.user.employeeId = u.employeeId;
    this.user.firstName = u.firstName;
    this.user.lastName = u.lastName;
  }

  delete(u: User): void {
    this.userService.deleteUser(u.employeeId)
      .then(
        value => {
          this.getUsers();
        }
      );
  }

  onSubmit() {
    if (!this.validateForm()) {
      return false;
    }
    this.errorMsg = '';
    this.userService.addUser(this.user).then(
      value => {
        this.getUsers();
        this.emptyFields();
      }
    );
  }

  onUpdate() {
    if (!this.validateForm()) {
      return false;
    }
    this.errorMsg = '';
    this.userService.updateUser(this.user).then(
      value => {
        this.getUsers();
      }
    );
    this.emptyFields();
    this.isUpdate = false;
  }

  emptyFields() {
    this.user.employeeId = undefined;
    this.user.firstName = '';
    this.user.lastName = '';
  }

  public validateForm() {
    const firstName = this.user.firstName;
    const lastName = this.user.lastName;
    const empId = this.user.employeeId;

    if (isNullOrUndefined(firstName) || firstName.trim().length < 1) {
      this.errorMsg = `FirstName is mandatory`;
      return false;
    }
    if (isNullOrUndefined(this.user.lastName) || lastName.trim().length < 1) {
      this.errorMsg = `LastName is mandatory`;
      return false;
    }
    if (isNullOrUndefined(empId) || !empId) {
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

  filterFirstName(filterByFirstName: string) {
    this.filteredUsers = this.users.filter(user =>
      user.firstName.toUpperCase().startsWith(filterByFirstName.toUpperCase()));
  }

  filterLastName(filterByLastName: string) {
    this.filteredUsers = this.users.filter(user =>
      user.lastName.toUpperCase().startsWith(filterByLastName.toUpperCase()));
  }

  filterEmpId(filterByEmpId: string) {
    this.filteredUsers = this.users.filter(user => {
        if (user.employeeId.toString().match(filterByEmpId)) {
          return user;
        }
    });
  }

  resetFilter() {
    this.getUsers();
  }
}
