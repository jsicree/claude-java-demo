# Getting Started

## Running the Application

### With Docker Compose (recommended)

Starts MySQL 8.4 and the app together:

```bash
docker compose up --build        # foreground (Ctrl-C to stop)
docker compose up --build -d     # detached
docker compose down              # stop and remove containers
docker compose down -v           # also wipe the MySQL data volume
```

### Locally (requires a running MySQL)

```bash
# Start MySQL separately, then:
./mvnw spring-boot:run
```

The app connects to `localhost:3306/claudedemo` by default (see `src/main/resources/application.properties`).

### Running Tests

Tests use an H2 in-memory database — no MySQL required:

```bash
./mvnw test
```

### Building a JAR

```bash
./mvnw package -DskipTests
java -jar target/claude-java-demo-0.0.1-SNAPSHOT.jar
```

---

## API Endpoints

### Products

| Method | Path                 | Description        |
|--------|----------------------|--------------------|
| `POST` | `/api/products`      | Create a product   |
| `GET`  | `/api/products`      | List all products  |
| `GET`  | `/api/products/{id}` | Get product by ID  |

### Customers

| Method | Path                  | Description           |
|--------|-----------------------|-----------------------|
| `POST` | `/api/customers`      | Register a customer   |
| `GET`  | `/api/customers`      | List all customers    |
| `GET`  | `/api/customers/{id}` | Get customer by ID    |

### Smoke Test

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Widget","price":9.99}'

curl http://localhost:8080/api/products
```

---

## Reference Documentation

* [Spring Boot 4.0 Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)
* [Spring Web MVC](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web.servlet)
* [Hibernate ORM](https://hibernate.org/orm/documentation/)
* [MySQL 8.4 Reference Manual](https://dev.mysql.com/doc/refman/8.4/en/)
* [Apache Maven Documentation](https://maven.apache.org/guides/index.html)

## Guides

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
