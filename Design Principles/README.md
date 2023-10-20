# Design Principles

Design principles provide essential guidelines and best practices for creating well-structured, maintainable, and robust software systems. By understanding and applying these principles, you can write better code and make your software more adaptable to change.

## SOLID Principles

SOLID is an acronym for the first five object-oriented design (OOD) principles by Robert C. Martin.

These principles, when combined together, make it easy for a programmer to develop software that are easy to maintain and extend. They also make it easy for developers to avoid code smells, easily refactor code, and are also a part of the agile or adaptive software development.

After applying the SOLID principles to your code base, the code should be decoupled, easy to reuse, easy to extend (as we are always looking for low coupling, and high cohesion)

## [1. Single Responsibility Principle (SRP)](./SOLID/Single_Responsibility_Principle.md)

A class should do one thing and therefore it should have only a single reason to change.

## [2. Open-Closed Principle (OCP)](./SOLID/Open_Closed_Principle.md)

Software modules should be open for extension, but closed for modification.

## [3. Liskov Substitution Principle (LSP)](./SOLID/Liskov_Substitution_Principle.md)

Objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program.

## [4. Interface Segregation Principle (ISP)](./SOLID/Interface_Segregation_Principle.md)

Many client-specific interfaces are better than one general-purpose interface. Clients should not be forced to implement a function they do no need.

## [5. Dependency Inversion Principle (DIP)](./SOLID/Dependency_Inversion_Principle.md)

Our classes should depend upon interfaces or abstract classes instead of concrete classes and functions.

---------------------------------------------

## Other Design Principles

In addition to the SOLID principles, there are other design principles that can help you create better software.

### [Loose Coupling](./loose-coupling.md)

Minimize the dependencies between software components to make them more flexible and easier to maintain.

### [Encapsulate What Varies](./encapsulate-what-varies.md)

Isolate and encapsulate the parts of your code that are likely to change to minimize the impact of changes on the rest of the system.

### [Program to interfaces, Not Implementations](./Program-to-interfaces.md)

Code against interfaces to decouple your code from specific implementations, making it more adaptable to change.

### [Favor Composition Over Inheritance](./Favor-Composition-over-Inheritance.md)

Prefer using composition to build flexible and reusable software components instead of relying on class inheritance.

### [Don't Repeat Yourself (DRY)](./DRY.md)

Avoid code duplication and promote code reusability to improve maintainability.

### [Keep It Simple, Stupid (KISS)](./KISS.md)

Keep your code simple and straightforward instead of over-engineering it.

### [You Aren't Gonna Need It (YAGNI)](./YAGNI.md)

Don't add functionality until it's necessary to avoid wasting time and effort.

### [Separation of Concerns (SoC)](./SoC.md)

Divide your software into distinct, manageable parts, each responsible for a specific aspect of functionality.

---------------------------------------------