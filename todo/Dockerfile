FROM openjdk:17-jdk-alpine
MAINTAINER diogo.afg13@gmail.com
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=k8s", "/app.jar"]