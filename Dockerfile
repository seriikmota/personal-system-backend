FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Instalação do git e Maven
RUN apk add --no-cache git maven

# Clonagem do repositório e build
RUN git clone https://github.com/seriikmota/generic-architecture.git && \
    cd generic-architecture && \
    git checkout main && \
    mvn dependency:go-offline && \
    mvn clean install -DskipTests && \
    cp target/*.jar /app/app.jar

# Exposição da porta da aplicação
EXPOSE 8080

# Comando de inicialização
CMD ["java", "-jar", "/app/app.jar"]
