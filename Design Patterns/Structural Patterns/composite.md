# Composite Pattern

Composite is a structural design pattern that lets you compose objects into tree structures and then work with these structures as if they were individual objects.

Using the Composite pattern makes sense only when the core model of your app can be represented as a tree. E.g., File System (Folders and Files).

## Problem

Imagine that you have two types of objects: `Products` and `Boxes`. A `Box` can contain several `Products` as well as a number of smaller `Boxes`. These little `Boxes` can also hold some `Products` or even smaller `Boxes`, and so on.

Say you decide to create an ordering system that uses these classes. Orders could contain simple products without any wrapping, as well as boxes stuffed with products...and other boxes. How would you determine the total price of such an order?

![Composite Pattern Problem](/imgs/Composite-Problem.png)

You could try the direct approach: unwrap all the boxes, go over all the products and then calculate the total. That would be doable in the real world; but in a program, it’s not as simple as running a loop. You have to know the classes of `Products` and `Boxes` you’re going through, the nesting level of the boxes and other nasty details beforehand. All of this makes the direct approach either too awkward or even impossible.

## Solution

The Composite pattern suggests that you work with Products and Boxes through a common interface which declares a method for calculating the total price.

How would this method work? For a product, it’d simply return the product’s price. For a box, it’d go over each item the box contains, ask its price and return a total for this box. If one of these items were a smaller box, that box would also start going over its contents and so on, until the prices of all inner components were calculated.

The greatest benefit of this approach is that you don’t need to care about the concrete classes of objects that compose the tree. You don’t need to know whether an object is a simple product or a sophisticated box. You can treat them all the same via the common interface. When you call a method, the objects themselves pass the request down the tree.

## Structure

1. **Component** declares the common interface between simple and complex objects of the tree.

2. **Leaf** represents the basic element of the tree that doesn't have sub-elements.

3. **Composite** represents a composite Component (component having children). It works with its sub-elements only through the Component interface.

![Composite Pattern Structure](/imgs/Composite-Structure.png)

## When to use

- When you have to implement a tree-like structure.

## Implementation

1. **Create the component interface**
    - Declare the common operations for both simple and complex components.

2. **Create leaf objects**
    - Represent the end objects of a tree structure. A leaf object can’t have any sub-objects.

3. **Create composite objects**
    - Represent containers that can contain both simple components and other containers.
    - Make sure a container works with all sub-objects only via the component interface.

### Example

```java
// Common interface for both products and boxes
interface Component {
    double getPrice();
}

// Product class that implements the Component interface
class Product implements Component {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

// Box class that also implements the Component interface
class Box implements Component {
    private List<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (Component component : components) {
            totalPrice += component.getPrice();
        }
        return totalPrice;
    }
}


// Client
class Composite{
    public static void main(String[] args) {
        // Box1 = Product1 + Box2(Product2 + Box3(Product3 + Product4))

        // Create the products
        Product product1 = new Product("Product 1", 10);
        Product product2 = new Product("Product 2", 20);
        Product product3 = new Product("Product 3", 30);
        Product product4 = new Product("Product 4", 40);
        
        // Create the boxes
        Box box1 = new Box();
        Box box2 = new Box();
        Box box3 = new Box();

        // Add the products to the boxes
        box1.addComponent(product1);
        box2.addComponent(product2);
        box3.addComponent(product3);
        box3.addComponent(product4);

        // Add box3 to box2
        box2.addComponent(box3);

        // Add box2 to box1
        box1.addComponent(box2);

        // Calculate the total price of box1
        System.out.println("Total price of box1 = " + box1.getPrice());
    }
}
```