# Pavuk

A full-stack application that connects customers with tailoring service providers. The backend is powered by **Java Spring Boot** with **Oracle Database**, while the frontend is built using **React**.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Technologies Used](#technologies-used)
3. [Installation](#installation)
4. [Running the Application](#running-the-application)
5. [Features](#features)
6. [Javadoc](#javadoc)

## Project Overview

**Pavuk** is a simple and convenient web application that connects customers with tailors. Customers can easily find tailors, order services, and track their orders. Tailors can accept and manage these orders with ease.

The purpose of this project is to make it easier for clients and tailors to work together by providing a straightforward platform for ordering and managing tailoring services.

## Technologies Used

### Backend:

- **Java**
- **Spring Boot**
- **Oracle Database**
- **Hibernate** 
- **JWT Authentication** and **SSL certificates** for security

### Frontend:

- **React**
- **TailwindCSS**

## Installation

### Prerequisites:

- Java 23
- Node.js
- Maven
- Oracle Database

### Installation Steps:

#### Backend Setup:

1. Clone the repository:
   ```bash
   git clone https://github.com/IvannaMo/pavuk-java
   cd ../Backend/pavuk
   ```
2. Build the backend using Maven:
   ```bash
   mvn clean install
   ```
3. Set up environmental variables:
   Create a .env file in the resources folder and fill it with following information:
   - DB_USERNAME = <your_db_username>
   - DB_PASSWORD = <your_db_password>
   - JWT_SECRET = <jwt_secret_key>
   - JWT_EXPIRATION_MS = <jwt_expiration_ms>

#### Frontend Setup:

1. Navigate to the frontend directory:
   ```bash
   cd ../Frontend/pavuk
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Set up environmental variables:
    Create a .env file in the root folder and fill it with following information:
   - VITE_SERVER_PATH = <your_server_path>

**You're all set and can run the applications now!**

## Running the Application

1. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

2. Start the React application:
   ```bash
   npm run dev
   ```

## Features

- **User Security**: Secure login and registration with JWT.
- **Personal Account**: Users can view their personal information, edit it and track their orders.
- **Ordering**: Customers can choose from the available clothing templates, edit the template to their liking and place an order for an experienced tailor to pick up.
- **Admin Rights**: As an admin, you have rights to view all users, orders and clothing items.

## Javadoc

- For a more detailed insight into classes and methods you can see: https://ivannamo.github.io/pavuk-java/

