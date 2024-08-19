# Usar a imagem do Maven para construir a aplicação
FROM maven:3.8.4-openjdk-17 AS build
#WORKDIR /main
COPY . .
RUN mvn clean package -DskipTests

# Usar a imagem do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim
#WORKDIR /src
# Verificar a existência do diretório 'target'
RUN #mkdir -p /src/target
COPY --from=build /target/api-customer-registration-0.0.1-SNAPSHOT.jar api-customer-registration.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-customer-registration.jar"]
