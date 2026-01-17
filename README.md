# Patient Management Service

##Technical assessment

## Description
This project is a Patient Management REST application built using Spring Boot. It provides CRUD operations for patient data and exposes RESTful APIs consumed by a React frontend.

## Description
This project is a Patient Management REST application built using Spring Boot. It provides CRUD operations for patient data and exposes RESTful APIs consumed by a React frontend.

---

## Tech Stack
- Java 17
- Spring Boot 4.0
- Gradle
- PostgreSQL (AWS RDS)
- Spring Data JPA (Hibernate)
- OpenAPI (Swagger)
- React

---

## Backend Setup

### Prerequisites
- Java 17
- Gradle
- PostgreSQL database (AWS RDS or local)

### Database Configuration
The application uses PostgreSQL for data persistence. Database connection details are configured in `application.properties`.

> **Note:** Database credentials are not committed to the repository.

---

### Build the Application
./gradlew clean build

### Run the Application
java -jar build/libs/patient-management-service-1.0.jar

## API Documentation
http://localhost:8195/swagger-ui.html





