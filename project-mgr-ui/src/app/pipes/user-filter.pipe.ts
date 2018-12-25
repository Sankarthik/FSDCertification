import { Pipe, PipeTransform, Injectable } from '@angular/core';
import { User } from '../model/user';

@Pipe({
  name: 'userFilter'
})

@Injectable()
export class UserFilterPipe implements PipeTransform {

  transform(users: Array<User>, filterByEmpId?: number,
                                filterByFirstName?: string,
                                filterByLastName?: string) {

    if (filterByFirstName) {
      users = users.filter(user =>
                    user.firstName.toUpperCase().startsWith(filterByFirstName.toUpperCase()));
    }
    if (filterByLastName) {
      users = users.filter(user =>
                    user.lastName.toUpperCase().startsWith(filterByLastName.toUpperCase()));
    }
    if (filterByEmpId) {
      users = users.filter(user => {
        if (user.employeeId === filterByEmpId) {
          return user;
        }
      });
    }

    return users;
  }

}
