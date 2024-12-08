# Etapa 1: Base para o backend
FROM eclipse-temurin:17-jdk-alpine AS backend

WORKDIR /app
COPY target/*.jar app.jar


RUN apk add --no-cache git maven

# Clonagem do repositório e build
RUN git clone https://github.com/seriikmota/generic-architecture.git && \
    cd generic-architecture && \
    git checkout main && \
    mvn dependency:go-offline && \
    mvn clean install -DskipTests && \
    cp target/*.jar app-architecture.jar

# Etapa 2: Base para o frontend
FROM node:18-alpine AS frontend

WORKDIR /frontend

RUN apk add --no-cache git

RUN git clone https://github.com/Lipolys/personal-system-frontend.git && \
    cd personal-system-frontend && \
    git checkout master && \
    npm install && \
    npm run build

# Etapa 3: Combinação dos projetos em uma única imagem
FROM eclipse-temurin:17-jdk-alpine

# Copiar o build do frontend
COPY --from=frontend /frontend/personal-system-frontend/dist /app/frontend

# Exposição das portas
EXPOSE 8080 3000

CMD ["java", "-jar", "/app/app.jar"]