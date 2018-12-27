import {Component, OnInit, Output, Input} from '@angular/core';
import {Task} from '../model/task';
import {ActivatedRoute, Router} from '@angular/router';
import {TaskService} from '../service/task.service';
import * as moment from 'moment';
import { isNullOrUndefined } from 'util';
import { ParentTask } from '../model/parentTask';
import { User } from '../model/user';
import { Project } from '../model/project';
import { ProjectService } from '../service/project.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-task-update',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  task: Task;
  parents: ParentTask[];
  parentId: number;
  users: User[];
  projectName: string;
  projectId: number;
  userName: string;
  userId: number;
  today: any;
  errorMsg: any;
  tempStartDt: Date;

  constructor(
            private route: ActivatedRoute,
            private router: Router,
            private taskService: TaskService,
            private projectService: ProjectService,
            private userService: UserService) {
    this.task = new Task();
   }

  ngOnInit() {
    const taskId = this.route.snapshot.paramMap.get('id');
    this.taskService.getTask(taskId).then(value => {
      this.task = value;
      this.tempStartDt = this.task.startDate;
      if (!isNullOrUndefined(this.task.parentTask)) {
        this.parentId = this.task.parentTask.id;
      }
      if (!isNullOrUndefined(this.task.project)) {
        this.projectId = this.task.project.id;
        this.loadProject(this.projectId);
      }
    });
    this.today = moment().format('YYYY-MM-DD');
    this.loadParents();
    this.loadUser(this.task.userId);
  }

  private loadParents() {
    this.taskService.getAllTasks().then(value => this.parents = value);
  }

  loadUsers() {
    this.userService.getAllUsers().then(value => this.users = value);
  }

  loadProject(id: number): void {
    this.projectService.getProject(id).then(value => {
      this.projectName = value.project;
    });
  }

  loadUser(id: number): void {
    this.userService.getUser(id).then(value => {
      this.userName = value;
    });
  }

  onSubmit() {
    if (!this.validateForm()) {
      return false;
    }
    if (this.parentId != null) {
      const parent = new ParentTask();
      parent.id = this.parentId;
      this.task.parentTask = parent;
    }
    this.taskService.updateTask(this.task.id, this.task)
      .then(
       value => { this.router.navigate(['./view']);
       }
      );
  }

  onCancel() {
   this.router.navigate(['./view']);
  }

  public validateForm() {
    const t = new Date();
    const today = new Date(t.getFullYear(), t.getMonth(), t.getDate() );
    const tmpEndDate = this.task.endDate == null ? undefined : this.task.endDate;
    const endDate = new Date(tmpEndDate);
    const startDate = new Date(this.task.startDate);
    const tmpStartDt = new Date(this.tempStartDt);
    const taskName = this.task.task;
    let formattedDate;

    if (isNullOrUndefined(taskName) || taskName.trim().length < 1) {
      this.errorMsg = `Task name is mandatory`;
      return false;
    }
    if (isNullOrUndefined(this.task.startDate) || (!this.task.startDate)) {
      this.errorMsg = `Start Date is mandatory`;
      return false;
    }

    if (endDate < today) {
      formattedDate = this.formatDate(today);
      this.errorMsg = `End Date should be ${formattedDate} or in the future`;
      return false;
    }

    if (startDate < tmpStartDt) {
      formattedDate = this.formatDate(tmpStartDt);
      this.errorMsg = `Start Date should be ${formattedDate} or in the future`;
      return false;
    }
    if (endDate < startDate) {
      formattedDate = this.formatDate(startDate);
      this.errorMsg = `End Date should be greater than start date: ${formattedDate}`;
      return false;
    }
    return true;
  }

  public formatDate(date: any) {
    return moment(date).format('DD-MM-YYYY');
  }

  onUserSelected() {
    this.users = this.users.filter(user => {
      if (user.employeeId == this.userId) {
        this.userName = user.firstName + '-' + user.lastName;
      }
  });
     this.task.userId = this.userId;
  }
}
