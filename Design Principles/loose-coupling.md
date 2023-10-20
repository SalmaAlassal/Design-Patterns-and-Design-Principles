# Loose Coupling

**Tight coupling is bad, and loose coupling is good.**

Loose coupling is a principle which suggests that “components interacting with each others should be independent of each others, relying on the knowledge of other component as little as possible”.

It means that the components should interact with each other in a way that minimizes direct dependencies, making them more independent and interchangeable.

This is one principle we follow in a microservice architecture. The services interacting with each others are independent of each others. The interaction is strictly based on the data contracts. The services are not aware of each others implementation details.

# Tight Coupling

Tight coupling is the scenario where a group of classes are highly dependent on one another.

In a tightly-coupled architecture, a change in one class will affect all the dependent classes and may cause a domino effect of changes in the entire architecture.

### Tight Coupling Example

Notice how the `OrderTotal` method (and thus the `Order class`) depends on the implementation details of the `CartContents` and the `CartEntry` classes. If we were to try to change this logic to allow for discounts, we'd likely have to change all 3 classes. Also, if we change to using a `List<CartEntry>` collection to keep track of the items we'd have to change the Order class as well.


```java
public class CartEntry
{
    public float Price;
    public int Quantity;
}

public class CartContents
{
    public CartEntry[] items;
}

public class Order
{
    private CartContents cart;
    private float salesTax;

    public Order(CartContents cart, float salesTax)
    {
        this.cart = cart;
        this.salesTax = salesTax;
    }

    public float OrderTotal()
    {
        float cartTotal = 0;
        for (int i = 0; i < cart.items.Length; i++)
        {
            cartTotal += cart.items[i].Price * cart.items[i].Quantity;
        }
        cartTotal += cartTotal*salesTax;
        return cartTotal;
    }
}
```

### Loose Coupling Example

The logic that is specific to the implementation of the cart line item or the cart collection or the order is restricted to just that class. So we could change the implementation of any of these classes without having to change the other classes.

```java
public class CartEntry
{
    public float Price;
    public int Quantity;

    public float GetLineItemTotal()
    {
        return Price * Quantity;
    }
}

public class CartContents
{
    public CartEntry[] items;

    public float GetCartItemsTotal()
    {
        float cartTotal = 0;
        foreach (CartEntry item in items)
        {
            cartTotal += item.GetLineItemTotal();
        }
        return cartTotal;
    }
}

public class Order
{
    private CartContents cart;
    private float salesTax;

    public Order(CartContents cart, float salesTax)
    {
        this.cart = cart;
        this.salesTax = salesTax;
    }

    public float OrderTotal()
    {
        return cart.GetCartItemsTotal() * salesTax;
    }
}
```