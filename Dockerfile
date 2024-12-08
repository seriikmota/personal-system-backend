# Etapa 1: Base para o backend
FROM eclipse-temurin:17-jdk-alpine AS backend

WORKDIR /app

# Instalar dependências: Git e Maven
RUN apk add --no-cache git maven

# Clonagem do repositório do backend e build do projeto
RUN git clone https://github.com/seriikmota/generic-architecture.git && \
    cd generic-architecture && \
    git checkout main && \
    mvn dependency:go-offline && \
    mvn clean install -DskipTests

# Copiar o JAR gerado para a pasta de destino da imagem
RUN cp generic-architecture/target/*.jar /app/backend.jar

# Etapa 2: Base para o frontend
FROM node:18-alpine AS frontend

WORKDIR /frontend

# Instalar dependências: Git
RUN apk add --no-cache git

# Clonagem do repositório do frontend e build da aplicação
RUN git clone https://github.com/Lipolys/personal-system-frontend.git && \
    cd personal-system-frontend && \
    git checkout master && \
    npm install && \
    npm run build

# Etapa 3: Combinação dos projetos em uma única imagem
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar o build do frontend (aplicação estática) para dentro da pasta /frontend
COPY --from=frontend /frontend/personal-system-frontend/dist /app/frontend

# Copiar o JAR gerado do backend para a pasta /app
COPY --from=backend /app/backend.jar /app/backend.jar

# Exposição das portas da aplicação (backend 8080, frontend 3000)
EXPOSE 8080 3000

# Comando de inicialização: Inicializar o backend e servir o frontend
CMD ["sh", "-c", "java -jar /app/app.jar & http-server /app/frontend"]
