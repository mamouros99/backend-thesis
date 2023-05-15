FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY . /app
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build


FROM eclipse-temurin:17-jdk-alpine AS deployment
WORKDIR /app
LABEL authors="mamouros99"
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]