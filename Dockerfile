# Base para o backend
FROM eclipse-temurin:17-jdk-alpine AS backend

WORKDIR /app

# Instalação do Git e Maven
RUN apk add --no-cache git maven

# Clonagem e build do backend
RUN git clone https://github.com/seriikmota/generic-architecture.git && \
    cd generic-architecture && \
    git checkout main && \
    mvn dependency:go-offline && \
    mvn clean install -DskipTests && \
    cp target/*.jar /app/backend.jar

# Base para o frontend
FROM node:18-alpine AS frontend

WORKDIR /frontend

# Instalação do Git
RUN apk add --no-cache git

# Clonagem e build do frontend
RUN git clone https://github.com/seu-usuario/seu-repositorio-frontend.git && \
    cd seu-repositorio-frontend && \
    git checkout main && \
    npm install && \
    npm run build

# Combinar os dois projetos em uma única imagem
FROM eclipse-temurin:17-jdk-alpine

# Copiar o backend
WORKDIR /app
COPY --from=backend /app/backend.jar /app/backend.jar

# Copiar o build do frontend
WORKDIR /app/frontend
COPY --from=frontend /frontend/seu-repositorio-frontend/build /app/frontend

# Exposição das portas do backend e do frontend (caso necessário)
EXPOSE 8080 3000

# Comando para iniciar o backend
CMD ["java", "-jar", "/app/backend.jar"]
