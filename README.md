# Room Reservation System

## Description
This back-end project implements a room reservation system using Spring Boot. It provides RESTful APIs for managing room bookings, user authentication, and administrative tasks.

## Features
- User authentication and authorization
- Room availability management
- Reservation creation, modification, and cancellation
- Administrative controls for managing rooms and reservations

## Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven
- MySQL database

### Steps
1. **Clone the repository:**
   ```bash
   git clone https://github.com/amm5949/room-reservation.git
   cd room-reservation

2. **Configure the database**
- Create a MYSQL database named `room_reservation`.
- Update the database configuration in `src/main/resources/application.properties`:
  ```
  spring.datasource.url=jdbc:mysql://locahost:3306/room_reservation
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  
3. ***Build the project***
   `mvn clean install`
4. ***Run the application***
   `mvn spring-boot:run`

## Usage
Access the API documentation at `http://localhost:8080/swagger-ui.html`.
Use tools like Postman to interact with the endpoints for managing rooms and reservations.

## Contributing
1. Fork the repository.
2. Create a new branch(`git checkout -b feature-branch`).
3. Commit your changes(`git commit -m 'Add new feature'`).
4. Push to the branch(`git push origin feature-branch`).
5. Open a Pull Request.






