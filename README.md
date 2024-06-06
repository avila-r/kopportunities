# Kopportunities

A RESTful API built-in Kotlin at Spring.

## Prerequisites

**Docker:**
  - Docker Engine (Linux) or Docker Desktop (Windows/MacOS)
  - [JDK 21](https://www.oracle.com/br/java/technologies/downloads/)
  - [Kotlin](https://kotlinlang.org/)
  - [Gradle](https://gradle.org/)
  - [Spring Boot](https://spring.io/projects/spring-boot)
  - [Hibernate](https://hibernate.org/)
  - [PostgreSQL](https://www.postgresql.org/) or your preferred database

## Configuration

**1.** Clone the repository:
```bash
git clone https://github.com/avila-r/kopportunities.git && cd kopportunities
```

**2.** Config properties:
  - [Docker Compose](https://github.com/avila-r/kopportunities/blob/main/docker-compose.yml)
  - [Application's properties](https://github.com/avila-r/kopportunities/blob/main/src/main/resources/application.yml)
  - [Postgre's Dockerfile](https://github.com/avila-r/kopportunities/blob/main/src/main/resources/datasource/Dockerfile)
  - [Postgre's Schema](https://github.com/avila-r/kopportunities/blob/main/src/main/resources/datasource/schema.sql)

**3.** Run docker compose:
```bash
docker compose up
```

## Endpoints

All schemas and endpoints are mapped by OpenAPI 3.0 (including Swagger UI integration). Just run the application and check `/docs` or `/docs.html` to list and test endpoints.

**OBS:** After testing, you can clean app-related images and containers with `docker compose down -v -rmi all`