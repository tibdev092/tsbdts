FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar emp-microservice.jar
ENTRYPOINT ["java","-jar","/emp-microservice.jar"]