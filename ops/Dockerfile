FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY build/libs/cli-proto-sender-0.0.1-SNAPSHOT.jar app.jar

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
