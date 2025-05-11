# User Service

## Overview
The User Service is a core microservice in the Blood Bank Management System, responsible for managing user accounts, authentication, roles, and user-related operations.

## Features
- User registration and management
- Role-based access control
- User profile management
- Secure password storage with BCrypt
- Soft delete functionality for data recovery
- Comprehensive audit logging of user activities

## Technology Stack
- Java 21
- Spring Boot 3.4.5
- Spring Security
- Spring Data JPA
- Microsoft SQL Server
- Spring Cloud Netflix Eureka (Service Discovery)

## API Endpoints
The service exposes the following REST API endpoints:

### User Management
- `POST /api/users` - Create a new user
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/username/{username}` - Get user by username
- `GET /api/users/email/{email}` - Get user by email
- `GET /api/users` - List all users
- `GET /api/users/role/{role}` - Get users by role
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user (soft delete)

### Validation
- `GET /api/users/check/username/{username}` - Check if username exists
- `GET /api/users/check/email/{email}` - Check if email exists

## Database Schema
The User entity is persisted in a `users` table with the following structure:
- `id` : UUID Primary Key
- `username`: VARCHAR, unique
- `email`: VARCHAR, unique
- `password`: VARCHAR (BCrypt hash)
- `phone_number`: VARCHAR
- `role`: ENUM (DONOR, HOSPITAL_ADMIN, BLOODBANK_ADMIN, SYSTEM_ADMIN)
- `status`: ENUM (ACTIVE, INACTIVE)
- `is_deleted`: BOOLEAN DEFAULT FALSE
- `created_at`: DATETIME
- `updated_at`: DATETIME

## Architecture
- Built on a microservices architecture
- Communicates with the Audit Service for comprehensive activity logging
- Registers with Eureka for service discovery
- Provides secure REST APIs for user management

## How to Build and Run
1. Ensure you have Java 21 and Maven installed
2. Configure the database connection in application.properties
3. Build the service: `mvn clean package`
4. Run the service: `java -jar target/userservice-0.0.1-SNAPSHOT.jar`

## Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Validation
- Spring Cloud Netflix Eureka Client
- Microsoft SQL Server JDBC Driver
