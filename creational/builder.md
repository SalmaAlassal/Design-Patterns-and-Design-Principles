# Builder

The Builder pattern is a creational pattern that allows us to create complex objects step by step. The pattern allows us to produce different types and representations of an object using the same construction code.

## Problem

When we need to create a complex object, we have two options:
1. Create the object directly, which requires us to call the constructor with a large number of parameters.
2. Provide a constructor with fewer parameters, but then we have to call setter methods to set each additional parameter of the object.

Using a constructor with numerous parameters or setters for every possible option can lead to code that is hard to read and error-prone. Additionally, constructing an object with many configuration options in a single step can result in inconsistent or invalid object states.

## Solution

The Builder pattern addresses this problem by providing a separate "builder" class that is responsible for constructing the complex object. The builder class has methods to set individual configuration options step by step, allowing for more readable and maintainable code. Once all the necessary configuration options are set, the builder is used to create the final object. This approach ensures that the object's state is valid and well-constructed.

### Steps

#### Simple Builder

1. **Create a Product:** Define the complex object (product) that you want to build. This class should have attributes that represent its various components.
    - The product class should have a public constructor that accepts a builder object as a parameter. The constructor should set all properties defined in the builder.
    - Use getter methods to access the product's properties. Do not define any setter methods.

2. **Create a Builder Class:** Create a separate builder class that will be responsible for constructing the product. This class can have methods for setting the various attributes of the product.

> It's used to make it easier to create an object with many fields.

#### Classic Builder

1. **Create a Product:** Define the complex object (product) that you want to build.
  
2. **Create an Abstract Builder:** Define an abstract builder interface with methods for building each component of the product.

3. **Create Concrete Builders:** Implement concrete builder classes that implement the abstract builder interface. Each concrete builder is responsible for constructing a specific variation of the product.

4. **Create a Director (optional):** Create a director class if you want to provide a higher-level interface for constructing the product. The director orchestrates the steps in the construction process.

> It's used for composite objects to provide a way to create them step-by-step.

## Structure

![Builder Pattern Structure](/imgs/builder.png)

- **Builder:** defines the interface for creating parts of a product object.
- **ConcreteBuilder:** constructs and assembles parts of the product by implementing the Builder interface.
- **Director:** constructs an object using the builder interface.
- **Product:** represents the complex object under construction.

## When to use

- When you need to create complex objects with many optional components or configuration parameters.

- When you want to avoid telescoping constructors (constructors with numerous parameters) or a large number of setter methods, which can lead to poor code readability and maintainability.

- When you need to create different variations of an object using the same construction process, or when the construction process may change over time.

## Different Implementations

### Simple Builder

```java
// Product
class User {
    // Required parameters
    private String firstName;
    private String lastName;
    // Optional parameters
    private int age;
    private String phone;
    private String address;

    public User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    // All getter, and NO setter to provide immutability
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}


class UserBuilder {
    // Required parameters
    public String firstName;
    public String lastName;
    // Optional parameters
    public int age;
    public String phone;
    public String address;

    public UserBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserBuilder age(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }
    
    public UserBuilder address(String address) {
        this.address = address;
        return this;
    }

    // Return the finally constructed User object
    public User build() {
        return new User(this);
    }
}

class builder{
    public static void main(String[] args) {
        User user1 = new UserBuilder("Salma", "Ayman")
        .age(30)
        .phone("1234567")
        .address("Fake address 1234")
        .build();
        
        System.out.println(user1.getFirstName());
        System.out.println(user1.getLastName());
        System.out.println(user1.getAge());
        System.out.println(user1.getPhone());
        System.out.println(user1.getAddress());

        User user2 = new UserBuilder("Nada", "Alassal")
        .age(40)
        .phone("5655")
        // no address
        .build();

        System.out.println(user2.getFirstName());
        System.out.println(user2.getLastName());
        System.out.println(user2.getAge());
        System.out.println(user2.getPhone());
        System.out.println(user2.getAddress());
    }
}
```

### Classic Builder

```java
// Product
class Report {
    private String header;
    private String body;
    private String footer;

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getFooter() {
        return footer;
    }

}

// Builder interface
interface IReportBuilder {
    void BuildHeader();
    void BuildBody();
    void BuildFooter();
    Report GetReport();
}

// Concrete Builders
class SimpleReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void BuildHeader() {
        report.setHeader("Simple Header");
    }

    @Override
    public void BuildBody() {
        report.setBody("Simple Body");
    }

    @Override
    public void BuildFooter() {
        report.setFooter("Simple Footer");
    }

    @Override
    public Report GetReport() {
        return report;
    }
}

class ComplexReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void BuildHeader() {
        report.setHeader("Complex Header");
    }

    @Override
    public void BuildBody() {
        report.setBody("Complex Body");
    }

    @Override
    public void BuildFooter() {
        report.setFooter("Complex Footer");
    }

    @Override
    public Report GetReport() {
        return report;
    }
}

// Director
class ReportDirector {
    private IReportBuilder reportBuilder;

    public ReportDirector(IReportBuilder reportBuilder) {
        this.reportBuilder = reportBuilder;
    }

    public Report GetReport() {
        return reportBuilder.GetReport();
    }

    public void BuildReport() {
        reportBuilder.BuildHeader();
        reportBuilder.BuildBody();
        reportBuilder.BuildFooter();
    }
}

// Client
public class Client {
    public static void main(String[] args) {
        ReportDirector reportDirector = new ReportDirector(new SimpleReportBuilder());
        reportDirector.BuildReport();
        Report report = reportDirector.GetReport();

        reportDirector = new ReportDirector(new ComplexReportBuilder());
        reportDirector.BuildReport();
        report = reportDirector.GetReport();
    }
}
```