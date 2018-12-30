### FSD Project Management Case Study ###
--------------------------------------------

Application is to manage the Project and Tasks with the below business functionalities.
To have the new project and task, User needs to be created

	-	 User can view all the existing projects/tasks and also filter the tasks in Frontend. (Backend JPA Filter is defined and not implemented on purpose)

	-   User can add a new project/task with the mandatory fields. Proper validations have been added.

	-   User can edit an existing project/task and also suspend / end the project/task.


Technologies Used
------------------
project-manager (Backend) - Spring Boot project uses MySql DB for real time and H2 embedded for testing.
						  - This also uses Flyway db for db version management and change log.
						  - Rest API, Spring Data JPA, JUnit and ECLEmma JaCaCo plugin along with Maven plugin.
						  
### Application Execution Instructions: ###
-------------------------------------------

Backend:
--------
		- Use 'mvn clean install' to add all the dependencies.
		- Use 'mvn spring-boot:run' to run the backend REST API application which will be available in 'http://localhost:8080' (with default port)
		- Use 'mvn clean install sonar:sonar' to run the static analysis along with the code coverage reports using JaCaCoPlugin.
		- Change the "profile = docker" in the application.properties file to build the docker image for deployment.

GIT Repository
---------------
Public: https://github.com/Sankarthik/FSDCertification

Docker HUB Repository
----------------------
Backend:  docker pull sankarthik30/project-mgr-springboot:<<tagname>>


### Docker Build Image and Deploy into Docker HUB ###
------------------------------------------------------
To build and run the project in Docker
----------------------------------------

My Sql Docker Steps
--------------------
  Step 1 -> docker pull mysql:latest
  
  Step 2 -> docker run -p 3306:3306 --name mysql-standalone -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=cogdb -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin -d mysql:latest


Backend Steps
---------------
From the Dockerfile location in docker terminal ->  
		Step 1->    docker build -f Dockerfile -t project-mgr-springboot:1.0 . 
		
		Step 2->    docker run -p 8080:8080 project-mgr-springboot (Run alone without DB) - Dont change PORT in docker vm's to avoid nightmares.:)
		
		Step 3 ->   docker run -p 8080:8080 --name project-mgr-springboot --link mysql-standalone:mysql -d project-mgr-springboot  (Run with my SQL)
		
        Step 4 ->   docker logs -f mysql-standalone and docker logs -f project-mgr-springboot to verify logs
		
		
Jenkins Steps inside Docker
---------------------------
1) Add .gitconfig file to add sslVerify = false in Jenkins_Home dir inside Docker container.
2) If it doesnot work -> execute "git config --global http.sslverify false" in Jenkins_Home dir
3) Start docker run -p 8088:8080 jenkinsci/blueocean
4) Jenkinsfile is being used as pipeline script to build the project and also build the docker image, to run the image.


To run docker inside Jenkins
----------------------------
docker run -u root -p 8088:8080 -v jenkins-data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v "/C/Users/GiridharanS":/home jenkinsci/blueocean

System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "sandbox allow-scripts; default-src 'self'; style-src 'self' 'unsafe-inline';")

login into container
--------------------
docker exec -it <mycontainer> bash



