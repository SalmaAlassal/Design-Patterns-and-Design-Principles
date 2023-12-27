# Flyweight Pattern

Flyweight is a structural design pattern that lets you fit more objects into the available amount of RAM by sharing common parts of state between multiple objects instead of keeping all of the data in each object.

> Also Known as: Cache

## Problem

Imagine that you’re creating a forest in a video game; the same model is used for every tree, and only the tree's coordinates differ. If each tree object had it's own, identical model, there would be thousands of duplicate objects in memory.

![Problem](/imgs/flyweight-problem.png)

## Solution

Instead, if the tree object's shared the model, only one instance of the model would ever be in memory (in theory), and only extra space would be needed for references to the model, which are comparatively miniscule!

![Solution](/imgs/flyweight-solution.png)

The Flyweight pattern suggests that you stop storing the extrinsic state inside the object. Instead, you should pass this state to specific methods which rely on it. Only the intrinsic state stays within the object, letting you reuse it in different contexts.

## Structure

![Structure](/imgs/flyweight-structure.png)

## When to use

- These objects share common state (intrinsic state) that can be grouped together.
- These objects have unique state (extrinsic state) that needs to be saved independently.
- Your application doesn't depend on objects identity (as they will be shared, so they can't be compared by reference comparison).
- Only when your program must support a huge number of objects which barely fit into available RAM.

## Implementation

- **Identify Intrinsic and Extrinsic State:**
    - **Intrinsic state:** Represents the shared, immutable part of the object. This state can be shared among multiple instances of the flyweight objects.
    - **Extrinsic state:** Represents the unique, mutable part of the object. This state must be provided by the client and is not shared among flyweight instances.

- **Create Flyweight Interface:**
    - Declare methods to set and get the intrinsic state(shared among flyweights) and to operate on the extrinsic state (unique to each flyweight).

- **Create Concrete Flyweight Class:**
    - Implement the Flyweight interface to create a concrete flyweight object. The concrete flyweight object must be sharable. Any state it stores must be intrinsic, that is, it must be independent of the concrete flyweight object’s context.

- **Create unshared Concrete Flyweight Class:**
    - Implements the flyweight interface but as this class has no shared state, any state that's put in it should be state that's always unique, and doesn't affect the action (not extrinsic state) (not all flyweight subclasses need to be shared. The flyweight interface enables sharing; it doesn't enforce it)

- **Create Flyweight Factory:**
    - Create a flyweight factory to create and manage flyweight objects. The factory maintains a pool of different flyweight objects and returns an object from the pool if it is already created, adds one to the pool and returns it if it doesn’t exist.

- **Create Context class:**
    - Sometimes it's not good to let the extrinsic state loose with the client (especially if it's more than one variable for each object), so the context defines the extrinsic state object, so the client can have the extrinsic state of the flyweights well structured.

- **Create Client:**
    - The client must use the factory to get the flyweight object and then it can pass the extrinsic state to the flyweight object to perform any action on it.

### Example

```java
// Flyweight Interface
interface Character {
    void format(FormattingContext context);
}

// Concrete Flyweight Class
class ConcreteCharacter implements Character {
    private String fontFamily;
    private int fontSize;

    public ConcreteCharacter(String fontFamily, int fontSize) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }

    @Override
    public void format(FormattingContext context) {
        System.out.println("Character at position " + context.getPosition() +
                " formatted with Font: " + this.fontFamily +
                ", Size: " + this.fontSize +
                ", External Font: " + context.getExternalFont() +
                ", External Size: " + context.getExternalSize());
    }
}

// Unshared Concrete Flyweight Class
// Represents an unshared flyweight that doesn't have any shared intrinsic state. It is created on demand and is not part of the shared pool.
class UnsharedConcreteCharacter implements Character {
    private char symbol;

    public UnsharedConcreteCharacter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void format(FormattingContext context) {
        System.out.println("Unshared Character '" + this.symbol +
                "' at position " + context.getPosition() +
                " formatted with External Font: " + context.getExternalFont() +
                ", External Size: " + context.getExternalSize());
    }
}

// Formatting Context
// Represents the context containing extrinsic state (external font, external size, position) that is passed to the flyweight objects during formatting.
class FormattingContext {
    private String externalFont;
    private int externalSize;
    private int position;

    public FormattingContext(String externalFont, int externalSize, int position) {
        this.externalFont = externalFont;
        this.externalSize = externalSize;
        this.position = position;
    }

    public String getExternalFont() {
        return externalFont;
    }

    public int getExternalSize() {
        return externalSize;
    }

    public int getPosition() {
        return position;
    }
}

// Flyweight Factory
class CharacterFactory {
    private static final Map<String, Character> characterPool = new HashMap<>();

    public static Character getCharacter(String key, boolean isShared) {
        if (isShared && !characterPool.containsKey(key)) {
            String[] parts = key.split(":");
            String fontFamily = parts[0];
            int fontSize = Integer.parseInt(parts[1]);
            characterPool.put(key, new ConcreteCharacter(fontFamily, fontSize));
        }
        return characterPool.getOrDefault(key, new UnsharedConcreteCharacter(key.charAt(0)));
    }
}

// Client
public class TextEditor {
    public static void main(String[] args) {
        // Client provides extrinsic state
        int position1 = 0;
        int position2 = 5;

        // Using the flyweight factory to get shared flyweight objects
        Character sharedCharA = CharacterFactory.getCharacter("Arial:12", true);
        Character sharedCharB = CharacterFactory.getCharacter("Times New Roman:14", true);

        // Using the flyweight factory to get unshared flyweight object
        Character unsharedCharC = CharacterFactory.getCharacter("C", false);

        // Formatting characters with extrinsic state
        FormattingContext context1 = new FormattingContext("Arial", 12, position1);
        FormattingContext context2 = new FormattingContext("Times New Roman", 14, position2);

        sharedCharA.format(context1); // Character at position 0 formatted with Font: Arial, Size: 12, External Font: Arial, External Size: 12
        sharedCharB.format(context2); // Character at position 5 formatted with Font: Times New Roman, Size: 14, External Font: Times New Roman, External Size: 14
        unsharedCharC.format(context1); // Unshared Character 'C' at position 0 formatted with External Font: Arial, External Size: 12
    }
}
```