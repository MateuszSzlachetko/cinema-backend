[# Cinema Application Backend

Welcome to the backend repository of the Cinema Application! 

This README will guide you through setting up and running the app.

## Prerequisites

Make sure you have the following installed on your system before proceeding:

1. **Java Development Kit (JDK):** A compatible version of JDK (Java 17).
2. **Maven:** 
3. **Docker:** Docker needs to be installed for managing the application dependencies.
4. **WSL (Windows Subsystem for Linux):** If you're on Windows, it's recommended to have a WSL distro installed to support Docker Compose.

## Getting Started

Follow the steps below to set up and run the application:

### 1. Reload Maven Project

- Open the project in your IDE (preferably IntelliJ IDEA).
- Ensure that the Maven dependencies are correctly loaded by reloading the Maven project.

### 2. Start the Database

- Make sure Docker is running.
- Open a terminal and navigate to the `database` directory:

  ```bash
  cd database
  ```

- Run the following command to start the database:

  ```bash
  docker compose up -d
  ```

- This command will initialize and start the required database containers.

### 3. Run the Application

- Once the database is up and running, you can start the backend application.
- Go back to IntelliJ IDEA and click the "Run" icon to launch the application.
- Alternatively, you can use the following command if you prefer a terminal:

  ```bash
  mvn spring-boot:run
  ```

### 4. Verify Setup

- After starting the application, check the logs in the console to ensure that the application is running smoothly and is connected to the database.

## Additional Information

- **Configuration:** Configuration files for the application can be found in the `src/main/resources` directory.
- **Docker Compose:** The `compose.yaml` file in the `database` directory manages the database services.
