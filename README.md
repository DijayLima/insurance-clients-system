## Sistema de Cadastro de Clientes e Simulação/Contratação de Seguros

### Visão Geral

Este projeto consiste em duas APIs separadas:

1. **Cliente API**: Responsável pelo cadastro e gerenciamento de clientes.
2. **Seguro API**: Responsável pela simulação e contratação de seguros.

Ambas as APIs são desenvolvidas em Java utilizando o framework Spring Boot. O projeto também utiliza PostgreSQL como banco de dados para armazenar as informações.

### Estrutura do Projeto

```plaintext
insurance-clients-system/
├── api-customer-registration/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── api-insurances-customers/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── docker-compose.yml
└── README.md
```
### Funcionalidades Principais
 - **Cliente API**:

   - Cadastro, atualização, e consulta de clientes.
   - Relacionamento de clientes com endereços.
- **Seguro API**:

   - Simulação de seguros com base nas informações de clientes.
   - Contratação de seguros.
### Tecnologias Utilizadas
   - Java 17: Linguagem de programação.
   - Spring Boot 3.x: Framework para construção das APIs.
   - PostgreSQL: Banco de dados relacional.
   - Docker & Docker Compose: Para containerização e orquestração dos serviços.
   - Swagger: Documentação da API.
### Pré-requisitos
   Antes de começar, certifique-se de ter instalado em seu ambiente:

   - Java 17 ou superior.
- Maven 3.6+
- Docker e Docker Compose
### Como Configurar e Executar o Projeto
#### 1. Clonar o Repositório
````
   git clone https://github.com/seuusuario/sistema-clientes-seguros.git
   cd insurance-clients-system
````

#### 2. Construir as Imagens Docker
   Dentro da raiz do projeto, execute o seguinte comando para construir as imagens Docker das APIs:
````
   docker-compose build
````

#### 3. Subir os Serviços com Docker Compose
   Após a construção das imagens, suba os serviços (APIs e bancos de dados) com:
````
docker-compose up
````

Esse comando irá subir os seguintes serviços:

- Cliente API: Acessível em http://localhost:8080
- Seguro API: Acessível em http://localhost:8081
- PostgreSQL: Dois bancos de dados separados, um para cada API.
#### 4. Acessar as APIs
   - Cliente API: Acesse a documentação Swagger em http://localhost:8080/swagger-ui.html.
   - Seguro API: Acesse a documentação Swagger em http://localhost:8081/swagger-ui.html.
### Estrutura das Configurações
   - ```api-customer-registration/src/main/resources/application.properties```: Contém as configurações do ambiente de desenvolvimento da API de Clientes.
   - ```api-insurances-customers/src/main/resources/application.properties```: Contém as configurações do ambiente de desenvolvimento da API de Seguros.
### Testes
Os testes unitários estão localizados nos diretórios src/test/java/ dentro de cada uma das APIs (cliente-api e seguro-api). Para rodar os testes, você pode usar o Maven:

````
cd ../api-customer-registration
mvn test

cd ../api-insurances-customers
mvn test
````

### Limpeza
Para parar e remover os contêineres Docker, execute:

````
docker-compose down
````

### Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

