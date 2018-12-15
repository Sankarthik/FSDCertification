# TaskManager

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.1.5.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

-------------------------------------------------------------------------------

## FSD Task Manager Case Study

--------------------------------------------------------------------------------

Application is to manage the Task with the below business functionalities.

User can view all the existing tasks and also filter the tasks in Frontend. (Backend JPA Filter is defined and not implemented on purpose)

User can add a new task with mandatory fields of Task name and Start date.

User can edit an existing task

User can end the task by clicking the End Task which will set the current date as End

Disable Behaviour
-----------------
Task row will be shown with Edit and Delete button in case of start date of the task is future.
Task row will be shown with Edit and End Task button in case of start date(task) is in active period.
All conditional buttons of Edit/End Task/Delete will be disabled/uneditable in case task is done/completed with end date set as of current date.
In case of end date is set with future date, then still Edit and End Task will be available for edit.

# Docker Build Image and Deploy into Docker HUB

Docker HUB URL:  https://hub.docker.com/r/sankarthik30/task-mgr-ui-final:1.0/
Git REPO: https://github.com/Sankarthik/FSDTraining

Build Steps:
-------------

1) Manual Build
	
	 Step 1 -> docker build --rm -f "Dockerfile" -t task-mgr-ui-3.0 .
	 Step 2 -> docker run --rm -d -p 8085:80/tcp --name task-mgr-ui-3.0 task-mgr-ui-3.0:latest
	 
2) Using Jenkinsfile
	1) docker pull jenkinssci/blueocean
	2) Using Jenkins Pipeline script, Build the image and will be pushed to Docker HUB


