# Encapsulate What Varies/Change

This principle suggests, Identify the aspects of your applications that vary and separate them from what stays the same. If a component or module in your application is bound to change frequently, then it’s a good practice to separate this part of code from the stable ones so that later we can extend or alter the part that varies without affecting those that don’t vary.

Most design patterns like Strategy, Adapter, Facade, Decorator, Observer, etc follow this principle.

## Its Two Aspects

This principle comprises two aspects that correspond to **SRP** and **OCP** sub-principles.

1. **SRP**:
    - SRP suggests that a class should have only one reason to change. In the context of encapsulating what varies, this aspect focuses on ensuring that a class or module has a single, well-defined responsibility or function.
    - By adhering to SRP, When a specific aspect of functionality changes, it should only affect one class, keeping the codebase more maintainable and less prone to errors.

2. **OCP**:
    - OCP suggests that software entities (classes, modules, functions) should be open for extension but closed for modification. This means that you can add new functionality or behavior without changing the existing code.
    - When encapsulating what varies in terms of extensions, you design your code in a way that allows you to add new features or variations by creating new classes or modules that extend the existing ones. This prevents the need to modify existing, working code, reducing the risk of introducing bugs in the process.

## Example

Assume we are designing an app for a company that provides online service of household equipments. In our applications core we have this method processServiceRequest(), the purpose of which is to create instance of a OnlineService class based on the serviceType and process the request. This is a heavy duty method as shown below:

```java
public void processServiceRequest(String serviceType) {
    OnlineService service;
    if(serviceType.equals("AirConditioner"))
        service = new ACService();
    else if(serviceType.equals("WashingMachine"))
        service = new WMService();
    else if(serviceType.equals("Refrigerator"))
        service = new RFService();
    else
        service = new GeneralService();

    service.getInfo();
    service.assignServiceRequest();
    service.assignAgent();
    service.processRequest();
}
```
Here, the type of service is a functionality which is bound to change at anytime. We might remove some services or add new and every such change in implementations would require to change this piece of code.

So, by the guidelines of “Encapsulate what varies” we need to find the code which is bound to vary and separate it so that any error in the same would not effect the important piece of code.

We can remove the code that creates instances and create a class which would work as a factory class that is only there to provide required type of instances. We are going to follow the Factory design pattern here and we will be having only one method `getOnlineService()` in this class which would do our job

```java
public class OnlineServiceFactory {
   public OnlineService getOnlineService(String type) {
       OnlineService service;
       if (type.equals("AirConditioner"))
           service = new ACService();
       else if (type.equals("WashingMachine"))
           service = new WMService();
       else if (type.equals("Refrigerator"))
           service = new RFService();
       else
           service = new GeneralService();
       return service;
   }
}
```
now we can refactor our previous code as,

```java
public void processServiceRequest(String serviceType) {
       OnlineService service = new OnlineServiceFactory().getOnlineService(serviceType);
       service.getInfo();
       service.assignServiceRequest();
       service.assignAgent();
       service.processRequest();
}
```