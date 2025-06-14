# Product Management API

This is a reactive REST API built with Quarkus for managing products. The application provides CRUD operations for products and additional features like stock availability checking and price-based sorting.

## Prerequisites

- Java 17 or later
- Maven 3.8.1 or later
- MySQL 8.0 or later
- Docker (optional)

## Database Setup

1. Install MySQL if not already installed
2. Create a new database:
```sql
CREATE DATABASE productdb;
```

3. Configure MySQL credentials in `src/main/resources/application.properties`:
```properties
quarkus.datasource.username=root
quarkus.datasource.password=root
```

## Running the Application

### Development Mode

```bash
./mvnw quarkus:dev
```

This will start the application in development mode with live coding enabled.

### Production Mode

1. Build the application:
```bash
./mvnw package
```

2. Run the application:
```bash
java -jar target/quarkus-app/quarkus-run.jar
```

## API Endpoints

- `POST /api/products` - Create a new product
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get a product by ID
- `PUT /api/products/{id}` - Update a product
- `DELETE /api/products/{id}` - Delete a product
- `GET /api/products/sorted-by-price` - Get all products sorted by price
- `GET /api/products/{id}/check-stock/{count}` - Check product stock availability

## API Documentation

Once the application is running, you can access:
- OpenAPI specification: `http://localhost:8080/openapi`
- Swagger UI: `http://localhost:8080/swagger-ui`

## Running Tests

```bash
./mvnw test
```

## Sample Request

Create a new product:
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Sample Product",
    "description": "A sample product description",
    "price": 99.99,
    "quantity": 100
  }'
```

## Docker Support

1. Build the Docker image:
```bash
./mvnw package -Dquarkus.container-image.build=true
```

2. Run with Docker:
```bash
docker run -i --rm -p 8080:8080 quarkus/product-management
```

## Features

- Reactive REST API using Quarkus
- MySQL database with Hibernate Reactive
- OpenAPI documentation
- Comprehensive test suite
- Input validation
- Stock availability checking
- Price-based sorting
- Docker support

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/acme/product/
│   │       ├── entity/
│   │       │   └── Product.java
│   │       ├── resource/
│   │       │   └── ProductResource.java
│   │       └── service/
│   │           └── ProductService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── org/acme/product/
            └── resource/
                └── ProductResourceTest.java
``` 