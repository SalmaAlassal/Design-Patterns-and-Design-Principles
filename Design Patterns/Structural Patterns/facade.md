# Facade Pattern

Facade is a structural design pattern that provides a simplified interface to a library, a framework, or any other complex set of classes.

## Problem

Imagine that you must make your code work with a broad set of objects that belong to a sophisticated library or framework. Ordinarily, you’d need to initialize all of those objects, keep track of dependencies, execute methods in the correct order, and so on.

As a result, the business logic of your classes would become tightly coupled to the implementation details of 3rd-party classes, making it hard to comprehend and maintain.

For example, its much easier for a developer to type a single line of code to do something as opposed to writing many lines of code such as this:

```java
cpu.startup();
memory.flush();
memory.start();
graphics.initialize();
pageFile.load();
// and so on...
```
The idea of a facade would be to encapsulate all this code into a single class that would allow us to do something like this:

```java
// Executes all the code above:
computer.startup();
```

## Solution

A facade is a class that provides a simple interface to a complex subsystem which contains lots of moving parts. A facade might provide limited functionality in comparison to working with the subsystem directly. However, it includes only those features that clients really care about.

Having a facade is handy when you need to integrate your app with a sophisticated library that has dozens of features, but you just need a tiny bit of its functionality.

## Structure

![Structure](/imgs/facade-strcuture.png)

## When to use

- When you need to have a limited but straightforward interface to a complex subsystem.

- Use the Facade when you need to decouple a subsystem from clients and other subsystems due to a large number of dependencies between them, promoting loose coupling.

## Implementation

1. **Declare and implement a facade class.** It should define the methods and operations that the client needs and delegate all calls to the appropriate objects within the subsystem.

2. **Make the client code communicate with the subsystem through the facade interface only.**

### Example

In this example, the Facade pattern simplifies interaction with a complex video conversion framework.

Instead of making your code work with dozens of the framework classes directly, you create a facade class which encapsulates that functionality and hides it from the rest of the code. This structure also helps you to minimize the effort of upgrading to future versions of the framework or replacing it with another one. The only thing you’d need to change in your app would be the implementation of the facade’s methods.

```java
// These are some of the classes of a complex 3rd-party video
// conversion framework. We don't control that code, therefore
// can't simplify it.
class VideoFile {
    // ...
}

class OggCompressionCodec {
    // ...
}

class MPEG4CompressionCodec {
    // ...
}

class CodecFactory {
    // ...
}

class BitrateReader {
    // ...
}

class AudioMixer {
    // ...
}

// We create a facade class to hide the framework's complexity
// behind a simple interface. It's a trade-off between
// functionality and simplicity.
class VideoConverter {
    public File convert(String filename, String format) {
        VideoFile file = new VideoFile(filename);
        Codec sourceCodec = new CodecFactory().extract(file);
        Codec destinationCodec;

        if (format.equals("mp4")) {
            destinationCodec = new MPEG4CompressionCodec();
        } else {
            destinationCodec = new OggCompressionCodec();
        }

        byte[] buffer = BitrateReader.read(filename, sourceCodec);
        byte[] result = BitrateReader.convert(buffer, destinationCodec);
        result = new AudioMixer().fix(result);

        return new File(new String(result));
    }
}

// Application classes don't depend on a billion classes
// provided by the complex framework. Also, if you decide to
// switch frameworks, you only need to rewrite the facade class.
class Application {
    public static void main(String[] args) {
        VideoConverter converter = new VideoConverter();
        File mp4 = converter.convert("funny-cats-video.ogg", "mp4");
        // You can save the 'mp4' File here as needed.
    }
}
```