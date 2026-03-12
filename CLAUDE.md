# claude-java-demo

Spring Boot demo project exploring hexagonal architecture patterns with Java.

**GitHub:** https://github.com/jsicree/claude-java-demo

## Stack

- **Java** 17
- **Spring Boot** 4.0.0
- **Build** Maven (`./mvnw`)
- **Packaging** JAR

## Common commands

```bash
./mvnw spring-boot:run      # run the app (port 8080 by default)
./mvnw test                 # run tests
./mvnw package              # build fat JAR → target/
```

## Docker

```bash
docker build -t claude-java-demo .                    # build image (two-stage: JDK build → JRE run)
docker run -p 8080:8080 --name demo claude-java-demo  # run container on port 8080
docker stop demo                                      # stop by name
docker ps                                             # find container ID if no name given
```

## System context

```mermaid
C4Context
    title System Context — claude-java-demo

    Person(client, "API Consumer", "Any HTTP client: curl, browser, frontend app")

    System_Boundary(app, "claude-java-demo") {
        System(api, "REST API", "Spring Boot 4.0 · Java 17\nExposes /api/products and /api/customers")
        SystemDb(mem, "In-Memory Store", "ConcurrentHashMap\nNo persistence across restarts")
    }

    Rel(client, api, "HTTP/REST", "JSON · port 8080")
    Rel(api, mem, "reads / writes")
```

## Architecture

Hexagonal (Ports & Adapters). Dependency rule: outer layers depend on inner layers, never the reverse.

```
domain → (nothing)
application → domain
adapter → application, domain
```

### Package layout

```
com.example.claudejavademo/
├── domain/
│   ├── model/          # entities, aggregates, value objects
│   └── exception/      # domain-specific exceptions
├── application/
│   ├── port/
│   │   ├── in/         # input port interfaces (use cases)
│   │   └── out/        # output port interfaces (repositories, clients)
│   └── service/        # use-case implementations (package-private classes)
└── adapter/
    ├── in/
    │   └── web/        # REST controllers, request/response records
    └── out/
        └── persistence/ # repository implementations
```

### Conventions

- **Domain** classes have zero framework dependencies.
- **Use-case interfaces** live in `application.port.in`; one interface per use case.
- **Service classes** are package-private and implement one or more use-case interfaces.
- **Controllers and repositories** are package-private; Spring wires them via the port interfaces.
- **Request/Response models** are Java records, scoped to `adapter.in.web` — the domain never sees HTTP shapes.
- To swap persistence, implement `ProductRepository` (output port) in a new `adapter.out.*` class without touching any other layer.

### Javadoc

Every Java file must include:

1. **File-level Javadoc** placed before the `package` statement:
```java
/**
 * <One-sentence description of the class/interface/record.>
 *
 * @author Joe Sicree (test@test.com)
 * @since <YYYY-MM-DD of file creation>
 */
package com.example.claudejavademo...;
```

2. **Method-level Javadoc** on every explicit method (constructors, static factories, instance methods):
```java
/**
 * <One-sentence description of what the method does.>
 *
 * @param <name>  <description>
 * @return        <description of return value>
 * @throws <Type> <condition under which this is thrown>
 */
```
- Include `@param` for every parameter.
- Include `@return` for every non-void method.
- Include `@throws` for every checked or domain exception the method raises or propagates.
- Records with no explicit method bodies are exempt from method-level Javadoc.

## Current domains

| Domain | Entities | Input ports | Output ports |
|--------|----------|-------------|--------------|
| Product | `Product` | `CreateProductUseCase`, `GetProductUseCase` | `ProductRepository` |
| Customer | `Customer` | `RegisterCustomerUseCase`, `GetCustomerUseCase` | `CustomerRepository` |

## Persistence

Currently in-memory (`InMemoryProductRepository`, `InMemoryCustomerRepository`). No database configured yet.