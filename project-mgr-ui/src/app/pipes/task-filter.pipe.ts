import {Injectable, Pipe, PipeTransform} from '@angular/core';
import * as moment from 'moment';
import { Task } from '../model/task';

@Pipe({
  name: "TaskFilter"
})

@Injectable()
export class TaskFilter implements PipeTransform {

  transform(tasks: Array<Task>, filterTask?: string,
                                filterByParentTask?: number,
                                filterByPriorityFrom?: number,
                                filterByPriorityTo?: number,
                                filterByStartDate?: Date,
                                filterByEndDate?: Date) {
    if(filterTask){
      tasks = tasks.filter(task => 
                  task.task.toUpperCase().startsWith(filterTask.toUpperCase()));
    }

    if(filterByParentTask) {
      tasks = tasks.filter(task => {
        let id = task.parentTask !== null ? task.parentTask.id : null;
        if(id == filterByParentTask) {
          return task;
        }
      });
    }

    if(filterByPriorityFrom) {
      tasks = tasks.filter(task => {
        if(task.priority >= filterByPriorityFrom) {
          return task;
        }
      });
    }

    if(filterByPriorityTo) {
      tasks = tasks.filter(task => {
        if(task.priority <= filterByPriorityTo) {
          return task;
        }
      });
    }

    if(filterByStartDate) {
      tasks = tasks.filter(task => {
        if(moment(task.startDate).isSameOrAfter(filterByStartDate)) {
          return task;
        }
      });
    }

    if(filterByEndDate) {
      tasks = tasks.filter(task => {
        if(moment(task.endDate).isSameOrBefore(filterByEndDate)) {
          return task;
        }
      });
    }

    return tasks;
  }
}
