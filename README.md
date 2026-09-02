# inventory-service

Spring Boot REST service that tracks product stock levels. It is the practice codebase for the Kiro MCP lab.

See `docs/conventions.md` for the team coding conventions.

## Tech stack

Java 21, Spring Boot 3.2.x, PostgreSQL 15+, Maven, JUnit 5, Mockito.

## Running locally

```bash
docker run --name inventory-pg -e POSTGRES_PASSWORD=training -e POSTGRES_DB=inventory -p 5432:5432 -d postgres:15-alpine
export DB_PASSWORD=training
mvn spring-boot:run
```

The service starts on port 8081. Swagger UI is at `http://localhost:8081/swagger-ui.html`.

## Running the tests

```bash
mvn test
```

Unit tests need no database.
