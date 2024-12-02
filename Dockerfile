FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY  /app/build/target/*.jar /app/app.jar
EXPOSE 8080
CMD ["java","jar","/app/app.jar"]