# Open-Closed Principle (OCP)

**Software modules (such as classes, modules, or functions) should be open for extension but closed for modification.**

In other words, once a software entity (like a class) is created, it should not be altered to add new functionality or modify existing behavior. Instead, it should be possible to extend the entity's behavior through additional code without changing the existing source code. This is because whenever we modify the existing code, we are taking the risk of creating potential bugs. So we should avoid touching the tested and reliable (mostly) production code if possible.

But how are we going to add new functionality without touching the class, you may ask. It is usually done with the help of interfaces and abstract classes.

> The modules should accept changes and extensions by adding new classes not by changing existing ones.

> We can rely on abstractions instead of concrete classes to achieve this principle as we can implement an infinite number of these abstractions without any problem.

> Changes to the already existing code is not accepted.


## Example - Calculating Area

Let’s say that we’ve got a Rectangle class :

```java
public class Rectangle {
    private double width;
    private double height;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
```

Our customer wants us to build an application that can calculate the total area of a collection of rectangles.

```java
public class AreaCalculator {
    public double calculateArea(Rectangle[] rectangles) {
        double area = 0;
        for (Rectangle rectangle : rectangles) {
            area += rectangle.getWidth() * rectangle.getHeight();
        }
        return area;
    }
}
```

The customer is happy with the application and wants us to add a also calculate the area of circles. So we modify the AreaCalculator class to add the new functionality.

```java
public class AreaCalculator {
    public double calculateArea(Object[] shapes) {
        double area = 0;
        for (Object shape : shapes) {
            if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                area += rectangle.getWidth() * rectangle.getHeight();
            } else {
                Circle circle = (Circle) shape;
                area += circle.getRadius() * circle.getRadius() * Math.PI;
            }
        }
        return area;
    }
}
```

The customer is happy with the new functionality. But now the customer wants us to add the functionality to calculate the area of triangles. So again we need to modify the AreaCalculator class.

**That is, AreaCalculator class isn't closed for modification.** as we need to modify in order to extend it.

### Solution

We can solve this problem by using the Open-Closed Principle. We can create an interface called Shape and make Rectangle, Circle, and Triangle implement it.

```java
public interface Shape {
    double getArea();
}
```

```java
public class Rectangle implements Shape {
   // ...
   // ...

    @Override
    public double getArea() {
        return width * height;
    }
}
```

```java
public class Circle implements Shape {
    // ...
    // ...

    @Override
    public double getArea() {
        return radius * radius * Math.PI;
    }
}
```

```java
public class Triangle implements Shape {
    // ...
    // ...

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }
}
```

Now we can modify the AreaCalculator class to use the Shape interface.

```java
public class AreaCalculator {
    public double calculateArea(Shape[] shapes) {
        double area = 0;
        for (Shape shape : shapes) {
            area += shape.getArea();
        }
        return area;
    }
}
```

--------------------------

The OCP can be challenging and time-consuming to apply. If you don't anticipate frequent changes to a module, you may not need to apply it initially. However, when you do make changes or extend the module for the second time, it's a good opportunity to refactor and apply the OCP to ensure extensibility and maintainability.