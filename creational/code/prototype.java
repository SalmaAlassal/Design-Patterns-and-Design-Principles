package creational.code;

import java.util.ArrayList;
import java.util.List;

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