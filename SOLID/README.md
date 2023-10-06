# SOLID Principles

SOLID is an acronym for the first five object-oriented design (OOD) principles by Robert C. Martin.

These principles, when combined together, make it easy for a programmer to develop software that are easy to maintain and extend. They also make it easy for developers to avoid code smells, easily refactor code, and are also a part of the agile or adaptive software development.

After applying the SOLID principles to your code base, the code should be decoupled, easy to reuse, easy to extend (as we are always looking for low coupling, and high cohesion)

## [1. Single Responsibility Principle (SRP)](./Single_Responsibility_Principle.md)

A class should do one thing and therefore it should have only a single reason to change.

## [2. Open-Closed Principle (OCP)](./Open_Closed_Principle.md)

Software modules should be open for extension, but closed for modification.

## [3. Liskov Substitution Principle (LSP)](./Liskov_Substitution_Principle.md)

Objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program.

## [4. Interface Segregation Principle (ISP)](./Interface_Segregation_Principle.md)

Many client-specific interfaces are better than one general-purpose interface. Clients should not be forced to implement a function they do no need.

## [5. Dependency Inversion Principle (DIP)](./Dependency_Inversion_Principle.md)

Our classes should depend upon interfaces or abstract classes instead of concrete classes and functions.