# CineBook - Cinema Ticket Booking System

CineBook is a console-based cinema ticket booking system built using
Core Java and Object-Oriented Programming principles.

## Features

### Customer
- User registration and login
- Search cinema halls by name or location
- Select cinema hall
- Select screen
- Select shows
- View available and booked seats
- Select one/multiple seats
- Prevent duplicate  seat selection
- Book seats for a particular show
- Prevent double booking of the same seats for the same show
- view previous bookings

### Admin Features

- Admin registration and login
- Admin access restricted to their assigned cinema hall
- Add, delete, and update movies
- Add, delete, and update screens
- Automatic seat generation while creating a screen
- Add, delete, and update individual seats
- Manage seat type and seat row/number
- Add, delete, and update shows
- Schedule shows for available movies
- Manage cinema hall screens, seats, movies, and shows

## Technologies Used
- Core Java
- Object-Oriented Programming
- Java Collection Framework
- ArrayList
- HashMap
- LocalDate
- LocalTime

## Database Integration

CineBook uses **PostgreSQL** for persistent data storage and **JDBC** for connecting the Java application with the database.

### Technologies Used

- Java
- JDBC
- PostgreSQL
- DBeaver
- IntelliJ IDEA

### Database Structure

The database contains the following tables:

- `cinemahall` – stores cinema hall details
- `screen` – stores screens belonging to cinema halls
- `seat` – stores seats belonging to screens
- `movie` – stores movie details
- `show` – stores movie shows
- `admin` – stores admin login details and assigned cinema hall
- `user` – stores customer details
- `booking` – stores booking information
- `booking_seat` – connects bookings with selected seats

### JDBC Connection

Database connection is handled through a dedicated `DBConnection` class.

Database credentials are stored using environment variables instead of hardcoding the password.

```java
private static final String URL =
        "jdbc:postgresql://localhost:5432/cinebook";

private static final String USER =
        System.getenv("DB_USER");

private static final String PASSWORD =
        System.getenv("DB_PASSWORD");
```

## Project Structure

```text
CineBook/
│
├── src/
│   ├── DAO/
│   │   └── CinemaHallDAO.java
│   │
│   ├── DB/
│   │   └── DBConnection.java
│   │
│   ├── model/
│   │   ├── Admin.java
│   │   ├── Booking.java
│   │   ├── CinemaHall.java
│   │   ├── Movie.java
│   │   ├── Screen.java
│   │   ├── Seat.java
│   │   ├── Show.java
│   │   └── User.java
│   │
│   ├── service/
│   │   ├── AdminManager.java
│   │   ├── BookingManager.java
│   │   ├── CinemaHallManager.java
│   │   ├── MovieManager.java
│   │   ├── ScreenManager.java
│   │   ├── SeatBookingManager.java
│   │   ├── SeatManager.java
│   │   ├── ShowManager.java
│   │   └── UserManager.java
│   │
│   └── Main.java
│
├── .gitignore
├── README.md
```
## Application Flow

```text
Register / Login
       ↓
Search Cinema
       ↓
Select Cinema Hall
       ↓
Select Screen
       ↓
Select Show
       ↓
View Seats
       ↓
Select Seats
       ↓
Book Seats
       ↓
View Booking
```

### OOP Concepts Used

```markdown
- Classes and Objects
- Encapsulation
- Composition
- Separation of responsibilities
- Collections
- Object interaction between model and service classes
```
## Booking Logic

Seat availability is maintained per show.

```text
Show → List of Booked Seats
```

### Current Status

```markdown
CineBook is currently under development.
```

### Completed
- Core Java OOP-based cinema ticket booking system implemented
- Customer registration and login
- Cinema hall and screen management
- Movie and show management
- Seat management and seat availability
- Seat selection and booking
- Booking history
- Admin login and admin operations
- PostgreSQL database schema created
- JDBC database connection configured
- Database credentials configured using environment variables
- Entity IDs added to Java models
- CinemaHall DAO implemented
- CinemaHallManager connected with CinemaHallDAO
- Cinema hall data successfully retrieved from PostgreSQL

### In Progress
- Integrating the remaining Manager classes with PostgreSQL
- Implementing remaining DAO classes
- Connecting screens, seats, movies, shows, users, admins and bookings with the database
- Testing complete database-backed application flow

## Future Improvements

- Spring Boot backend
- REST APIs
- Payment integration
- Web-based user interface
- Ticket/QR generation