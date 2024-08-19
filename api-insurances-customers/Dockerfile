# Usar a imagem do Maven para construir a aplicação
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY api-insurances-customers/pom.xml api-insurances-customers/
COPY api-insurances-customers/src api-insurances-customers/src
RUN mvn -f /app/api-insurances-customers/pom.xml clean package -DskipTests

# Usar a imagem do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/api-insurances-customers/target/api-insurances-customers-0.0.1-SNAPSHOT.jar api-insurances-customers.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-insurances-customers.jar"]
