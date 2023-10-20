# Program to interfaces, Not Implementations

This principle encourages developers to depend on abstractions (such as interfaces or abstract classes) rather than concrete classes or implementations. In other words, your code should interact with classes through their declared interfaces or abstract base classes rather than dealing directly with specific implementations.

The design principle interface refers to lose coupling between modules or systems.

> The reserved keyword `interface` it's not the `interface` in `Program to interfaces`. “Program to interfaces” actually mean program to a super type like an interface or abstract class in java.


# Example

Assume a database accessor layer in your application which is used to perform CRUD operations on your DB. Let’s consider that we implement a `Service` class which calls the `DatabaseClient` class (However practically we should have a DataAccessor class between Service and DatabaseClient).

The DatabaseClient is concrete class programmed to access postgres DB. The DatabaseClient is a heavy duty class with all helper methods required to access the DB. Assume that the client decides to switch to a NoSQL database like MongoDB or add it as a secondary database for some specific purposes. This would lead to rewriting the DatabaseClient which would complicate things.

**Solution?** As this principle states, any such modules should have an abstract super type like an interface. The basic methods should be made available in the interface. Specific implementations should be implement the interface.

![Example](/imgs/program-to-interfaces.webp)

```java
ServiceClass service = new ServiceClass();
if(dbType == "POSTGRES") {
    service.db = new PostgresDBClient();
} else if(dbType == "MYSQL") {
    service.db = new MySQLDBClient();
}
```

## An interface for everything 🚫

A common mistake is to create an interface for every class.

```java
public interface PersonService { /* ... */ }
class PersonServiceImpl implements PersonService { /* ... */ }

public interface AccountingService { /* ... */ }
class AccountingServiceImpl implements AccountingService { /* ... */ }
```

Whenever you have an interface which is a one-to-one copy of a class called ...Impl, something is wrong and you should consider if you really need this interface. There are two rules to think of:

1. Use interfaces (the language keyword) if you have multiple concrete implementations.
2. Use interfaces (the design principle) to decouple your own system from external system.

So if you have a domain service (e.g. `AccountingService`) there is no need to have an interface (the language keyword) for this service if you don’t have multiple types of it (e.g. `CreditCardAccountingService` and `PayPalAccountingService`). Such a service is part of your own application, so there is no need to decouple yourself from it. There will always be just a single implementation! Our own one!