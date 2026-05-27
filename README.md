# Employee Management System

A production-ready RESTful API built with **Java 17** and **Spring Boot** for managing Employees and Departments. Features full CRUD operations, One-to-Many JPA relationships, pagination, salary-based filtering, and global exception handling.

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Primary language |
| Spring Boot 4.0.6 | Application framework |
| Spring Data JPA | ORM and database abstraction |
| Hibernate | JPA implementation |
| H2 Database | In-memory database (default) |
| MySQL | Production database |
| Lombok | Boilerplate reduction |
| Maven | Build and dependency management |
| Postman | API testing |

---

## Project Structure

```
src/
└── main/
    └── java/com/example/employee_management_system/
        ├── controller/
        │   ├── EmployeeController.java     # REST endpoints for Employee
        │   └── DepartmentController.java   # REST endpoints for Department
        ├── service/
        │   ├── EmployeeService.java        # Business logic for Employee
        │   └── DepartmentService.java      # Business logic for Department
        ├── repository/
        │   ├── EmployeeRepository.java     # JPA queries including salary filter
        │   └── DepartmentRepository.java   # Department data access
        ├── entity/
        │   ├── Employee.java               # Employee JPA entity
        │   └── Department.java             # Department JPA entity (One-to-Many)
        ├── dto/
        │   ├── EmployeeDTO.java            # Request body for Employee
        │   └── DepartmentDTO.java          # Request body for Department
        ├── exception/
        │   ├── GlobalExceptionHandler.java # Centralized exception handling
        │   ├── ResourceNotFoundException.java
        │   └── ErrorResponse.java          # Structured error response
        └── EmployeeManagementSystemApplication.java
```

---

## Setup & Run

### Prerequisites
- Java 17 or higher
- Maven 3.8+

### Run (H2 In-Memory — default, no setup needed)

```bash
git clone https://github.com/darshanamiraje01/employee-management-system.git
cd employee-management-system
mvn spring-boot:run
```

Server starts at: `http://localhost:8080`

### Switch to MySQL (optional)

Add these to `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

Then create the database in MySQL:
```sql
CREATE DATABASE employee_db;
```

---

## API Endpoints

### Department Endpoints — `/api/departments`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/departments` | Create a new department |
| GET | `/api/departments` | Get all departments |
| GET | `/api/departments/{id}` | Get department by ID |
| DELETE | `/api/departments/{id}` | Delete department |

### Employee Endpoints — `/api/employees`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employees` | Create a new employee |
| GET | `/api/employees` | Get all employees (paginated) |
| GET | `/api/employees/{id}` | Get employee by ID |
| GET | `/api/employees/filter?minSalary= | Filter employees by minimum salary |
| DELETE | `/api/employees/{id}` | Delete employee |

---

## Sample Postman Flow

### Step 1 — Create a Department first
```
POST http://localhost:8080/api/departments
Content-Type: application/json

{
    "name": "Engineering",
    "location": "Pune"
}
```
Response: Department created with `id: 1`

---

### Step 2 — Create an Employee under that Department
```
POST http://localhost:8080/api/employees
Content-Type: application/json

{
    "name": "Darshana Mirje",
    "email": "darshana@company.com",
    "salary": 75000,
    "designation": "Software Engineer",
    "departmentId": 1
}
```

---

### Step 3 — Get all Employees with Pagination
```
GET http://localhost:8080/api/employees?page=0&size=5&sortBy=salary
```
Returns paginated result sorted by salary.

---

### Step 4 — Filter Employees by Salary
```
GET http://localhost:8080/api/employees/filter?minSalary=50000
```
Returns all employees earning more than ₹50,000.

---

### Step 5 — Get Employee by ID
```
GET http://localhost:8080/api/employees/1
```

---

### Step 6 — Delete Employee
```
DELETE http://localhost:8080/api/employees/1
```

---

## Key Features Explained

### One-to-Many Relationship
One Department contains many Employees. Managed via JPA:
- `Department` → `@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)`
- `Employee` → `@ManyToOne @JoinColumn(name = "department_id")`
- Circular JSON serialization handled with `@JsonManagedReference` and `@JsonBackReference`

### Pagination and Sorting
```
GET /api/employees?page=0&size=5&sortBy=salary
```
Implemented using Spring Data `PageRequest.of(page, size, Sort.by(sortBy))` — returns a `Page<Employee>` object containing data, total elements, total pages, and current page info.

### Salary Filter
```
GET /api/employees/filter?minSalary=60000
```
Uses a custom Spring Data JPA derived query method:
```java
List<Employee> findBySalaryGreaterThan(Double salary);
```
No manual SQL needed — Spring generates the query from the method name.

### Global Exception Handling
All exceptions are handled centrally by `GlobalExceptionHandler` using `@ControllerAdvice`:
- `ResourceNotFoundException` → 404 Not Found
- `MethodArgumentNotValidException` → 400 Bad Request with validation message

Structured error response:
```json
{
    "message": "Employee not found",
    "status": 404,
    "timestamp": "2026-05-26T10:00:00"
}
```

### DTO Pattern
Request bodies use DTOs (Data Transfer Objects) instead of raw entities:
- Decouples API layer from database layer
- Allows input validation with Jakarta annotations
- Prevents exposing internal JPA fields to API consumers

---

## Error Responses

| Scenario | Status | Message |
|---|---|---|
| Employee/Department not found | 404 | "Employee not found" |
| Invalid request body | 400 | Validation error message |

---

## Author

**Darshana Mirje**
- GitHub: [github.com/darshanamiraje01](https://github.com/darshanamiraje01)
- LinkedIn: [linkedin.com/in/darshana-mirje](https://linkedin.com/in/darshana-mirje)