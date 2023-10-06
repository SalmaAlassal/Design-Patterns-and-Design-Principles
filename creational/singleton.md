# Singleton

Singleton is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.

## Problem

The Singleton pattern solves two problems at the same time, violating the *Single Responsibility Principle*:

- **Ensure that a class has just a single instance.** Why would anyone want to control how many instances a class has? The most common reason for this is to control access to some shared resource—for example, a database or a file.

- **Provide a global access point to that instance.** Remember those global variables that you used to store some essential objects? While they’re very handy, they’re also very unsafe since any code can potentially overwrite the contents of those variables and crash the app.

> Single Responsibility Principle (SRP): A class should have only a single responsibility.

## Solution

All implementations of the Singleton have these two steps in common:

- **Make the default constructor private**, to prevent other objects from using the `new` operator with the Singleton class.

- **Create a static creation method that acts as a constructor.** Under the hood, this method calls the private constructor to create an object and saves it in a static field. All following calls to this method return the cached object.

If your code has access to the Singleton class, then it’s able to call the Singleton’s static method. So whenever that method is called, the same object is always returned.

## Structure

![Singleton](/imgs/singleton.png)

## When to use

- When we need only one instance of a class, available to all clients.
- When we need a global point of access to a shared resource.

### Scenarios

- **Database connection**.
    - In a web application that uses a database, we may want to create a single database connection instance to avoid creating multiple connections that can cause performance issues. By using the Singleton pattern, we can create a single database connection instance that can be shared across the application.

## Static vs Singleton

- **Static class**: A class that can't be instantiated. It can only have static members (properties and methods). It can't be inherited.

- **Singleton**: A class that can be instantiated only once. It can have instance members (properties and methods). It can be inherited.

### When to use Singleton over Static Class

Singleton is used over static classes if most of the reasons to use Singleton is applied:
 - **If lazy initialization is needed,** as we cannot lazy initialize static classes nor control when it is initialized (especially if initialization must be after a certain point of time), and to save memory.
 
 - **If polymorphism is going to be used,** as we cannot inject static classes as services. Static methods cannot be called from a class instance. Also in a few languages we cannot override static functions in child classes.

## Use the singleton

```java
public class Main {
    public static void main(String[] args) {
        // Get the Singleton instance
        Singleton singleton1 = Singleton.getInstance();

        // Access methods or properties of the Singleton
        // ...

        // Get the Singleton instance again; it's the same instance
        Singleton singleton2 = Singleton.getInstance();

        // Check if the two references point to the same instance
        if (singleton1 == singleton2) {
            System.out.println("singleton1 and singleton2 are the same instance.");
        }
    }
}
```

## Different Implementations of the Singleton

### Eager Initialization

In eager initialization, the instance of the singleton class is created at the time of class loading. The drawback to eager initialization is that the method is created even though the client application might not be using it.

```java
public class Singleton {
    private static Singleton instance = new Singleton();

    // Private constructor to prevent external instantiation
    private Singleton() {
        // Initialization code, if needed
    }

    // Public static method to get the Singleton instance
    public static Singleton getInstance() {
        return instance;
    }

    // Other methods and properties of the Singleton class
    public void someBusinessLogic() {
        // ...
    }
}
```

### Lazy Initialization

Lazy initializations works fine in a single-threaded environment. However, it may cause issues in a multi-threaded environment. Imagine that two threads try to access `Singleton` for the first time at the same time. If both threads enter the `if` condition at the same time, then two instances of the singleton class will be created. To avoid this problem, you need to synchronize the code of the `getInstance()` method.

Lazily initialization means that the Singleton instance is created only when it's actually needed, not when the class is first loaded. This approach is often used to optimize resource usage.


```java
public class Singleton {
    private static Singleton instance;

    // Private constructor to prevent external instantiation
    private Singleton() {
        // Initialization code, if needed
    }

    // Public static method to get the Singleton instance
    public static Singleton getInstance() {
        if (instance == null) {
            // Create the Singleton instance lazily (if needed)
            instance = new Singleton();
        }
        return instance;
    }

    // Other methods and properties of the Singleton class
    public void someBusinessLogic() {
        // ...
    }
}
```

### Thread Safe Singleton

To make the `Singleton` thread-safe, you need to synchronize access to the singleton instance. This approach will force every thread to wait its turn before it can enter the method. That is, no two threads will be able to enter the method at the same time.


```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void someBusinessLogic() {
        // ...
    }
}
```

### Double-Checked Locking Singleton


```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void someBusinessLogic() {
        // ...
    }
}
```