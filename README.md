# FSD Task Manager Case Study
--------------------------------------------

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


Technologies Used
------------------
taskmanager - Spring Boot WAR project uses MySql DB for real time and H2 embedded for testing.
This also uses Flyway db for db version management and change log.

task ui - Frontend application uses Angular 6 and latest dependencies 


# Docker Build Image and Deploy into Docker HUB


Git REPO: https://github.com/Sankarthik/FSDTraining
Frontend Docker HUB URL:  https://hub.docker.com/r/sankarthik30/task-mgr-ui-final:1.0/
Backend Docker HUB URL:  https://hub.docker.com/r/sankarthik30/task-mgr-springboot-final/

To run the project
---------------------

Frontend - use 'ng serve' and the application will be available in 'http://localhost:4200' (with default port)

Backend - run the CtsTaskMgrApplication.java application.

To build and run the project in Docker
----------------------------------------
Front End Steps
----------------
	 Step 1 -> docker build --rm -f "Dockerfile" -t task-mgr-ui-3.0 .
	 Step 2 -> docker run --rm -d -p 8085:80/tcp --name task-mgr-ui-3.0 task-mgr-ui-3.0:latest


My Sql Docker Steps
--------------------
  Step 1 -> docker pull mysql:latest
  
  Step 2 -> docker run -p 3306:3306 --name mysql-standalone -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=cogdb -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin -d mysql:latest


Backend Steps
---------------
From the Dockerfile location in docker terminal ->  
		Step 1->    docker build -f Dockerfile -t task-mgr-springboot-1.0 . 
		
		Step 2->    docker run -p 8080:8080 task-mgr-springboot-1.0 (Run alone without DB) - Dont change PORT in docker vm's to avoid nightmares.:)
		
		Step 3 ->   docker run -p 8080:8080 --name task-mgr-springboot-1.0 --link mysql-standalone:mysql -d task-mgr-springboot-1.0  (Run with my SQL)
		
        Step 4 ->   docker logs -f mysql-standalone and docker logs -f task-mgr-springboot-1.0 to verify logs
		
		
Jenkins Steps inside Docker
---------------------------
1) Add .gitconfig file to add sslVerify = false in Jenkins_Home dir inside Docker container.
2) Start docker run -p 8088:8080 jenkinsci/blueocean


