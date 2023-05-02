FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar webapp.jar
ENTRYPOINT ["java","-jar","/webapp.jar"]