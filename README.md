# MyBudget

This is a full-stack web application built with Spring Boot for the Backend Side and with Angular on the Frontend Side.
The application provides features such as account management, transactions, and currency conversion.


**Technologies Used**

**Backend:**

  - Spring Boot
  
  - Spring Web
  
  - Spring Data JPA
  
  - Spring Data JDBC
  
  - MySQL
  
  - Maven
  
  - Lombok


**Frontend:**

  - Angular

  - TypeScript
  
  - Angular Material
  
  - CSS


**Getting Started**

  Prerequisites
  Java 17
  Node.js (v18) & npm
  Angular CLI (v17)
  MySQL or any other relational database (for production)


**Backend Setup**

**Clone the repository**

  git clone https://github.com/MagdalenaS18/RBCProject.git

  cd RBCProject/rbc
  

**Configure the database:**

    Update the application.properties or application.yml file with your database configuration.
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update


**Build the project:**
  
    mvn clean install
The backend runs on 'http://localhost:8080'.


**Frontend Setup**

Install dependencies:

    npm install
The frontend runs on 'http://localhost:4200'.


**Run the project**

Run the backend:
  
    mvn spring-boot:run

Run the frontend:
    
    ng serve


**API Endpoints**

Here is a list of API endpoints:

Account Controller:

  - GET /api/accounts - Retrieve all accounts
  
  - GET /api/accounts/{accountId} - Retrieve an account by it's ID
  
  - POST /api/accounts - Create a new account
  
  - DELETE /api/accounts - Delete all accounts

Transaction Controller:

  - GET /api/transactions - Retrieve all transactions
  
  - GET /api/transactions/account/{accountId} - Retrieve all transactions made by one account (by account's ID)
  
  - POST /api/transactions - Create a new transaction

  
Settings Controller:

  - GET /api/settings/default-currency - Get the default currency
  - PATCH /api/settings/default-currency - Update the default currency
  - GET /api/settings/default-currency/date - Get the date of conversion
  - GET /api/settings/default-currency/rates - Get the conversion rates
