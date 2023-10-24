# Proxy Pattern

Proxy is a structural design pattern that lets you provide a substitute or placeholder for another object. A proxy controls access to the original object, allowing you to perform something either before or after the request gets through to the original object.

A proxy is a placeholder class that is able to limit the accessibility of the "real" class. This is useful for cases such as:

- **Security**: Proxy can check if the caller has the access permissions required to perform a request to the real object.
- **Remote access**: Proxy can act as an intermediary between the client and the real object when the object is located on a remote server.
- **Lazy initialization**: Proxy can postpone the creation of the real object until the moment it’s really needed.

## Problem

Consider a bank system that has this function `bank.transferMoney(from, to, amount)`, obviously, this function is very important and should be protected, we wouldn't want it to be exposed to the public because anyone can easily gift themselves some money. We could "mask" the function so that another class (that doesn't implement the method) has to make a call to it after doing some work of its own.

## Solution

The proxy has an interface identical to the original object. So, for the client, there’s no difference between using the proxy and the real service. To actually make the proxy do something useful, we should add some code to it. In most cases, the additional behavior is giving the ability to control the service object.

## Structure

1. The **Service Interface** declares the interface of the Service, which is common to both the Real Service and the Proxy.

2. The **Real Service** contains some useful business logic.

3. The **Proxy** has an interface identical to the Real Service and holds a reference to a Real Service object. After the proxy finishes its processing (e.g., lazy initialization, logging, access control, caching, etc.), it passes the request to the service object.

4. **The Client** should work with both services via the same interface. This way you can pass a proxy into any code that expects a service object.

![Proxy Pattern Structure](/imgs/proxy-structure.png)

## When to use

- When you want to add some additional behaviors to the object of interest without changing the client code.

- When you have a heavyweight service object that wastes system resources by being always up, even though you only need it from time to time (Lazy Initialization).

- Local execution of a remote service (remote proxy). This is when the service object is located on a remote server.

- Caching request results (caching proxy). This is when you need to cache results of client requests and manage the life cycle of this cache, especially if results are quite large.

## Proxy Types

- **Remote Proxy**: provide a local representation for an object in a different address space(like a remote server). As in Client-Server apps the Client can encapsulate a Server Service in a proxy to hide the network logic behind the service.

- **Virtual Proxy**: Create expensive objects on demand (Lazy Initialization).

- **Protection Proxy**: They check that the caller has the access permissions required to perform a request to the real object.

- **Smart Reference**: from time to time, the proxy may go over the clients and check whether they are still active. If the client list gets empty, the proxy might dismiss the service object and free the underlying system resources.

## Implementation

1. **Create a proxy class** that has an interface identical to the service object.
    - It should also contain a reference to the service object.
    - Add the addational behaviors to the proxy class.

2. **Use the proxy class via the service object interface**.

### Example 1

Imagine that you run a website for a code repository database. The files are publicly viewable, but to promote user activity, you want clients to have an account in order to download any code files.

You already have a file grabbing system in place that fetches content based on a url provided:

```java
// Common interface that our real and proxy will implement:
interface IFileGrabber {
   String getFile(String url);
 }

// Our real class:
class RealFileGrabber implements IFileGrabber {
   // Some arbitrary http service to get web-content:
   HttpService http;
   
   String getFile(String url) {
     return http.get(url);
   }
}
```

Let's say that we don't want to edit the `getFile()` method because its used in other places of the website that don't require an account to access. To amend this, we'll create a new class that implements the same interface and call it `FileGrabberProxy`:

```java
// Our proxy class:
class FileGrabberProxy implements IFileGrabber {
   IFileGrabber realFileGrabber;
   boolean isRegisteredUser;
   
   FileGrabberProxy(boolean isRegisteredUser) {
     this.isRegisteredUser = isRegisteredUser;
     this.realFileGrabber = new RealFileGrabber();
   }
   
   String getFile(String url) {
     if(isRegisteredUser) {
        // After we've done whatever we need to do, we can call the real method:
       return realFileGrabber.getFile();
     }
     else {
       return "401 Unauthorized: You must have an account to get this file!";
     }
   }
}
```

Now, we can use the proxy class to check if the user is registered before allowing them to download the file:

```java
// Our client class:
class Client {
   public static void main(String[] args) {
        String user = "John";
        
        // Check if user is registered
        boolean isRegistered = checkIfUserIsRegistered(user);
        
        // Attempt to get the file
        IFileGrabber fileGrabber = new FileGrabberProxy(isRegistered);
        String file = fileGrabber.getFile("example.com/file.txt");
    }
}
```

### Example 2

This example illustrates how the Proxy pattern can help to introduce lazy initialization and caching to a 3rd-party YouTube integration library.

![Proxy Pattern Example](/imgs/proxy-example.png)

The library provides us with the video downloading class. However, it’s very inefficient. If the client application requests the same video multiple times, the library just downloads it over and over, instead of caching and reusing the first downloaded file.

The proxy class implements the same interface as the original downloader and delegates it all the work. However, it keeps track of the downloaded files and returns the cached result when the app requests the same video multiple times.

```java
// The interface of a remote service.
interface ThirdPartyYouTubeLib {
    void listVideos();
    void getVideoInfo(String id);
    void downloadVideo(String id);
}

// The concrete implementation of a service connector.
class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {
    @Override
    public void listVideos() {
        // Send an API request to YouTube.
    }

    @Override
    public void getVideoInfo(String id) {
        // Get metadata about some video.
    }

    @Override
    public void downloadVideo(String id) {
        // Download a video file from YouTube.
    }
}

// Proxy class to cache requests.
class CachedYouTubeClass implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib service;
    private String listCache;
    private String videoCache;
    private boolean needReset;

    public CachedYouTubeClass(ThirdPartyYouTubeLib service) {
        this.service = service;
    }

    @Override
    public void listVideos() {
        if (listCache == null || needReset) {
            listCache = service.listVideos();
        }
        System.out.println("List Videos: " + listCache);
    }

    @Override
    public void getVideoInfo(String id) {
        if (videoCache == null || needReset) {
            videoCache = service.getVideoInfo(id);
        }
        System.out.println("Video Info for ID " + id + ": " + videoCache);
    }

    @Override
    public void downloadVideo(String id) {
        if (!downloadExists(id) || needReset) {
            service.downloadVideo(id);
        }
        System.out.println("Download video for ID " + id);
    }

    private boolean downloadExists(String id) {
        // Check if the video download exists.
        return true;
    }
}

// GUI class that works with the service through the interface.
class YouTubeManager {
    protected ThirdPartyYouTubeLib service;

    public YouTubeManager(ThirdPartyYouTubeLib service) {
        this.service = service;
    }

    public void renderVideoPage(String id) {
        service.getVideoInfo(id);
        // Render the video page.
    }

    public void renderListPanel() {
        service.listVideos();
        // Render the list of video thumbnails.
    }

    public void reactOnUserInput() {
        renderVideoPage("12345"); // Example video ID.
        renderListPanel();
    }
}

// The application can configure proxies on the fly.
class Application {
    public static void main(String[] args) {
        ThirdPartyYouTubeLib aYouTubeService = new ThirdPartyYouTubeClass();
        ThirdPartyYouTubeLib aYouTubeProxy = new CachedYouTubeClass(aYouTubeService);
        YouTubeManager manager = new YouTubeManager(aYouTubeProxy);
        manager.reactOnUserInput();
    }
}
```