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

## Project Structure

```text
src
├── app
│   └── Cine.java
│
├── model
│   ├── Booking.java
│   ├── CinemaHall.java
│   ├── Movie.java
│   ├── Screen.java
│   ├── Seat.java
│   ├── Show.java
│   └── User.java
│
└── service
    ├── BookingManager.java
    ├── CinemaHallManager.java
    ├── MovieManager.java
    ├── ScreenManager.java
    ├── SeatBookingManager.java
    ├── SeatManager.java
    ├── ShowManager.java
    └── UserManager.java
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
The customer-side booking flow is implemented using Core Java.
```
## Future Improvements

- Spring Boot backend
- REST APIs
- Payment integration
- Web-based user interface
- Ticket/QR generation