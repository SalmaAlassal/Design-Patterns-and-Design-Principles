# Favor Composition over Inheritance

Object oriented programming provides 2 types of relationships between classes and its instances. **has-a (composition)** and **is-a (inheritance)**. This design principle essentially suggests us that **“has-a relationship should be preferred over is-a relationship”**.

Most of the time, we tend to use inheritance to reuse the code. But inheritance is not the only way to reuse the code. Inheritance is tightly coupled and any change in superclass will affect the subclass. Inheritance breaks encapsulation. **Inheritance should be used only when subclass is a subtype of superclass, not for code reuse.**

## When to use each one?

### Inheritance

- Choose inheritance when modeling a *"is-a"* relationship. For example, a `Car` is a `Vehicle`.

### Composition

- Choose composition when modeling a *"has-a"* relationship. For example, a `Car` has an `Engine`.



## Composition over Inheritance

Suppose you are designing a software system to model different types of vehicles. You have various vehicle types such as cars &  motorcycles, and each of them has common properties like "color" and "number of wheels," but they also have unique characteristics and behaviors.

### Using Inheritance

```java
// Base Vehicle class
class Vehicle {
    private String color;
    private int numWheels;

    Vehicle(String color, int numWheels) {
        this.color = color;
        this.numWheels = numWheels;
    }

    //...
}

// Subclasses using inheritance
class Car extends Vehicle {
    Car(String color) {
        super(color, 4);
    }
    //...
}

class Motorcycle extends Vehicle {
    Motorcycle(String color) {
        super(color, 2);
    }
    //...
}
```

The problem with this approach is that if you later want to add a behavior that doesn't fit neatly into the class hierarchy (e.g., a Boat), you may need to modify the base class or create workarounds.

### Using Composition

Alternatively, you can use composition to create a more flexible and extensible design. Instead of using inheritance, you can create separate classes for each aspect of a vehicle, such as Engine, Wheels, and Color, and then compose these classes within your Vehicle class. This allows you to assemble vehicles dynamically and mix and match their components easily.

```java
// Component classes
class Wheels {
    // number of wheels
    private int numWheels;

    Wheels(int numWheels) {
        this.numWheels = numWheels;
    }

    int getNumWheels() {
        return numWheels;
    }
}

class Color {
    private String color;

    Color(String color) {
        this.color = color;
    }

    String getColor() {
        return color;
    }
}

// Vehicle class using composition
class Vehicle {
    private Color color;
    private Wheels wheels;
    //...
}
```

## Example

### Using Inheritance

```java
class Person {
 String title;
 String name;
 int age;
}

class Employee extends Person {
 int salary;
 String title;
}
```

### Using Composition

```java
class Person {
 String title;
 String name;
 int age;

 public Person(String title, String name, String age) {
    this.title = title;
    this.name = name;
    this.age = age;
 }

}

class Employee {
 int salary;
 private Person person;

 public Employee(Person p, int salary) {
     this.person = p;
     this.salary = salary;
 }
}
```