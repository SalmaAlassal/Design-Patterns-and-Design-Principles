# Factory Method

Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

It allows us to create objects without specifying the exact class that will be instantiated. Instead of creating objects directly, we use a factory method to create and return them.

## Problem

Imagine you have to develop a system for a bank that allows it’s customers to open Bank accounts and get bank account details. Let’s say initially your application should support creating 3 account types(Personal, Business, and Checking account), and future there may be more account types.

![Example](/imgs/BankAccount.webp)

What happens you have to create each of the above concrete classes based on user input? Imagine this is a branch of the bank and we have to create accounts based on user requirements.

- If the user input is P -> Create PersaonAccount object.
- If the user input is B -> Create BusinessAccount object.
- If the user input is C -> Create CheckingAccount object.

```java
public class Branch {
    public BankAccount openAccount(String type) {
        BankAccount bankAccount = null;
        if (type.equals("P")){
            bankAccount = new PersonalAccount();
        } else if (type.equals("B")){
            bankAccount = new BusinessAccount();
        } else if (type.equals("C")){
            bankAccount = new CheckingAccount();
        } else {
            System.out.println("Invalid type");
        }

        bankAccount.validateUserIdentity();
        bankAccount.calculateInterestRate();
        bankAccount.registerAccount();

        return bankAccount;
    }
}
```

### Problems with this approach

- What happens if we have some other places in our code to do the same. Then we have to repeat the above if-else statement in all of those places.

- What happens if we have to introduce some new bank accounts other than above 3. Then we have to modify our client in this case our openAccount method in the Branch and all other places in order to accommodate new account types.

This approach becomes problematic you'd need to modify the code and add new conditional blocks, leading to code that's hard to maintain and requiring recompilation for each change.

## Solution

The Factory Method pattern suggests that you replace direct object construction calls (using the `new` operator) with calls to a special factory method. Don't worry: the objects are still created via the `new` operator, but it's being called from within the factory method. Objects returned by a factory method are often referred to as *products*.

### Steps

To implement the Factory Method pattern, follow these steps:

1. **Define an Interface or Abstract Class:** Create an interface or abstract class that declares the factory method. This method is responsible for creating objects of a specific product type.
    - The factory method should be abstract, forcing all subclasses to implement their own versions of the method.

2. **Create Concrete Products:** Implement concrete classes that extend the interface or abstract class, representing different product types. These classes will provide the actual implementations of the objects to be created.

3. **Define Concrete Factories:** Create concrete factory classes that extend the interface or abstract class and implement the factory method. Each concrete factory is responsible for creating a specific product.
    - The factory method here should return the concrete product type.

4. **Client Usage:** In client code, instead of creating objects directly, rely on the factory method by using an instance of the appropriate concrete factory. This allows you to create objects without specifying their exact class and ensures flexibility.

5. **Add New Products:** To add new product types, create a new concrete product class and a corresponding concrete factory. The existing client code doesn't need to change, promoting extensibility.

## Structure

![Structure](/imgs/factorymethod.png)

## When to use

- You can't know the type of object you want to create until runtime.

## Factory Method Pattern vs. Simple Factory Pattern

Simple factory is just an encapsulation of a piece of code so that it can be reused. It provides only one way of creating objects, and hence if you want to create objects in other ways, you have to modify the factory itself, but which is the strength of factory method pattern. But of course, it doesn’t mean you must use factory method pattern, it all depends on how you want the factory to be used.

## Different implementations of the pattern

### 1. Simple Factory

```java
interface Product {
    void use();
}

class ProductA implements Product {
    @Override
    public void use() {
        System.out.println("Product A");
    }
}

class ProductB implements Product {
    @Override
    public void use() {
        System.out.println("Product B");
    }
}

class Factory {
    public Product createProduct(String type) {
        if (type.equals("A")) {
            return new ProductA();
        } else if (type.equals("B")) {
            return new ProductB();
        } else {
            return null;
        }
    }
}

class SimpleFactoryPattern {
    public static void main(String[] args) {
        // Create a factory
        Factory factory = new Factory();

        // Create a product
        Product product = factory.createProduct("A");

        // Use the product
        product.use();
    }
}
```

### 2. Factory Method

```java
interface Product {
    void use();
}

class ProductA implements Product {
    @Override
    public void use() {
        System.out.println("Product A");
    }
}

class ProductB implements Product {
    @Override
    public void use() {
        System.out.println("Product B");
    }
}

interface Factory {
    abstract Product createProduct();
}

class aFactory implements Factory {
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}

class bFactory implements Factory {
    @Override
    public Product createProduct() {
        return new ProductB();
    }
}

class FactoryMethodPattern {
    public static void main(String[] args) {
        // Create a factory
        Factory factory = new aFactory();

        // Create a product
        Product product = factory.createProduct();

        // Use the product
        product.use();
    }
}
```

**See a real example on factory method pattern [here](/creational/code/factory-method.java).**