# Calculator Service

A microservice-based calculator application with Kafka integration, built using Spring Boot and Maven.

## Features

- RESTful API for basic arithmetic operations
  - Addition
  - Subtraction
  - Multiplication
  - Division
- Event streaming with Apache Kafka
- Multi-module Maven project structure
- Logging with SLF4J
- Request ID tracking
- Error handling for division by zero

## Tech Stack

- Java 17
- Spring Boot 3.2.3
- Apache Kafka
- Maven (Multi-module)
- Docker (for Kafka infrastructure)
- SLF4J for logging

## API Endpoints

All endpoints accept GET requests with query parameters `a` and `b`.

`GET /api/v1/calculator/sum?a={number}&b={number}`
`GET /api/v1/calculator/subtract?a={number}&b={number}`
`GET /api/v1/calculator/multiply?a={number}&b={number}`
`GET /api/v1/calculator/divide?a={number}&b={number}`

### Example Requests

```bash
Addition
curl "http://localhost:8080/api/v1/calculator/sum?a=10.5&b=5.5"
Subtraction
curl "http://localhost:8080/api/v1/calculator/subtract?a=10.5&b=5.5"
Multiplication
curl "http://localhost:8080/api/v1/calculator/multiply?a=10.5&b=5.5"
Division
curl "http://localhost:8080/api/v1/calculator/divide?a=10.5&b=5.5"
```

### Example Response
```json
{
"result": 16.0
}
```

## Setup and Installation

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker Desktop (for Kafka)

### Running Locally

1. Clone the repository:

```bash
git clone https://github.com/YOUR_USERNAME/calculator-service.git
cd calculator-service
```

2. Start Kafka infrastructure (requires Docker):

```bash
docker-compose up -d
```

3. Build the project:

```bash
mvn clean install
```

4. Run the application:

```bash
cd rest
mvn spring-boot:run
```


The service will be available at `http://localhost:8080`

## Configuration

### Application Properties

Calculator Module (`calculator/src/main/resources/application.properties`):

```properties
spring.application.name=calculator-service
server.port=8081
```

REST Module (`rest/src/main/resources/application.properties`):

```properties
spring.application.name=calculator-rest
server.port=8080
```


### Kafka Configuration

Kafka is configured to run locally with the following settings:
- Bootstrap Servers: localhost:9092
- Topic: calculation-events
- Consumer Group: calculator-group

## Logging

Logs are written to both console and file:
- Console pattern: `%d{HH:mm:ss.SSS} [%thread] [%X{requestId}] %-5level %logger{36} - %msg%n`
- Log files location: `logs/calculator-service.log` and `logs/calculator-rest.log`

## To
Java Challenge_2024

## License

This project is licensed under the MIT License - see the LICENSE file for details.
