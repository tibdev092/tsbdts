FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar naming-server.jar
ENTRYPOINT ["java","-jar","/naming-server.jar"]