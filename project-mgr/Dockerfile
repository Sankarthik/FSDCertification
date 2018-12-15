FROM openjdk:8
#FROM maven:onbuild AS buildenv
LABEL maintainer="sankarkarthick@gmail.com"
#WORKDIR /app
#COPY --from=buildenv app/target/task-manager-springboot.jar  task-mgr-springboot.jar
ADD target/task-manager-springboot.jar  task-mgr-springboot.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "task-mgr-springboot.jar"]
