package creational.code;

class Main {
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


class Singleton {
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