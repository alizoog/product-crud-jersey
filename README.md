# Product CRUD API

This project is a simple Product CRUD (Create, Read, Update, Delete) API built using Jersey and integrated with PostgreSQL. The repository name for this project is **product-crud-vue**.

## Features
- Create a new product
- Retrieve a list of products with pagination
- Update an existing product
- Delete a product

## Technologies Used
- Java 21
- Jersey (Jakarta RESTful Web Services)
- Hibernate ORM
- PostgreSQL
- Maven
- Lombok
- Tomcat

## Installation

### Prerequisites
- Java 21 or later
- Maven
- PostgreSQL
- Tomcat (for deployment)

### Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/product-crud-vue.git
   cd product-crud-vue
   ```
2. Configure the database in `persistence.xml`:
   ```properties
     <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/your_database_name"/>
     <property name="jakarta.persistence.jdbc.user" value="your_database_user"/>
     <property name="jakarta.persistence.jdbc.password" value="your_database_password"/>
   ```
3. Build the project:
   ```sh
   mvn clean package
   ```
4. Deploy the `war` file to Tomcat:
   - Copy the generated `.war` file from `target/` to Tomcat's `webapps/` directory.
   - Start Tomcat and access the API.

## API Endpoints

### 1. Get All Products (Paginated)
- **Endpoint:** `POST /products/list`
- **Query Parameters:**
  - `search` (optional) - Search term
  - `status` (optional) - Filter by product status
  - `page` - Page number
  - `size` - Number of items per page
- **Response:** List of products with pagination metadata

### 2. Create a Product
- **Endpoint:** `POST /products`
- **Request Body:**
  ```json
  {
    "name": "Product Name",
    "price": 100.0,
    "quantity": 10,
    "status": "ACTIVE"
  }
  ```
- **Response:** Created product details

### 3. Update a Product
- **Endpoint:** `PUT /products/{id}`
- **Request Body:** Same as create request
- **Response:** Updated product details

### 4. Delete a Product
- **Endpoint:** `DELETE /products/{id}`
- **Response:** "Product deleted"

## License
This project is open-source and available for use.

---
For any issues or contributions, feel free to create a pull request or raise an issue in the repository.

