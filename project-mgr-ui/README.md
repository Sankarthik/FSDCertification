# ProjectManager

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.2.7.

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

--------------------------------------------
### FSD Project Management Case Study ###
--------------------------------------------

Application is to manage the Project and Tasks with the below business functionalities.
To have the new project and task, User needs to be created

	-	 User can view all the existing projects/tasks and also filter the tasks in Frontend. (Backend JPA Filter is defined and not implemented on purpose)

	-   User can add a new project/task with the mandatory fields. Proper validations have been added.

	-   User can edit an existing project/task and also suspend / end the project/task.


Disable Behaviour
-----------------
View Task row will be shown with Edit and End button and these buttons will be disabled once the task is set to COMPLETED.
View Project will be shown with Edit and Suspend button and these buttons will be disabled once the suspend is triggered.

Technologies Used
------------------
project-manager-ui (Frontend) - Frontend application uses Angular 7 and latest dependencies.
							  -  All screens prototypes used Bootstrap version 4 and npm as dependency management.
							  

### Application Execution Instructions: ###
-------------------------------------------
Frontend:
---------
		- Use 'ng-serve' and the application will be available in 'http://localhost:4200' (with default port)
		- Use ng build --prod to create a image using Docker for deployment.

GIT Repository
---------------
Public: https://github.com/Sankarthik/FSDCertification

Docker HUB Repository
----------------------
Frontend: docker pull sankarthik30/project-mgr-ui:<<tagname>>


### Docker Build Image and Deploy into Docker HUB ###
------------------------------------------------------
To build and run the project in Docker
----------------------------------------
Front End Steps
----------------
	 Step 1 -> docker build --rm -f "Dockerfile" -t project-mgr-ui:1.0 .
	 Step 2 -> docker run --rm -d -p 8085:80/tcp --name project-mgr-ui project-mgr-ui:1.0
		
		
Jenkins Steps inside Docker
---------------------------
1) Add .gitconfig file to add sslVerify = false in Jenkins_Home dir inside Docker container.
2) If it doesnot work -> execute "git config --global http.sslverify false" in Jenkins_Home dir
3) Start docker run -p 8088:8080 jenkinsci/blueocean
4) Jenkinsfile is being used as pipeline script to build the application and build the docker image and to run the application.


To run docker inside Jenkins
----------------------------
docker run -u root -p 8088:8080 -v jenkins-data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v "/C/Users/GiridharanS":/home jenkinsci/blueocean

System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "sandbox allow-scripts; default-src 'self'; style-src 'self' 'unsafe-inline';")

login into container
--------------------
docker exec -it <mycontainer> bash