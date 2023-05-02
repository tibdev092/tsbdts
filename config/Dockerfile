FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar cloud-config.jar
ENTRYPOINT ["java","-jar","/cloud-config.jar"]