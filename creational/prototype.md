# Prototype

Prototype is a creational design pattern that lets you copy existing objects without making your code dependent on their classes.

## Problem

There are situations where you need to create multiple instances of an object with similar properties, but creating each instance from scratch can be inefficient or impractical. This can lead to code duplication and reduced performance, as well as making it difficult to maintain the code when changes are needed in the object's structure. The Prototype Pattern addresses this problem.

## Solution

The Prototype pattern delegates the cloning process to the actual objects that are being cloned.

**The pattern declares a common interface for all objects that support cloning.** This interface lets you clone an object without coupling your code to the class of that object. Usually, such an interface contains just a single `clone` method.

The implementation of the clone method is very similar in all classes. The method creates an object of the current class and carries over all of the field values of the old object into the new one. You can even copy private fields because most programming languages let objects access private fields of other objects that belong to the same class.

An object that supports cloning is called a *prototype*. When your objects have dozens of fields and hundreds of possible configurations, cloning them might serve as an alternative to subclassing.

### Steps

To implement the Prototype Pattern efficiently, follow these steps:

1. **Define an interface or base class with a clone method.**
   - This defines the common interface for all concrete prototypes.

2. **Create concrete prototype classes.**
   - Implement the clone method in each concrete prototype class.
   - These classes represent different types of objects you want to clone.

3. **Create a prototype registry. (Optional)**
    - The Prototype Registry provides an easy way to access frequently-used prototypes. It stores a set of pre-built objects that are ready to be copied.
   
This pattern helps in efficient object creation, code reuse, and maintaining encapsulation. It's especially useful when objects have complex initialization processes or when you need to create similar objects with different initial states.

## Structure

### Basic implementation

![Prototype Pattern](/imgs/prototype-design-pattern.webp)

### Prototype registry implementation

![Prototype Pattern with Registry](/imgs/Prototype-registry-implementation.png)

## When to use

- If we want to instantiate the same class with a minimal change of state, it's more appropriate to clone the instance and change its state to the required state.

## Use the prototype

```java
class Application {
    private List<Shape> shapes = new ArrayList<>();

    public Application() {
        Circle circle = new Circle();
        circle.x = 10;
        circle.y = 10;
        circle.radius = 20;
        shapes.add(circle);

        Circle anotherCircle = circle.clone();
        shapes.add(anotherCircle);

        Rectangle rectangle = new Rectangle();
        rectangle.width = 10;
        rectangle.height = 20;
        shapes.add(rectangle);

        Rectangle anotherRectangle = rectangle.clone();
        shapes.add(anotherRectangle);
    }

    public void businessLogic() {
        // Prototype rocks because it lets you produce a copy of
        // an object without knowing anything about its type.
        List<Shape> shapesCopy = new ArrayList<>();

        // For instance, we don't know the exact elements in the
        // shapes list. All we know is that they are all
        // shapes. But thanks to polymorphism, when we call the
        // `clone` method on a shape, the program checks its real
        // class and runs the appropriate clone method defined
        // in that class. That's why we get proper clones
        // instead of a set of simple Shape objects.
        for (Shape s : shapes) {
            shapesCopy.add(s.clone());
        }

        // The `shapesCopy` list contains exact copies of the
        // `shapes` list's children.
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.businessLogic();
    }
}
```
### Implementation

```java
// Base prototype.
abstract class Shape {
    protected int x;
    protected int y;
    protected String color;

    public Shape() {
    }

    public Shape(Shape source) {
        this();
        this.x = source.x;
        this.y = source.y;
        this.color = source.color;
    }

    // The clone operation returns one of the Shape subclasses.
    public abstract Shape clone();
}
```

```java
// Concrete prototype. The cloning method creates a new object
// in one go by calling the constructor of the current class and
// passing the current object as the constructor's argument.
// Performing all the actual copying in the constructor helps to
// keep the result consistent: the constructor will not return a
// result until the new object is fully built; thus, no object
// can have a reference to a partially-built clone.
class Rectangle extends Shape {
    protected int width;
    protected int height;

    public Rectangle() {
    }

    public Rectangle(Rectangle source) {
        super(source);
        this.width = source.width;
        this.height = source.height;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }
}
```

```java
class Circle extends Shape {
    protected int radius;

    public Circle() {
    }

    public Circle(Circle source) {
        super(source);
        this.radius = source.radius;
    }

    @Override
    public Circle clone() {
        return new Circle(this);
    }
}
```
---------------------------------------------