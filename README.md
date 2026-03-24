# claude-java-demo

A Spring Boot REST API built with **hexagonal (Ports & Adapters) architecture**. Manages Products and Customers via a clean domain model that is fully decoupled from the web and persistence layers.

Companion backend for [claude-react-demo](https://github.com/jsicree/claude-react-demo).

## Features

- RESTful API for Products and Customers (CRUD)
- Hexagonal architecture — domain layer has zero framework dependencies
- MySQL 8.4 persistence via Spring Data JPA
- H2 in-memory database for tests (no MySQL required to run tests)
- OpenAPI / Swagger UI at `/swagger-ui.html`
- Docker Compose setup for one-command startup

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 4.0 |
| ORM | Spring Data JPA (Hibernate) |
| Database | MySQL 8.4 (production) · H2 (tests) |
| Build | Maven (via `./mvnw`) |
| API Docs | springdoc-openapi 3.0.1 |
| Runtime | Docker + nginx |

## Quick Start

### Docker Compose (recommended)

```bash
git clone https://github.com/jsicree/claude-java-demo.git
cd claude-java-demo
docker compose up -d
```

- API: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

### Local (requires MySQL)

```bash
./mvnw spring-boot:run
```

Expects MySQL at `localhost:3306/claudedemo` with credentials `demo` / `demo`.

### Tests

```bash
./mvnw test
```

## API Reference

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/api/products` | Create a product |
| `GET` | `/api/products` | List all products |
| `GET` | `/api/products/{id}` | Get product by UUID |
| `DELETE` | `/api/products/{id}` | Delete a product |
| `POST` | `/api/customers` | Register a customer |
| `GET` | `/api/customers` | List all customers |
| `GET` | `/api/customers/{id}` | Get customer by UUID |
| `DELETE` | `/api/customers/{id}` | Delete a customer |

Full interactive docs available at `http://localhost:8080/swagger-ui.html` when the app is running.

## Documentation

- [HELP.md](HELP.md) — detailed setup, running options, and smoke tests
- [CLAUDE.md](CLAUDE.md) — architecture guide, conventions, and domain model reference for AI-assisted development

## Related Projects

- [claude-react-demo](https://github.com/jsicree/claude-react-demo) — React 18 SPA frontend for this API
