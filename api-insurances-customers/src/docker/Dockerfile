# Usar a imagem do Maven para construir a aplicação
FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Usar a imagem do OpenJDK para rodar a aplicação
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/seguro-api.jar seguro-api.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "seguro-api.jar"]
