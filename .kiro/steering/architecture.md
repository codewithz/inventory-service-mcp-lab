# Architecture Standards

## Stack
- Java 21, Spring Boot 3.2.x, PostgreSQL 15+, Maven
- JUnit 5, Mockito, Testcontainers
- SpringDoc OpenAPI for API documentation

## Package convention
`com.training.inventory` with sub-packages: `controller`, `service`, `repository`, `entity`, `dto`, `exception`, `config`.

## Rules
- Constructor injection only. Never `@Autowired` on fields.
- No secrets in committed `.properties` or `.yml` files. Use environment variables.
- Controllers return DTOs, never entities.
- Every new endpoint ships with unit tests.
