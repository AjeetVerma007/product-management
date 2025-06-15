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

## Testing the Application

### Running All Tests

To run all tests (unit tests and integration tests):
```bash
./mvnw test
```

### Running Tests in Development Mode

To run tests continuously while developing:
```bash
./mvnw quarkus:test
```

### Running Specific Test Classes

To run a specific test class:
```bash
./mvnw test -Dtest=ProductResourceTest
```

### Integration Testing

The application includes integration tests that test the full API endpoints. Make sure the application is running before running integration tests manually.

### Manual API Testing

#### 1. Start the Application
```bash
./mvnw quarkus:dev
```

#### 2. Test Product Creation
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Product",
    "description": "A test product for validation",
    "price": 29.99,
    "quantity": 50
  }'
```

#### 3. Test Getting All Products
```bash
curl -X GET http://localhost:8080/api/products
```

#### 4. Test Getting Products Sorted by Price
```bash
curl -X GET http://localhost:8080/api/products/sorted-by-price
```

#### 5. Test Getting a Specific Product
```bash
curl -X GET http://localhost:8080/api/products/1
```

#### 6. Test Stock Availability
```bash
curl -X GET http://localhost:8080/api/products/1/check-stock/10
```

#### 7. Test Product Update
```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Product",
    "description": "Updated description",
    "price": 39.99,
    "quantity": 75
  }'
```

#### 8. Test Product Deletion
```bash
curl -X DELETE http://localhost:8080/api/products/1
```

### Testing with Swagger UI

1. Start the application in development mode
2. Open your browser and navigate to: `http://localhost:8080/swagger-ui`
3. Use the interactive UI to test all API endpoints

### Test Data Setup

For comprehensive testing, you can create multiple products:
```bash
# Product 1
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Laptop", "description": "Gaming laptop", "price": 1299.99, "quantity": 15}'

# Product 2
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Mouse", "description": "Wireless mouse", "price": 29.99, "quantity": 100}'

# Product 3
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Keyboard", "description": "Mechanical keyboard", "price": 89.99, "quantity": 50}'
```

### Expected Test Results

- **Product Creation**: Should return status 200 and the created product with an assigned ID
- **Get All Products**: Should return status 200 and an array of all products
- **Get Product by ID**: Should return status 200 and the specific product, or 404 if not found
- **Stock Check**: Should return `true` if requested quantity is available, `false` otherwise
- **Product Update**: Should return status 200 and the updated product
- **Product Deletion**: Should return status 204 (No Content)
- **Sorted by Price**: Should return products ordered by price (ascending)

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