FROM openjdk:8
#FROM maven:onbuild AS buildenv
LABEL maintainer="sankarkarthick@gmail.com"
#WORKDIR /app
#COPY --from=buildenv app/target/project-manager-springboot.jar  project-manager-springboot.jar
ADD target/project-manager-springboot.jar  project-manager-springboot.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "project-manager-springboot.jar"]
