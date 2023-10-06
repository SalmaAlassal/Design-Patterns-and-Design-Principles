# Liskov Substitution Principle (LSP)

**Subclasses should be substitutable for their base classes.**

**If B is a subtype of A, then objects of type A may be replaced with objects of type B.**

**Substitutability Rules:**

- Child class mustn't remove a base class behavior.
- Child class mustn't violate base class objects (manipulated behavior).
- Inheritance can be described as *IS A* but Liskov says it should be described as *IS Substitutable For*.
- There’s no problem if the child class has extra members but it should not be less (have members with no use or with manipulated behavior).

This means that, given that class B is a subclass of class A, we should be able to pass an object of class B to any method that expects an object of class A and the method should not give any weird output in that case. This is the expected behavior, because when we use inheritance we assume that the child class inherits everything that the superclass has. The child class extends the behavior but never narrows it down.

Therefore, when a class does not obey this principle, it leads to some nasty bugs that are hard to detect.

## Example

### Problem

One of the most famous examples of violating Liskov is when dealing with logical hierarchies in code. We usually want to make an interface Shape that Rectangle, Square, and even Circle will implement. Although this may make sense logically as they're all shapes, they differ greatly in behavior and can't be substituted without violating LSP.

Square has a Width equal to its Height, so when setting one of them we're also setting the other, but of course this is not explicit for the user of the code and may lead to faulty results.

![Liskov Substitution Principle](/imgs/Example-Liskov-Substitution-Principle.png)

### Solution

Now we kept only the real common methods in the interface of Shape, which is getArea() in our case, and now the code returned to be completely substitutable again without any violations for LSP, and without any unexpected behaviors.

![Liskov Substitution Principle](/imgs/solution-Liskov-Substitution-Principle.png)


## Another Similar Example in detail

We have a simple Rectangle class, and a getArea function which returns the area of the rectangle.

```java
class Rectangle {
	protected int width, height;

	public Rectangle() {
	}

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getArea() {
		return width * height;
	}
}
```

Now we decide to create another class for Squares. As you might know, a square is just a special type of rectangle where the width is equal to the height.

Our Square class extends the Rectangle class. We set height and width to the same value in the constructor, but we do not want any client to change height or weight in a way that can violate the square property.

Therefore we override the setters to set both properties whenever one of them is changed. But by doing that we have just violated the Liskov substitution principle.

```java
class Square extends Rectangle {
	public Square() {}

	public Square(int size) {
		width = height = size;
	}

	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		super.setHeight(width);
	}

	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		super.setWidth(height);
	}
}
```

Now we have a function that takes a Rectangle and calculates its area. We pass a Square to it and get the wrong result.

```java
class Demo {
    static void useIt(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc); // Expected area of 20, got 20

        Rectangle sq = new Square();
        sq.setWidth(5);
        useIt(sq); // Expected area of 50, got 100
    }
}
```