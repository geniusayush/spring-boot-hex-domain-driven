FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/backend-coding-challenge-1.0.jarbackend-coding-challenge-1.0.jar
ENTRYPOINT ["java","-jar","/backend-coding-challenge-1.0.jar"]