# Usar a imagem do Maven para construir a aplicação
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY api-customer-registration/pom.xml api-customer-registration/
COPY api-customer-registration/src api-customer-registration/src
RUN mvn -f /app/api-customer-registration/pom.xml clean package -DskipTests

# Usar a imagem do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/api-customer-registration/target/api-customer-registration-0.0.1-SNAPSHOT.jar api-customer-registration.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-customer-registration.jar"]
