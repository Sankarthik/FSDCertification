import { Component, OnInit, Output, NgModule } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TaskService} from '../service/task.service';
import {Task} from '../model/task';
import {ParentTask} from "../model/parentTask";
import * as moment from 'moment';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'app-task-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  @Output() tasks;
  task: Task;
  parents= [];
  parentId: number;
  errorMsg: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService
  ) {
    this.task = new Task();
    this.task.priority = 1;
  }

  ngOnInit() {
    this.loadParents();
  }

  onSubmit() {
    if(!this.validateForm()) {
      return false;
    }

    if (this.parentId != null) {
      let parent = new ParentTask();
      parent.id = this.parentId;
      this.task.parentTask = parent;
    }
    this.errorMsg = '';
    this.taskService.addTask(this.task).then(
      value => {
        this.router.navigate(['./view']);
      }
    );
  }

  private loadParents() {
    this.taskService.getAllTasks().then(value => this.parents = value);
  }

  public validateForm() {
    let t = new Date();
    let today = new Date(t.getFullYear(), t.getMonth(), t.getDate() );
    let endDate = new Date(this.task.endDate);
    let startDate = new Date(this.task.startDate);
    let formattedDate;
    if(isNullOrUndefined(this.task.task)) {
      this.errorMsg = `Task name is mandatory`;
      return false;
    }
    if(isNullOrUndefined(this.task.startDate)) {
      this.errorMsg = `Start Date is mandatory`;
      return false;
    }

    if (endDate < today || startDate < today) {
      formattedDate = this.formatDate(today);    //moment(today).format('DD-MM-YYYY');
      this.errorMsg = `Start or End Date should be ${formattedDate} or in the future`;
      return false;
    }
    if(endDate < startDate) {
      formattedDate = this.formatDate(startDate); 
      this.errorMsg = `End Date should be greater than start date: ${formattedDate}`;
      return false;
    }
    return true;
  }

  public reset() {
    this.errorMsg = '';
  }

  public formatDate(date: any) {
    return moment(date).format('DD-MM-YYYY');
  }

}

