# Usar a imagem do Maven para construir a aplicação
FROM maven:3.8.4-openjdk-17 AS build
#WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Usar a imagem do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim
#WORKDIR /src
# Verificar a existência do diretório 'target'
RUN #mkdir -p /src/target
COPY --from=build /target/api-insurances-customers-0.0.1-SNAPSHOT.jar api-insurances-customers.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "api-insurances-customers.jar"]
