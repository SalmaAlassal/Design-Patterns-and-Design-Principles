# UML

Unified Modeling Language (UML) is a powerful visual modeling language used in software development to represent various aspects of a system.

UML consists of diagrams to help visualize a software's design.

There are many kinds of UML diagrams, however for the sake of design patterns, the class diagram suffices.

# UML Class Diagram

UML class diagrams visualize how various classes interact with each other. It describes the classes, their attributes, methods, and relationships.

## Class

A class is a blueprint for creating objects. It defines the attributes and methods that an object will have.

![Class Diagram](/imgs/class.png)

> The class method shouldn't include the inherited methods.

## Interface

An interface is a contract that defines the methods that a class must implement.

![Interface Diagram](/imgs/interface.png)


## Relationships

![Relationships Diagram](/imgs/relationships.png)

### Association

It describes how the classes interact with each other.

When an association is drawn between two classes, it usually means that they are in some way related or contain a reference to one another.

![Association Diagram](/imgs/association.png)

#### Types of Association

- **Unidirectional:** One class has an object of the other class.
- **Bidirectional:** Each class has an object of the other class.

### Aggregation

Aggregation is a special type of association. It describes a relationship where one class is a part of another class and can exist independently of it.

Think of it as a *"has-a"* relationship. Class A has a class B.

![Aggregation Diagram](/imgs/aggregation.png)

## Composition

Composition is a special type of aggregation. It describes a relationship where one class is a part of another class and cannot exist without it.

Think of it as a *"part-of"* relationship. Class A is a part of class B, A exists only if B exists.

![Composition Diagram](/imgs/composition.png)


## Generalization/Inheritance

Generalization is a relationship between two classes where one class inherits the attributes and methods of another class.

It's used to represent an *"is-a"* relationship. E.g. a car is a vehicle.

![Generalization Diagram](/imgs/generalization.png)

## Realization/Implementation

A realization is similar to a generalization, except that instead of inheriting from the parent class, it implements the parent, usually as an interface or abstract-class.

Realization can be expressed as either a dotted line and arrow, or a lollipop notation.

![Realization Diagram](/imgs/realization.png)

---------------------------------------------