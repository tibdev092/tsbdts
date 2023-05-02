FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar dept-microservice.jar
ENTRYPOINT ["java","-jar","/dept-microservice.jar"]