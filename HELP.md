# Getting Started

## Running the Application

The app serves a React SPA at `http://localhost:8080/` and the REST API at `http://localhost:8080/api/`.

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

### Frontend Development

Run the Vite dev server (hot reload, proxies `/api` → `localhost:8080`):

```bash
cd frontend
npm install      # first time only
npm run dev      # http://localhost:5173
```

Build the frontend into `src/main/resources/static` (Maven does this automatically during `package`):

```bash
cd frontend && npm run build
```

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

| Method   | Path                 | Description        | Status |
|----------|----------------------|--------------------|--------|
| `POST`   | `/api/products`      | Create a product   | 201    |
| `GET`    | `/api/products`      | List all products  | 200    |
| `GET`    | `/api/products/{id}` | Get product by ID  | 200    |
| `DELETE` | `/api/products/{id}` | Delete a product   | 204    |

### Customers

| Method   | Path                  | Description           | Status |
|----------|-----------------------|-----------------------|--------|
| `POST`   | `/api/customers`      | Register a customer   | 201    |
| `GET`    | `/api/customers`      | List all customers    | 200    |
| `GET`    | `/api/customers/{id}` | Get customer by ID    | 200    |
| `DELETE` | `/api/customers/{id}` | Delete a customer     | 204    |

### Smoke Test

```bash
# Create a product
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Widget","price":9.99}'

# List products
curl http://localhost:8080/api/products

# Delete a product (replace <id> with the UUID returned above)
curl -X DELETE http://localhost:8080/api/products/<id>
```

---

## Reference Documentation

* [Spring Boot 4.0 Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)
* [Spring Web MVC](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web.servlet)
* [Hibernate ORM](https://hibernate.org/orm/documentation/)
* [MySQL 8.4 Reference Manual](https://dev.mysql.com/doc/refman/8.4/en/)
* [Apache Maven Documentation](https://maven.apache.org/guides/index.html)
* [React 18 Documentation](https://react.dev/)
* [Vite Documentation](https://vitejs.dev/guide/)

## Guides

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
