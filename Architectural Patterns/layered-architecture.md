# Layered (n-tier) Architecture Pattern

Layered architecture pattern is a software architecture pattern that divides the software application into multiple horizontal layers. Each layer is responsible for performing different tasks.

![Layered Architecture](/imgs/Layered-Architecture.png)

## Layers

A layered architecture typically consists of the following layers:

### Presentation Layer

The presentation layer is the topmost layer of the application. It is responsible for presenting information to the user and handling user interaction. It is also known as the UI layer or the client layer.

### Application Layer (Business Logic or Service Layer)

The application layer is responsible for implementing the business logic of the application. It contains the core functionality of the application. It processes and implements business rules and logic.

This layer acts as an intermediary between the Presentation Layer and the Data Layer.

### Data Access Layer (Persistence Layer)

The persistence layer is in charge of communicating with a database. It contains the code that's necessary to access the database layer.

It is employed to manage operations like:
- object-relational mapping
- CRUD (Create, Read, Update, Delete) operations

### Database Layer

The database layer is the actual database that stores the data.

## Data Flow

Data flows through the layers in a typical Layered Architecture as follows:

1. User interactions occur at the Presentation Layer.
2. The Presentation Layer invokes functions in the Application Layer to handle these interactions.
3. The Application Layer processes the requests, enforces business rules, and may interact with the Data Layer to read or update data.
4. Data access and storage are managed by the Data Layer, which communicates with the database or other storage systems.
5. Results or responses flow back through the layers in reverse order until they reach the user in the Presentation Layer.

![Example](/imgs/Layered-Architecture-example.png)

------------------------------------------------------------