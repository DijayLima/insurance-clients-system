# Usar a imagem do Maven para construir a aplicação
FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Usar a imagem do OpenJDK para rodar a aplicação
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/cliente-api.jar cliente-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cliente-api.jar"]
