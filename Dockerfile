FROM build AS deployment
WORKDIR /app
LABEL authors="mamouros99"
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]