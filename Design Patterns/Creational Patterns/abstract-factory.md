# Abstract Factory

Abstract Factory is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.

## Problem

Imagine that you’re creating a furniture shop simulator. Your code consists of classes that represent:
 1. A family of related products, say:` Chair` + `Sofa` + `CoffeeTable`.
 2. Several variants of this family. For example, products `Chair + Sofa + CoffeeTable` are available in these variants: `Modern`, `Victorian`, `ArtDeco`.

![Abstract Factory Problem](/imgs/abstract-factory-problem.png)

You need a way to create individual furniture objects so that they match other objects of the same family. Customers get quite mad when they receive non-matching furniture.

Also, you don’t want to change existing code when adding new products or families of products to the program. Furniture vendors update their catalogs very often, and you wouldn’t want to change the core code each time it happens.

## Solution

- The first thing the Abstract Factory pattern suggests is to explicitly declare interfaces for each distinct product of the product family (e.g., chair, sofa or coffee table). Then you can make all variants of products follow those interfaces. For example, all sorts of chair must implement the `Chair` interface; all coffee tables must implement the `CoffeeTable` interface, and so on.

![Solution 1](/imgs/astract-factory-sol1.png)

- The second thing is to declare the Abstract Factory—an interface with a list of creation methods for all products that are part of the product family (for example, `createChair`, `createSofa` and `createCoffeeTable`). These methods must return abstract product types represented by the interfaces we extracted previously: `Chair`, `Sofa` and `CoffeeTable`.

- The third thing is to implement separate concrete factory classes for each product variant. These classes must implement the factory interface declared by the Abstract Factory pattern.

![Solution 2](/imgs/AbstractFactory-sol2.png)

### Steps

1. **Declare interfaces for each distinct product of the product family.**

2. **Implement all product variants.**

3. **Declare the Abstract Factory interface with a list of creation methods for all products that are part of the product family.**

4. **Implement separate concrete factory classes for each product variant.**

## Structure

![Abstract Factory](/imgs/AbstractFactory.png)

## When to use

- You can't know the type of object you want to create until runtime.
- When your code needs to work with various families of related products.

## Implementation

```java
abstract class AbstractFactory {
    abstract AbstractProductA createProductA();
    abstract AbstractProductB createProductB();
}

class ConcreteFactory1 extends AbstractFactory {
    AbstractProductA createProductA() {
        return new ProductA1();
    }
    AbstractProductB createProductB() {
        return new ProductB1();
    }
}

class ConcreteFactory2 extends AbstractFactory {
    AbstractProductA createProductA() {
        return new ProductA2();
    }
    AbstractProductB createProductB() {
        return new ProductB2();
    }
}

abstract class AbstractProductA {
    abstract void doSomething();
}

class ProductA1 extends AbstractProductA {
    void doSomething() {
        System.out.println("ProductA1");
    }
}

class ProductA2 extends AbstractProductA {
    void doSomething() {
        System.out.println("ProductA2");
    }
}

abstract class AbstractProductB {
    abstract void doSomething();
}

class ProductB1 extends AbstractProductB {
    void doSomething() {
        System.out.println("ProductB1");
    }
}

class ProductB2 extends AbstractProductB {
    void doSomething() {
        System.out.println("ProductB2");
    }
}

class Client {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractFactory factory2 = new ConcreteFactory2();
        AbstractProductA productA1 = factory1.createProductA();
        AbstractProductA productA2 = factory2.createProductA();
        AbstractProductB productB1 = factory1.createProductB();
        AbstractProductB productB2 = factory2.createProductB();
        productA1.doSomething();
        productA2.doSomething();
        productB1.doSomething();
        productB2.doSomething();
    }
}
```

See the furniture shop example here: [Abstract Factory Example](/creational/code/abstract-factory.java)

--------------------------------------