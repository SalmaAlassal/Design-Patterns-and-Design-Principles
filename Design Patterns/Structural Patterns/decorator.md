# Decorator Pattern (Wrapper)

Decorator is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.

## Problem

Imagine you're developing a notification service which can send notifications via various channels like SMS, Email, Slack, etc. One way to do this is to create a base notification class and then create sub-classes for each channel. This approach works fine until the client wants to send notifications via multiple channels. In that case, we'll have to create multiple instances of notification classes which will result in many classes and code duplication.

![Problem](/imgs/decorator-problem.png)

Extending a class is the first thing that comes to mind when you need to alter an object’s behavior. However, inheritance has several serious caveats that you need to be aware of.
   - **Inheritance is static.** You can’t alter the behavior of an existing object at runtime. You can only replace the whole object with another one that’s created from a different subclass.
   - **Subclasses can have just one parent class.** In most languages, inheritance doesn’t let a class inherit behaviors of multiple classes at the same time.

One of the ways to overcome these caveats is by using *Aggregation* or *Composition* instead of Inheritance.

## Solution

![Solution](/imgs/decorator-solution.png)

## Structure

![Decorator Pattern Structure](/imgs/decorator-structure.png)

## When to use

- When you need to be able to assign extra behaviors to objects at runtime without breaking the code that uses these objects.

- Extending functionality by sub-classing is no longer practical.

- When it is not possible to extend by sub-classing, as some classes are final classes, and maybe comming from an external library where it is not possible to modify.

## Implementation

1. Make sure your business domain can be represented as a primary component with multiple optional layers over it. Think of the layers as of different flavors of the primary component.

2. **Define a Component Interface:**
   - Create an interface that defines the methods that are common to both the primary component and the optional layers.

3. **Create Concrete Component:**
   - Implement a concrete component class that implements the component interface and defines the base behavior in it.

4. **Create a Decorator Interface:**
   - Create an interface that extends the Component interface. It should have a field for storing a reference to a wrapped object. The field should be declared with the component interface type to allow linking to concrete components.
   - It must delegate all work to the wrapped object.

5. **Create Concrete Decorators:**
   - Implement concrete decorator classes that implements the decorator interface.
   - It must execute its behavior before or after the call to the parent method (which always delegates to the wrapped object).

6. The client code must be responsible for creating decorators and composing them in the way the client needs.

### Example 1 : Notification Service

```java
// The component interface defines operations that can be
// altered by decorators.
interface Notifier {
    void send(String message);
}

// Concrete components provide default implementations for the
// operations.
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

class NotificationDecorator implements Notifier {
    protected Notifier wrappee;

    public NotificationDecorator(Notifier notifier) {
        wrappee = notifier;
    }

    // The base decorator simply delegates all work to the
    // wrapped component. Extra behaviors can be added in
    // concrete decorators.
    @Override
    public void send(String message) {
        wrappee.send(message);
    }
}

class SlackDecorator extends NotificationDecorator {
    public SlackDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
       super.send(message);
       sendToSlack(message);
    }

   private void sendToSlack(String message) {
        System.out.println("Sending slack: " + message);
   }
}

class SMSDecorator extends NotificationDecorator {
      public SMSDecorator(Notifier notifier) {
         super(notifier);
      }
   
      @Override
      public void send(String message) {
         super.send(message);
         sendToSMS(message);
      }
   
      private void sendToSMS(String message) {
            System.out.println("Sending SMS: " + message);
      }
}

class Decorator {
    public static void main(String[] args) {
        Notifier notifier = new NotificationDecorator(new EmailNotifier());
        notifier = new SlackDecorator(notifier);
        notifier = new SMSDecorator(notifier);
        notifier.send("Hello World");
        // The function call will be executed in the following order:
        // 1. SMSDecorator.send() : Will call the super.send() which is delegates the call to the next decorator in the chain i.e SlackDecorator.send()
        // 2. SlackDecorator.send() : Will call the super.send() which is delegates the call to the next decorator in the chain i.e EmailNotifier.send()
        // 3. EmailNotifier.send() : Will print the message "Sending email: Hello World"
        // 4. SlackDecorator.send() : Will print the message "Sending slack: Hello World"
        // 5. SMSDecorator.send() : Will print the message "Sending SMS: Hello World"
    }
}
```

### Example 2 : Data Compression and Encryption

In this example, the Decorator pattern lets you compress and encrypt sensitive data independently from the code that actually uses this data.

![Example](/imgs/decorator-example.png)

```java
// The component interface defines operations that can be
// altered by decorators.
interface DataSource {
    void writeData(String data);
    String readData();
}

// Concrete components provide default implementations for the
// operations. There might be several variations of these
// classes in a program.
class FileDataSource implements DataSource {
    public FileDataSource(String filename) {
        // Constructor implementation
    }

    @Override
    public void writeData(String data) {
        // Write data to file.
    }

    @Override
    public String readData() {
        // Read data from file.
        return null; // Placeholder return value
    }
}

// The base decorator class follows the same interface as the
// other components. The primary purpose of this class is to
// define the wrapping interface for all concrete decorators.
class DataSourceDecorator implements DataSource {
    protected DataSource wrappee;

    public DataSourceDecorator(DataSource source) {
        wrappee = source;
    }

    // The base decorator simply delegates all work to the
    // wrapped component. Extra behaviors can be added in
    // concrete decorators.
    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}

// Concrete decorators must call methods on the wrapped object,
// but may add something of their own to the result. Decorators
// can execute the added behavior either before or after the
// call to a wrapped object.
class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        // 1. Encrypt passed data.
        // 2. Pass encrypted data to the wrappee's writeData
        // method.
    }

    @Override
    public String readData() {
        // 1. Get data from the wrappee's readData method.
        // 2. Try to decrypt it if it's encrypted.
        // 3. Return the result.
        return null; // Placeholder return value
    }
}

// You can wrap objects in several layers of decorators.
class CompressionDecorator extends DataSourceDecorator {
    public CompressionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        // 1. Compress passed data.
        // 2. Pass compressed data to the wrappee's writeData
        // method.
    }

    @Override
    public String readData() {
        // 1. Get data from the wrappee's readData method.
        // 2. Try to decompress it if it's compressed.
        // 3. Return the result.
        return null; // Placeholder return value
    }
}

// Option 1. A simple example of a decorator assembly.
class Application {
    public void dumbUsageExample() {
        DataSource source = new FileDataSource("somefile.dat");
        source.writeData(salaryRecords);
        // The target file has been written with plain data.

        source = new CompressionDecorator(source);
        source.writeData(salaryRecords);
        // The target file has been written with compressed
        // data.

        source = new EncryptionDecorator(source);
        // The source variable now contains this:
        // Encryption > Compression > FileDataSource
        source.writeData(salaryRecords);
        // The file has been written with compressed and
        // encrypted data.
    }
}
```