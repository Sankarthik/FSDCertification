import {Component, OnInit} from '@angular/core';
import {Task} from '../model/task';
import {ActivatedRoute, Router} from '@angular/router';
import {TaskService} from '../service/task.service';
import * as moment from 'moment';
import { Project } from '../model/project';
import { ProjectService } from '../service/project.service';

@Component({
  selector: 'app-task-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  tasks: Task[];
  filteredTasks: Task[];
  filterByName: string;
  filterByParentTask: number;
  filterByPriorityFrom: number;
  filterByPriorityTo: number;
  filterByStartDate: Date;
  filterByEndDate: Date;
  currentDate: Date;
  projects: Project[];
  projectName: string;
  projectId: number;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private taskService: TaskService,
              private projectService: ProjectService) {
    this.getTasks();
  }

  ngOnInit() {
    this.getTasks();
    this.currentDate =  new Date();
  }

  getTasks(): void {
    this.taskService.getAllTasks().then(value => {
      this.tasks = value;
      this.filteredTasks = value;
    });
  }
  update(t: Task ): void {
    this.router.navigate(['/update' , t.id]);
  }

  delete(t: Task): void {
    this.taskService.deleteTask(t.id, t)
      .then(
        value => {
          this.getTasks();
        }
      );
  }

  finishTask(t: Task): void {
    t.endDate = new Date(moment.now());
    t.status = 'COMPLETED';
    this.taskService.updateTask(t.id, t)
      .then(
        value => {
          this.getTasks();
        }
      );
  }
  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  isTaskActive(t: Task): boolean {
    return moment(t.startDate).isSameOrBefore(moment())
           && (t.endDate == null || moment(t.endDate).isAfter(moment()));
  }

  isTaskExpired(t: Task): boolean {
    return moment(t.endDate).isBefore(moment());
  }

  loadProjects(): void {
    this.projectService.getAllProjects().then(value => {
      this.projects = value;
    });
  }

  getTaskByProject(id: number): void {
    this.taskService.getTaskByProject(id).then(task => {
      this.filteredTasks = task;
    });
  }

  onProjectSelected() {
    this.projects = this.projects.filter(project => {
      if (project.id == this.projectId) {
        this.projectName = project.project;
      }
    });
    this.getTaskByProject(this.projectId);
  }

  // Sorting in ascending order by Start Date
  sortByStartDate() {
    this.filteredTasks = this.tasks.sort ((a: any, b: any) =>
      new Date(a.startDate).getTime() - new Date(b.startDate).getTime()
    );
  }
   // Sorting in ascending order by End Date
   sortByEndDate() {
    this.filteredTasks = this.tasks.sort ((a: any, b: any) =>
      new Date(a.endDate).getTime() - new Date(b.endDate).getTime()
    );
  }
  // Sorting in descending order by Prority
  sortByPriority() {
    this.filteredTasks = this.tasks.sort ((a: any, b: any) =>
       b.priority - a.priority
    );
  }
  // Sorting in descending order by Completed (end date in desc order)
  sortByCompleted() {
      this.filteredTasks = this.tasks.filter(t => this.isTaskExpired(t));
      this.filteredTasks = this.filteredTasks.sort ((a: any, b: any) =>
        new Date(b.endDate).getTime() - new Date(a.endDate).getTime()
      );
  }

  resetFilter() {
    this.getTasks();
  }
}
