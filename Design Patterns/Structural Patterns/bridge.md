# Bridge Pattern

Bridge is a structural design pattern that lets you split a large class or a set of closely related classes into two separate hierarchies—abstraction and implementation—which can be developed independently of each other.

> Abstraction (also called interface) is a high-level control layer for some entity. This layer isn’t supposed to do any real work on its own. It should delegate the work to the implementation layer (also called platform).

> Note that we’re not talking about interfaces or abstract classes from your programming language. These aren’t the same things.

> When talking about real applications, the abstraction can be represented by a graphical user interface (GUI), and the implementation could be the underlying operating system code (API) which the GUI layer calls in response to user interactions.

## Problem

Say you have a geometric `Shape` class with a pair of subclasses: `Circle` and `Square`. You want to extend this class hierarchy to incorporate colors, so you plan to create `Red` and `Blue` shape subclasses. However, since you already have two subclasses, you’ll need to create four class combinations such as `BlueCircle` and `RedSquare`.

```
         Shape
        /     \
     Circle   Square
     /  \      /   \
BlueC  RedC  BlueS RedS
```

Adding new shape types and colors to the hierarchy will grow it exponentially. For example, to add a triangle shape you’d need to introduce two subclasses, one for each color. And after that, adding a new color would require creating three subclasses, one for each shape type. The further we go, the worse it becomes.

## Solution

This problem occurs because we’re trying to extend the shape classes in two independent dimensions: by form and by color. That’s a very common issue with class inheritance.

The Bridge pattern attempts to solve this problem by switching from inheritance to the object composition. What this means is that you extract one of the dimensions into a separate class hierarchy, so that the original classes will reference an object of the new hierarchy, instead of having all of its state and behaviors within one class.

![Bridge Pattern Solution](/imgs/bridge-solution.png)

Following this approach, we can extract the color-related code into its own class with two subclasses: Red and Blue. The Shape class then gets a reference field pointing to one of the color objects. Now the shape can delegate any color-related work to the linked color object. That reference will act as a bridge between the Shape and Color classes. From now on, adding new colors won’t require changing the shape hierarchy, and vice versa.

## Structure

- **Abstraction**: provides high-level control logic. It relies on the implementation object to do the actual low-level work.

- **RefinedAbstraction (optional):** either extends the features of the abstraction, or makes a variant of the Abstraction. They work with different implementations via the general implementation interface.

- **Implementation**: defines the interface for implementation classes, usually it provides only primitive (low-level) operations, and then the Abstraction do a higher-level operation based on there primitives.

- **ConcreteImplementation**: implements the Implementation interface and defines its concrete implementation.

Usually, the Client is only interested in working with the abstraction. However, it’s the client’s job to link the abstraction object with one of the implementation objects.

![Bridge Pattern Structure](/imgs/bridge-strcuture.png)

## When to use

- Use the pattern when you need to extend a class in several orthogonal (independent) dimensions.

## Implementation

- **Identify the Abstraction and Implementation:**
    - The first step is to identify the two separate concerns you want to decouple: the abstraction (what you want to use) and the implementation (how it is implemented).

- **Create Abstraction and Implementation Interfaces (or Abstract Classes):**
    - They should represent what you want to achieve without specifying how it will be achieved.
    
- **Create Concrete Implementations:**
  - They should implement the Implementation interface.
  - These classes will provide the actual implementation details.

- **Create Concrete Abstraction Classes:**
  - Concrete abstraction classes inherit from the abstraction interface.
  - These classes provide different implementations of the high-level methods. They delegate the work to the implementation objects instead of doing everything on their own.

### Example

To understand the power that this pattern offers, let's look at a UI example that doesn't use a bridge pattern:

![UI Example](/imgs/UIExample.png)

As we can see, there's a lot of duplicate classes that only vary in name by the theme they implement. This is a code smell that can be remedied with help from the "don't repeat yourself" principle!

If we abstracted our UI and Theme so that the themes can change without needing to tweak the UI, then we end up with a design that looks like this:

![UI Example with Bridge Pattern](/imgs/UIExampleWithBridgePattern.png)

On top of needing to write less code with this design, tweaking one of the color's only means changing code in a single place instead of every class that implements it. Our bridge pattern solution should look like this diagram:

![Bridge Pattern Example](/imgs/BridgePatternExample.png)


```java
// An interface that defines the needed colors for a basic theme.
interface Theme {
    /* Uses RGB HEX codes for color values */
   // Backgrounds
   String background();
   String altBackground();
   // Lines and graphs
   String lineColor();
   String altLineColor();
   // Text colors
   String fontColor();
   String altFontColor();
 }
// Defines an abstract class for UI elements
abstract class UIObject {
   // Our view theme object that we want to vary independently.
   Theme viewTheme;
   // Constructor
   UIObject(Theme theme) {
     viewTheme = theme;
   }
   // Draws the UIObject to the screen.
    void draw();
}
```

Our `Theme` interface defines methods that return a particular color value, and is general enough to be used across the application. Our abstraction, `UIObject`, gets a reference to a `Theme` implementation through dependency injection.

Having defined the abstractions, let's implement Theme for light mode and dark mode:

```java
// Light Mode implementation
class LightModeTheme implements Theme {
   String background() { return "#ffffff"; }
   String altBackground() { return "#eeeeee"; }
   String lineColor() { return "#333333"; }
   String altLineColor() { return "#8888ff"; }
   String fontColor() { return "#000000"; }
   String altFontColor() { return "#555555"; }
 }

// Dark Mode implementation
class DarkModeTheme implements Theme {
   String background() { return "#222222"; }
   String altBackground() { return "#444444"; }
   String lineColor() { return "#dddddd"; }
   String altLineColor() { return "#dfdfdf"; }
   String fontColor() { return "#eeeeee"; }
   String altFontColor() { return "#aaaaaa"; }
}
```

Lastly, let's create a few classes that extend our `UIObject` abstraction; a button class, `UIButton`, and a graph chart class `UIGraph`:

```java
class UIButton extends UIObject {
   // Constructor
   UIButton(Theme theme) {
     super(theme);
   }
   
   void draw() {
        /* Implementation */
     print("Drawing a button on the screen..");
     print("Text color value is: " + theme.fontColor());
     print("Button color value is: " + theme.backgroundColor());
     print("Done drawing the button!");
   }
   // More methods for a button (onClick, etc..);
   
 }

class UIGraph extends UIObject {
   // Constructor
   UIGraph(Theme theme) {
     super(theme);
   }
   
   void draw() {
     /* Implementation */
     print("Drawing a graph on the screen...");
     print("Text color value is " + theme.fontColor());
     print("Axis text color value is " + theme.altFontColor());
     print("Background color value is " + theme.backgroundColor());
     print("Line color value is " + theme.lineColor());
     print("Done drawing the graph!");
   }
   
   // More methods that help the graph (data and such)...
}
```

With all our classes defined, we can see what it looks like when applied in code!

```java
static void main(String[] args) {
   // Instantiating our themes:
   Theme lightTheme = new LightModeTheme();
   Theme darkTheme = new DarkModeTheme();
   // Creating a dark button:
   UIButton darkButton = new UIButton(darkTheme);
   darkButton.draw();
   
print("*****");
   // Creating a light graph:
   UIGraph lightGraph = new UIGraph(lightTheme);
   lightGraph.draw();
}
```