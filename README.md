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

  Prerequisites:
  JDK 17, 
  Maven, 
  IntelliJ IDEA Community Edition, 
  Node.js (v18) & npm, 
  Angular CLI (v17), 
  MySQL or any other relational database (for production)


**Backend Setup**

**Clone the repository**

  git clone https://github.com/MagdalenaS18/RBCProject.git

  cd RBCProject/rbc

**Import the Project into IntelliJ IDEA**

  - Open IntelliJ IDEA
  - Click on `File > Open....`
  - Navigate to the cloned repository and select the project's root folder (where the pom.xml file is located).

**Configure the Project SDK**

  - Go to File > Project Structure.
  - Under Project Settings > Project, ensure the Project SDK is set (or the version of Java installed).
  - If the SDK is not listed, click New > JDK, and navigate to the installation path of your JDK.

**Build the Project**

  - Open the Maven tool window on the right side of IntelliJ IDEA.
  - Under the Lifecycle section, double-click on clean and then install.
  - Maven will download all dependencies and build the project.

**Configure the database:**

    Update the application.properties or application.yml file with your database configuration.
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    server.error.include-message=always

**Running the Application**

  - In the Project tool window, locate the main class (YourMainApplication.java).
  - Right-click on the main class and select Run 'YourMainApplication'.
  - The application will start, and you can monitor the logs in the Run/Debug window.

The backend runs on 'http://localhost:8080'.

After running the backend application, run the code from `accountmysql.txt` in MySQL and then run the code from `transactionmysql.txt`.


**Frontend Setup**

Install dependencies:

    npm install

Run the frontend:
    
    ng serve

The frontend runs on 'http://localhost:4200'.

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
