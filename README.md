# Identity REST API

This project is a REST API developed to manage user roles and details. It follows a monolithic architecture and provides various environments: dev, qa, staging, and prod. It supports database migration using Flyway.

## Setup

To get started with the project, follow these steps:

### Prerequisites

- Java 21
- Maven
- PostgreSQL

### Installation

1. Clone the repository: `git clone https://github.com/jjohxx/identity.git`
2. Navigate to the project directory: `cd identity`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

## Database

The project includes four tables mapped to entities: users, roles, user_roles, and user_detail. The database migration is handled through Flyway.

## Structure

The project implements Repositories, Services, Controllers, and Mappers. DTOs are used for data transfer to the client, ensuring clean separation of concerns.

### Features

- **CRUD Operations:** Implements Create, Read (list all users and get by user ID), Update, and Delete functionalities for the 'role' entity.
- **User Listing:** Allows users to be listed in both detailed and normal views.
- **Create User:** Supports creating a user where clients can provide basic user information or detailed user data in a single request.
- **Edit and Delete User:** Allows editing and deleting user data.
- **Role Assignment:** Enables clients to assign one or multiple roles to a user. Roles can be selected during user creation or at a later time.
- **User-Role Management:** Provides functionality to deactivate or make a role-user relationship inactive (PATCH).
- **Transactions:** Utilizes transactions, especially in user-related operations.

### Optional Features

- **Exception Handling:** Implemented exception handling for robust error management.
- **Backend Validations:** Implements server-side validations for data consistency.
- **Additional Tables and Services:** Can easily incorporate more tables and services as needed.
- **Security with Spring Boot:** Potential to implement security features using Spring Boot's security modules.
- **Role-based User Listing:** Provides an API that lists all users with a specific role.

## Usage

The API documentation and endpoints can be found in the Swagger UI provided by this project.

## Contributors

- [Jhonatan Mamani](https://github.com/jjohxx)

Feel free to contribute or raise issues for improvements.

---

*Note: Modify the installation steps and features according to the actual implementation of your project.*