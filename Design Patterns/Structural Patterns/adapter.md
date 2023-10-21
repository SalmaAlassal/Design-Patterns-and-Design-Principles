# Adapter Pattern (Wrapper)

Adapter is a structural design pattern that allows objects with incompatible interfaces to work together. It converts the interface of a class into another interface clients expect.

## Problem

You have an existing class that you want to use, but its interface is not compatible with the rest of your code. You can’t just go in and change the interface of the class, since it might break existing code or the class might be part of a third-party library that you can’t change. You need to adapt the interface of the existing class to the interface you need.

In the early 2000s, XML was the dominant format for storing and sending structured data. In the 2010s, though, the web industry started to shift towards JSON formatted data, meaning that some people who relied on XML in their application needed to consider their options if 3rd party services began to stop supporting XML in favor of JSON. Instead of rewriting the entire application around this change in dependencies, if they made an adapter that converted the JSON to XML, then they would at least have a temporary solution!

## Solution

Create an adapter class that implements the interface you need, and delegates calls to an instance of the existing class.

The adapter is a special object that converts the interface of one object so that another object can understand it.

## Structure

### Object Adapter (Composition)

![Object Adapter Structure](/imgs/structure-object-adapter.png)

- **Service Class:** This is the class that we want to use which can't be modified (often a third-party or legacy class). The class that we want to make compatible with the target.

- **Client Class:** This is the class that needs to use the service class.

- **Client Interface (Target):** This is the interface that the client uses. The class that we want to adapt, or wrap.

- **Adapter:** This is the adapter class that converts the interface of the service class to the client interface.

### Class Adapter (Inheritance)

![Class Adapter Structure](/imgs/structure-class-adapter.png)

## When to use

- You want to use an existing class, but its interface is not compatible with the rest of your code.

## Implementation

### Object Adapter (Composition)

- Create the adapter class and make it follow the client interface.
- Add a field to the adapter class to store a reference to the service object.
- Implement all methods of the client interface in the adapter class. In each method, delegates most of the real work to the service object, handling only the interface or data format conversion.
- Clients should use the adapter via the client interface. This will let you change or extend the adapters without affecting the client code.

### Example

```java
interface IXMLparser {
   // Parses XML data into an object.
   Object parse(String data);
   // Converts an object into XML.
   String toXML(Object obj);
 }
// Concrete implementation of our XML parser.
class XMLparser implements IXMLparser {
   Object parse(String data) {
     /*...*/
   }
   String toXML(Object obj) {
     /* Example... */
     return "<ObjectClassAttr>Hello!</ObjectClassAttr>";
   }
 }

interface IJSONparser {
   // Parses JSON data into an object.
   Object parseJSON(String data);
   // Converts and object into JSON.
   String stringify(Object obj);
 }
// Concrete implementation of our Json parser.
class JSONparser implements IJSONparser{
   Object parseJSON(String data) {
     /*...*/
   }
   String stringify(Object obj) {
     /* Example... */
     return "{'ObjectClassAttr': 'Hello!'}";
   }
 }
```
When thinking about how to best design our adapter, it can help to imagine how the data will be flowing. In this example, the developer needs to take JSON data from a 3rd party, and then convert it to XML before working on it further.

```java
// We are only focusing on adapting the parse() method from XML.
class JSONtoXMLAdapter implements IXMLparser {
   // Reads in JSON data
   Object parse(String data) {
     IJSONparser json = new JSONparser();
     return json.parseJSON(data);
   }
   
   String toXML(Object obj) {
       // ...
   }
}
```

```java
static void main(String[] args) {
   // Using our third party api that uses JSON now...
   IXMLparser parserAdapter = new JSONtoXMLAdapter();
   // Arbitrary api class.
   BackendAPI api = new BackendAPI();
   // Get the JSON formatted data...
   String jsonFromAPI = api.getImportantData();
   // .. Then parse into an object ..
   Object parsedObject = parserAdapter.parse(jsonFromAPI);
   // .. Finally, translate it to XML..
   String xml = parserAdapter.toXML(parsedObject);
   // .. And send it off to where XML is needed!
   Application.methodThatNeededXMLFormat(xml);
   // Done!
}
```