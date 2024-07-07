FROM openjdk:17-jdk-alpine

WORKDIR /app


ADD ./target/*.jar ./target/ChangeModel /app/

RUN mv /app/*.jar /app/app.jar && chmod -R 777 /app
