# Etapa 1: build da aplicação com Maven e JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia o repositório local Maven (.m2) para dentro do container
COPY ./libs/ojdbc17.jar /root/.m2/repository/com/oracle/database/jdbc/ojdbc17/21.9.0.0/ojdbc17-21.9.0.0.jar

# Copia pom.xml e baixa dependências (melhora cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src

# Compila o projeto e gera o JAR (sem rodar testes)
RUN mvn clean package -DskipTests

# Etapa 2: imagem final com apenas o JAR e JDK 21
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copia o JAR do stage anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta usada pelo Spring Boot
EXPOSE 8601

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
