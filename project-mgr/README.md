
## FSD Task Manager Case Study

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

Docker HUB URL:  https://hub.docker.com/r/sankarthik30/task-mgr-springboot-final/
Git REPO: https://github.com/Sankarthik/FSDTraining

Build Steps:
-------------

My Sql Docker Steps
--------------------
  Step 1 -> docker pull mysql:latest
  
  Step 2 -> docker run -p 3306:3306 --name mysql-standalone -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=cogdb -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin -d mysql:latest


Backend Steps
---------------
1) From the Dockerfile location in docker terminal ->  
		Step 1->    docker build -f Dockerfile -t task-mgr-springboot-1.0 . 
				
		Step 2 ->   docker run -p 8080:8080 --name task-mgr-springboot-1.0 --link mysql-standalone:mysql -d task-mgr-springboot-1.0  (Run with my SQL)
		
        Step 3 ->   docker logs -f mysql-standalone and docker logs -f task-mgr-springboot-1.0 to verify logs
		
	 
2) Using Jenkinsfile
	1) docker pull jenkinssci/blueocean
	2) Using Jenkins Pipeline script, Build the image and will be pushed to Docker HUB


