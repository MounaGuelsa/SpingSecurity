# Étape de construction
FROM maven:3.8-openjdk-8 AS build

WORKDIR /app

COPY . .
RUN chmod +x mvnw
RUN mvn clean package

# Étape d'exécution
FROM openjdk:8-jre-slim

WORKDIR /app

COPY --from=build /app/target/LabXpertProject-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "LabXpertProject-0.0.1-SNAPSHOT.jar"]

# Base de données PostgreSQL
FROM postgres:latest

ENV POSTGRES_DB labxpertnew
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 1234

EXPOSE 5432



docker build -t labexpert:tag .
docker login
docker tag labexpert:tag moonguel/labexpert:tag
docker push moonguel/labexpert:tag