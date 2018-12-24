import {Component, OnInit, Output, Input} from '@angular/core';
import {Task} from '../model/task';
import {ActivatedRoute, Router} from '@angular/router';
import {TaskService} from '../service/task.service';
import * as moment from 'moment';
import { isNullOrUndefined } from 'util';
import { ParentTask } from '../model/parentTask';

@Component({
  selector: 'app-task-update',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  task: Task;
  parents: ParentTask[];
  parentId: number;
  today: any;
  errorMsg: any;
  tempStartDt: Date;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService
  ) {
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
    });
    this.today = moment().format('YYYY-MM-DD');
    this.loadParents();
  }

  private loadParents() {
    this.taskService.getAllTasks().then(value => this.parents = value);
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

}
