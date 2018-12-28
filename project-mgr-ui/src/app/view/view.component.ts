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
  // filterTask: Task;
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
    // this.filterTask = new Task();
    this.currentDate =  new Date();
  }

  getTasks(): void {
    this.taskService.getAllTasks().then(value => this.tasks = value);
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

  onProjectSelected() {
    this.projects = this.projects.filter(project => {
      if (project.id == this.projectId) {
        this.projectName = project.project;
      }
    });
  }

  // Sorting in ascending order by Start Date
  sortByStartDate() {
    this.filteredProjects = this.projects.sort ((a: any, b: any) =>
      new Date(a.startDate).getTime() - new Date(b.startDate).getTime()
    );
  }
   // Sorting in ascending order by End Date
   sortByEndDate() {
    this.filteredProjects = this.projects.sort ((a: any, b: any) =>
      new Date(a.endDate).getTime() - new Date(b.endDate).getTime()
    );
  }
  // Sorting in descending order by Prority
  sortByPriority() {
    this.filteredProjects = this.projects.sort ((a: any, b: any) =>
      new Date(b.priority).getTime() - new Date(a.priority).getTime()
    );
  }
  // Sorting in descending order by Completed
  sortByCompleted() {
    // this.filteredProjects = this.projects.sort ((a: any, b: any) =>
    //   new Date(b.priority).getTime() - new Date(a.priority).getTime()
    // );
  }
}
