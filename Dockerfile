# Etapa 1: Base para o backend
FROM eclipse-temurin:17-jdk-alpine AS backend

WORKDIR /app

# Instalar dependências: Git e Maven
RUN apk add --no-cache git maven

# Clonagem do repositório e build do projeto
RUN git clone https://github.com/seriikmota/generic-architecture.git && \
    cd generic-architecture && \
    git checkout main && \
    mvn dependency:go-offline && \
    mvn clean install -DskipTests

# Copiar o JAR gerado para a imagem
# Ajuste o nome do JAR conforme o nome gerado no seu projeto
RUN cp generic-architecture/target/*.jar /app/app.jar

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

WORKDIR /app

# Copiar o build do frontend
COPY --from=frontend /frontend/personal-system-frontend/dist /app/frontend

# Copiar o arquivo JAR do backend
COPY --from=backend /app/app.jar /app/app.jar

# Exposição das portas
EXPOSE 8080 3000

# Executar o backend com o JAR correto
CMD ["java", "-jar", "/app/app.jar"]
