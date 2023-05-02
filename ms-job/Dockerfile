FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar job-microservice.jar
ENTRYPOINT ["java","-jar","/job-microservice.jar"]