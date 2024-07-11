# Inventory Management Microservice

- This Java micro-server project demonstrates proficiency in microservices architecture and RESTful APIs for managing e-commerce inventory. It showcases the ability to create, retrieve, update, and delete inventory items using HTTP-based communication and data persistence.

## Table of Contents

- Features
- Requirements
- Getting Started
- API Endpoints
- Database
- Authentication
- Error Handling

## Features

- Entity Mapping: JPA entities for Supplier, Stock, Order, and OrderItem with defined relationships.
- Inventory Management API: CRUD operations for entities, stock management, and order management.
- Security: Implementation of Spring Security for restricted access and authentication mechanisms.
- Production-Ready Considerations: Error handling, validation, logging, and monitoring functionalities.
- Additional Functionalities: Low stock alerts, order status updates, and inventory reports.

## Requirements

- Java 17 or higher
- Maven 3.9 or higher
- PostgresSql 16 or higher

## Getting Started

1. Clone the repository:

- git clone https://github.com/LamNgo1911/fs17_java_inventory_service.git

2. Navigate to the project and set up application.properties:

- cd fs17_java_inventory_service/src/main/resources

```
  spring.application.name=inventory
  
  # Setup postgres
  spring.datasource.url=your database url
  spring.datasource.username=your database username
  spring.datasource.password=your database password
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
  
  logging.level.root=INFO
  logging.level.org.springframework.web=DEBUG
  logging.level.org.springframework.security=DEBUG
  
  logging.level.org.hibernate.SQL=DEBUG
  logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
  
  
  spring.config.import = env.properties
  server.port=8081
  
  # Email service
  spring.mail.host=smtp.gmail.com
  spring.mail.port=587
  spring.mail.username=your email address
  spring.mail.password=${EMAIL_PASSWORD}
  spring.mail.properties.mail.smtp.auth=true
  spring.mail.properties.mail.smtp.starttls.enable=true
  
  # Monitoring
  management.endpoints.web.exposure.include=*
  management.endpoint.health.show-details=always
```
3. Set up the environment variables (see Environment Variables).
- create .env file
```
  SECRET_API_KEY=your api key
  EMAIL_PASSWORD=your email password
```
4. Maven installation
```
  mvn clean install
 ```
5. Run the server and should now be running on http://localhost:8081

## API Endpoints

1. Suppliers

- POST /api/v1/suppliers: Create a new supplier.
![img.png](src/main/resources/assets/img.png)
- PUT /api/v1/suppliers/{id}: Update supplier.
![img_2.png](src/main/resources/assets/img_2.png)
- GET /api/v1/suppliers: Get all suppliers.
![img_1.png](src/main/resources/assets/img_1.png)
- GET /api/v1/suppliers/{id}: Get a single supplier.
![img_3.png](src/main/resources/assets/img_3.png)
- DELETE /api/v1/suppliers/{id}: Delete a supplier.
![img_4.png](src/main/resources/assets/img_4.png)
2. Stocks

- POST /api/v1/stocks: Create a new stock.
![img_5.png](src/main/resources/assets/img_5.png)
- PUT /api/v1/stocks/{id}: Update stock.
![img_6.png](src/main/resources/assets/img_6.png)
- GET /api/v1/suppliers: Get all stocks.
![img_7.png](src/main/resources/assets/img_7.png)
- GET /api/v1/stocks/{id}: Get a single stock.
![img_8.png](src/main/resources/assets/img_8.png)
- DELETE /api/v1/stocks/{id}: Delete a stock.
![img_9.png](src/main/resources/assets/img_9.png)
- GET /api/v1/stocks/product/{productId}: Get a stock by productId.
![img_10.png](src/main/resources/assets/img_10.png)
- GET /api/v1/stocks/supplier/{supplierId}: Get stocks by supplierId.
![img_11.png](src/main/resources/assets/img_11.png)
3. Orders

- POST /api/v1/orders: Create a new order.
![img_12.png](src/main/resources/assets/img_12.png)
- PUT /api/v1/orders/{id}/status: Update order status.
![img_13.png](src/main/resources/assets/img_13.png)
![img_14.png](src/main/resources/assets/img_14.png)
- GET /api/v1/orders: Get all orders.
![img_15.png](src/main/resources/assets/img_15.png)
- GET /api/v1/orders/{id}: Get a single order.
![img_16.png](src/main/resources/assets/img_16.png)
- DELETE /api/v1/orders/{id}: Delete an order.
![img_17.png](src/main/resources/assets/img_17.png)
4. Reports

- GET /api/v1/reports/sales: Generate daily sales report.
![img_19.png](src/main/resources/assets/img_19.png)

## Database

- This project uses PostgresSql as the database for storing data.

## Authentication

- Since this project was aiming at managing inventory without users, I only created Api key and set Role ADMIN for the routes to access to the endpoint.
- Make sure that I have Api key header and Api key value when you try to access to the endpoints.

## Error Handling

- The server handles errors using a centralized global error handler. Any errors thrown in the routes are caught and formatted into an error response entity with a field and error message.
![img_18.png](src/main/resources/assets/img_18.png)
