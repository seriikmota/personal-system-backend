FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -q -e -DskipTests dependency:go-offline

COPY src ./src
ARG SKIP_TESTS=true
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -q -e clean package -DskipTests=${SKIP_TESTS}

FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=25.0 \
-XX:+ExitOnOutOfMemoryError -Dfile.encoding=UTF-8" \
    SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]
