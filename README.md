# Patient Management Service

##Technical assessment

## Overview
The Patient Management Service is a RESTful backend application built using Spring Boot.
It provides full CRUD operations for managing patient data and exposes APIs that are consumed by a React frontend.

---

## Tech Stack
- Java 17
- Spring Boot 4.0
- Gradle
- PostgreSQL (AWS RDS)
- Spring Data JPA (Hibernate)
- Redis
- OpenAPI (Swagger)
- React

---

## Backend Setup

### Prerequisites
- Java 17
- Spring Boot 4.0
- Gradle
- PostgreSQL database (AWS RDS or local)
- Redis Server 8.0
  
> **Note:** Redis Server must be running before starting the application.

### Database Configuration
The application uses PostgreSQL for data persistence. Database connection details are configured in `application-dev.yml`.

> **Note:** Database credentials are not committed to the repository.

---

### Build the Application
./gradlew clean build

### Run the Application
java -jar build/libs/patient-management-service-1.0.jar

## API Documentation
http://localhost:8195/swagger-ui.html
