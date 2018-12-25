import { Component, OnInit } from '@angular/core';
import { Project } from '../model/project';
import { ProjectService } from '../service/project.service';
import { isNullOrUndefined } from 'util';
import * as moment from 'moment';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
  project: Project;
  projects: Project[];
  filteredProjects: Project[];
  errorMsg: any;
  isUpdate: boolean;
  searchFilter: string;
  isDateChecked: boolean;

  constructor(private projectService: ProjectService,
              private datePipe: DatePipe) {
    this.project = new Project();
  }

  ngOnInit() {
      this.getProjects();
  }

  getProjects(): void {
    this.projectService.getAllProjects().then(value => {
      this.projects = value;
      this.filteredProjects = value;
    });
  }

  update(p: Project ): void {
    this.isUpdate = true;
    this.project.project = p.project;
    this.project.startDate = p.startDate;
    this.project.endDate = p.endDate;
    this.project.priority = p.priority;
    this.project.managerId = p.managerId;
  }

  onSubmit() {
    if (!this.validateForm()) {
      return false;
    }
    this.errorMsg = '';
    this.projectService.addProject(this.project).then(
      value => {
        this.getProjects();
        this.emptyFields();
      }
    );
  }

  onUpdate() {
    if (!this.validateForm()) {
      return false;
    }
    this.errorMsg = '';
    this.projectService.updateProject(this.project).then(
      value => {
        this.getProjects();
      }
    );
    this.emptyFields();
    this.isUpdate = false;
  }

  emptyFields() {
    this.project.project = '';
    this.project.startDate = undefined;
    this.project.endDate = undefined;
    this.project.priority = 0;
    this.project.managerId = undefined;
  }

  public validateForm() {
    const projectName = this.project.project;
    const t = new Date();
    const today = new Date(t.getFullYear(), t.getMonth(), t.getDate() );
    const endDate = new Date(this.project.endDate);
    const startDate = new Date(this.project.startDate);
    let formattedDate;
    if (isNullOrUndefined(projectName) || projectName.trim().length < 1) {
      this.errorMsg = `Project name is mandatory`;
      return false;
    }

    if (endDate < today || startDate < today) {
      formattedDate = this.formatDate(today);    // moment(today).format('DD-MM-YYYY');
      this.errorMsg = `Start or End Date should be ${formattedDate} or in the future`;
      return false;
    }
    if (endDate < startDate) {
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

  changeChkBox(event) {
    if (event.target.checked) {
      this.isDateChecked = true;
      const startDate = new Date();
      console.log(startDate);
      this.datePipe.transform(startDate, 'yyyy-MM-dd');
      console.log(this.datePipe.transform(startDate, 'MM-dd-yyyy'));

      this.project.startDate = new Date(this.datePipe.transform(startDate, 'MM-dd-yyyy'));
    } else {
      this.isDateChecked = false;
    }
  }
}
