# Separation of Concerns (SoC)

This principle states that each component of your application should have a single concern.

Divide your software into distinct sections or layers, each responsible for a specific concern (e.g., presentation, business logic, data storage). This separation makes the code easier to manage and maintain.

## Example

Consider a simple application that displays a list of users. The application consists of the following components:

- `User` class
- `UserRepository` class
- `UserView` class

The `User` class represents a user in the system. The `UserRepository` class is responsible for retrieving user data from the database. The `UserView` class is responsible for displaying the list of users.

The `UserView` class should not be responsible for retrieving user data from the database. Instead, it should delegate this responsibility to the `UserRepository` class. This way, the `UserView` class is only responsible for displaying the list of users.