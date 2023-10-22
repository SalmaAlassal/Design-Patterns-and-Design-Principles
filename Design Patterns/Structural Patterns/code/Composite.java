import java.util.ArrayList;
import java.util.List;

// Common interface for both products and boxes
interface Component {
    double getPrice();
}

// Product class that implements the Component interface
class Product implements Component {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

// Box class that also implements the Component interface
class Box implements Component {
    private List<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (Component component : components) {
            totalPrice += component.getPrice();
        }
        return totalPrice;
    }
}


// Client
class Composite{
    public static void main(String[] args) {
        // Box1 = Product1 + Box2(Product2 + Box3(Product3 + Product4))

        // Create the products
        Product product1 = new Product("Product 1", 10);
        Product product2 = new Product("Product 2", 20);
        Product product3 = new Product("Product 3", 30);
        Product product4 = new Product("Product 4", 40);
        
        // Create the boxes
        Box box1 = new Box();
        Box box2 = new Box();
        Box box3 = new Box();

        // Add the products to the boxes
        box1.addComponent(product1);
        box2.addComponent(product2);
        box3.addComponent(product3);
        box3.addComponent(product4);

        // Add box3 to box2
        box2.addComponent(box3);

        // Add box2 to box1
        box1.addComponent(box2);

        // Calculate the total price of box1
        System.out.println("Total price of box1 = " + box1.getPrice());
    }
}