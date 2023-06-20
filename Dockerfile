FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
LABEL authors="mamouros99"
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]