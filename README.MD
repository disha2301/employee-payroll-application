# 🚀 Employee Payroll Application

Built with **Spring Boot, JPA, and H2** - for seamless and efficient payroll management. This Employee Management System is a RESTful web application built with Spring Boot. It enables CRUD operations on employee data with robust validation, exception handling, and logging. The project leverages JPA with ORM for database interactions and uses H2 as an in-memory database for development and testing. DTO patterns ensure clean API contracts and security by decoupling entity models from API payloads
## 🏗️Folder Structure:
```
src/main/java
└── com.gevernova.employee
├── controller
│ └── EmployeeController.java
├── service
│ └── EmployeeService.java
├── entity
│ └── Employee.java
├── dto
│ ├── EmployeeRequestDTO.java
│ └── EmployeeResponseDTO.java
├── repository
│ └── EmployeeRepository.java
└── exception
└── GlobalExceptionHandler.java
```
---
## 📖 What is Spring Boot?
**Spring Boot** is a Java-based framework used to create stand-alone, production-ready Spring applications with minimal configuration.  
It simplifies the development of REST APIs and integrates well with JPA, validation libraries, and more.

Key Features:
- Embedded servers (Tomcat)
- Auto-configuration
- Starter dependencies
- Production-ready metrics

---
## 🚀 Project Overview

This is a full-featured **Employee Management REST API** that supports:

- Create employee (`POST`)
- Read employee (`GET`)
- Update employee (`PUT`)
- Delete employee (`DELETE`)

The project uses:
- In-memory H2 database
- DTO pattern for separation of concerns
- Lombok to reduce boilerplate
- Jakarta Validation for request data
- Global Exception Handling
- Custom Logs for service flow tracking

---
## 🧱 Technologies Used

| 🧰 Technology         | 📝 Description                                 |
|-----------------------|-----------------------------------------------|
| **Spring Boot**       | Backend framework for rapid development       |
| **Spring Web**        | To build robust RESTful APIs                  |
| **Spring Data JPA**   | ORM tool to interact with relational DBs      |
| **H2 Database**       | Lightweight in-memory DB for dev/testing      |
| **Lombok**            | Reduces boilerplate via annotations           |
| **Jakarta Validation**| Validates request data using annotations      |
| **SLF4J + Logback**   | Logging API and implementation                |
| **Maven**             | Project build, dependency, and lifecycle tool |

---
## 🧪 How to Run

Follow these steps to set up and run the project locally:

### 🔧 Prerequisites
- Java 17 or above installed
- Maven installed
- IDE like IntelliJ or VS Code (optional but recommended)

### 🚀 Steps to Run

```bash
# Clone the repository
git clone https://github.com/yourusername/employee-management.git

# Navigate into the project directory
cd employee-management

# Run the application using Maven
mvn spring-boot:run
# Clone the repository
git clone https://github.com/yourusername/employee-management.git

# Navigate into the project directory
cd employee-management

# Run the application using Maven
mvn spring-boot:run
```
## 🔗 API Endpoints

| Method  | Endpoint           | Description             |
|---------|--------------------|-------------------------|
| `POST`  | `/employees`       | Create a new employee   |
| `GET`   | `/employees/{id}`  | Get employee by ID      |
| `PUT`   | `/employees/{id}`  | Update employee by ID   |
| `DELETE`| `/employees/{id}`  | Delete employee by ID   |

### Example POST Request Body

```json
{
  "name": "Disha",
  "department": "SDE",
  "salary": 5000,
  "password": "Disha@1234",
  "gender": "FEMALE",
  "dob": "2000-01-01",
  "joinDate": "2025-06-01",
  "skills": ["Springboot", "Cloud Computing"]
}
```
### 🛠 Annotations Used

| Annotation                  | Description                                  |
|-----------------------------|----------------------------------------------|
| `@RestController`           | Marks the class as a REST controller         |
| `@RequestMapping`           | Base URI mapping for the controller          |
| `@PostMapping`, `@GetMapping` | Maps HTTP methods to controller methods    |
| `@Valid`                    | Triggers validation on incoming request body |
| `@RequestBody`              | Binds HTTP request body to Java object       |
| `@PathVariable`             | Extracts variable from URL                    |
| `@Entity`                   | Specifies a JPA entity                        |
| `@Id`, `@GeneratedValue`    | Marks primary key and generation strategy    |
| `@Builder`, `@Data`         | Lombok annotations to reduce boilerplate     |
| `@Service`                  | Marks business logic layer                    |
| `@Autowired` or constructor | Used for dependency injection                 |
| `@NotBlank`, `@Pattern`     | Validates request fields                       |
| `@ExceptionHandler`         | Handles specific exceptions globally          |
---
### 🗃 H2 Database

- In-memory relational database ideal for development and testing.
- Accessible UI console at: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User:** `sa`
- **Password:** *(blank)*

---

### 📊 JPA & ORM

**What is JPA?**  
Java Persistence API (JPA) is a specification for managing relational data in Java applications using ORM.

**What is ORM?**  
Object-Relational Mapping (ORM) automatically maps Java objects to database tables, simplifying database operations by reducing boilerplate SQL code.

---
### 📦 Lombok

Lombok helps reduce boilerplate code such as getters, setters, constructors, and more.

**Commonly used annotations:**

| Annotation             | Description                                              |
|-----------------------|----------------------------------------------------------|
| `@Data`               | Generates getters, setters, `toString()`, `equals()`, and `hashCode()` methods |
| `@Builder`            | Implements the builder pattern for easy object creation  |
| `@NoArgsConstructor`  | Generates a no-argument constructor                      |
| `@AllArgsConstructor` | Generates a constructor with arguments for all fields   |

---
### 📥 RequestEntity and DTO Pattern

**DTOs (Data Transfer Objects)** decouple the entity model from the API layer to promote cleaner architecture.

- **RequestDTO:** Used to receive and validate user input.
- **ResponseDTO:** Controls the data returned to the client, hiding sensitive fields and enabling flexible mappings.
---
### ✅ Validations

Using Jakarta Bean Validation with `@Valid` and constraint annotations:

| Field     | Validation                             |
|-----------|--------------------------------------|
| `name`    | `@NotBlank`, `@Pattern`               |
| `salary`  | `@NotNull`, `@Min(1000)`              |
| `dob`     | `@Past`                              |
| `joinDate`| `@FutureOrPresent`                   |
| `skills`  | `@NotEmpty`                          |
| `gender`  | `@Pattern` (e.g., "MALE", "FEMALE")  |
| `password`| `@Pattern` (strong password format)  |

Invalid requests return **400 Bad Request** with field-specific error messages.

---

### 🧾 Exception Handling

Handled globally using `@ControllerAdvice` and `@ExceptionHandler` for:

- Entity not found
- Invalid input
- Database errors

**Example error response:**

```json
{
  "timestamp": "2025-06-04T10:00:00",
  "status": 400,
  "errors": [
    "Name is required",
    "Salary must be at least 1000"
  ]
}
```
---

### ✉️ JMS (Java Message Service) Integration

This Spring Boot application includes optional support for **JMS-based asynchronous messaging** to handle tasks such as sending emails. However, in the current version, email is sent **directly** using `JavaMailSender` without a JMS broker.

### ❓ What is JMS?

JMS (Java Message Service) is a Java API for sending messages between two or more clients. It's useful for:

- Decoupling components of a system
- Handling background tasks (like sending notifications or reports)
- Ensuring reliable message delivery via queues or topics

### ⚙️ Current State in This Project

✅ **Used:**
- `JavaMailSender` for direct email delivery on employee creation


### 📦 Dependencies Used

```xml
<!-- JavaMail for sending emails -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

        <!-- JMS support (optional – can be removed if not using) -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

### ✉️ Email Sending Flow

- When a new employee is created via `POST /employees`, the following occurs:
    1. Employee is saved to the database
    2. A **welcome email** is sent to their registered email ID using Gmail SMTP

### 🔐 Gmail SMTP Setup

To enable email sending from your Gmail account, follow these steps:

1. Enable **2-Step Verification** in your Google account
2. Generate an **App Password**: [https://myaccount.google.com/apppasswords](https://myaccount.google.com/apppasswords)
3. Use that app password in your `application.properties` like below:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

### 🧾 Logging

Logging implemented using SLF4J with Logback:

```bash
log.info("Fetching employee with ID: {}", id);
log.error("Employee not found with ID: {}", id);
log.debug("Saved employee: {}", employee);
```
## 🙋‍♀️ Author

**Disha**  
Backend Developer | Spring Boot | REST APIs | Java


