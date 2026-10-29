# Flight Planning System

## Abstract

The **Flight Planning System** is a robust, modular, and user-friendly desktop application developed using Java Swing and JDBC to streamline flight scheduling, passenger record management, and ticket booking tracking. 
The software allows users to navigate seamlessly across structured GUI frames, validate form inputs in real time, and persist transaction records securely into a MySQL database.

Designed to address the limitations of manual record-keeping, this project emphasizes clean software architecture, modular encapsulation, and core Object-Oriented Programming (OOP) paradigms such as inheritance, event-driven execution, and database connectivity.

## Key Features

* **Interactive GUI Dashboard:** Intuitive navigation between different operational modules.
* **Passenger Management:** Register and store passenger details with strict age and input validation.
* **Flight Route Scheduling:** Record flight numbers, departure/destination cities, and flight dates (prevents matching departure and destination inputs).
* **Advanced Flight Booking:** Book tickets and automatically calculate total fares, including a dynamic luggage surcharge (Free up to 15 kg; extra weight charged at ₹500/kg).
* **Travel History Log:** View dynamic, real-time logs of scheduled flights fetched directly from the MySQL database into a read-only `JTable`.

## Technologies Used

* **Programming Language:** Java (JDK 8 or higher)
* **GUI Framework:** Java Swing (`JFrame`, `JPanel`, `JButton`, `JTextField`, `JTable`, etc.)
* **Layout Managers:** `BorderLayout`, `GridLayout`, `FlowLayout`
* **Database:** MySQL Server
* **Database Connectivity:** JDBC (MySQL Connector/J Driver)

## Database Setup

1. Ensure MySQL Server is installed and running.
2. Open your MySQL Command Line Client or MySQL Workbench.
3. Execute the following SQL script to create the necessary database and tables:

```sql
CREATE DATABASE IF NOT EXISTS flight_db;
USE flight_db;

CREATE TABLE IF NOT EXISTS passengers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS flights (
    flight_no VARCHAR(50) PRIMARY KEY,
    departure VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    flight_date VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    passenger VARCHAR(100) NOT NULL,
    flight VARCHAR(50) NOT NULL,
    travel_class VARCHAR(50) NOT NULL,
    fare DOUBLE NOT NULL
);
```

## How to Run the Application

### Prerequisites

* Install Java Development Kit (JDK).
* Install MySQL Server.
* Download the **MySQL Connector/J** `.jar` file and place it in your root project directory (alongside the `flightmodules` folder).

### Compilation and Execution

Open Windows PowerShell or Command Prompt in your project directory and run the following commands:

1. **Compile the Java files:**
   ```powershell
   javac -cp ".;*" -d . *.java
   ```
   *(If you combined all classes into one file, replace `*.java` with `Loginframe.java`)*

2. **Run the Application:**
   ```powershell
   java -cp ".;*" flightmodules.Loginframe
   ```

## Project Structure (Package: `flightmodules`)

* `Loginframe.java` - Application entry point and welcome screen.
* `Dashboardframe.java` - Central navigation hub.
* `PassengerFrame.java` - Module for passenger registration.
* `FlightFrame.java` - Module for flight route creation.
* `BookFlightFrame.java` - Module for booking confirmation and fare calculation.
* `Managebookframe.java` - Module for viewing travel history and flight status.

> **Note:** The `.gitignore` file in this repository is configured to exclude compiled `.class` files and the MySQL `.jar` driver to keep the repository clean. You will need to download the MySQL Connector `.jar` file locally to run the code.
