# Keep It Simple, Stupid (KISS)

This principle states that try to keep each small piece of software simple and unnecessary complexity should be avoided. This helps us to write easy maintainable code.

## Examples

```java
// BAD practice: Unnecessary complexity
public int CalculateArea(int length, int width)
{
    int area = (length > 0 && width > 0) ? length * width : 0;
    return area;
}

// GOOD practice: Keeping it simple
public int CalculateArea(int length, int width)
{
    return length * width;
}
```

```csharp
// BAD practice: Unnecessary complexity
private void PrintNumbersRecursively(int number)
{
    if (number >= 0)
    {
        PrintNumbersRecursively(number - 1);
        Console.WriteLine(number);
    }
}

//GOOD Practice: Keeping it simple
private void PrintNumbersLoop(int number)
{
    for (int i = 0; i <= number; i++)
    {
        Console.WriteLine(i);
    }
}
```

```java
// BAD: Deeply nested and hard to understand
public string GetPlayerStatus(Player player)
{
    if (player.IsOnline) {
        if (player.CurrentGame != null) {
            return "Player is currently in a game";
        } else {
            if (player.PendingInvitations.Count > 0) {
                return "Player has pending invitations";
            } else {
                return "Player is online";
            }
        }
    } else {
        return "Player is offline";
    }
}

// GOOD: Reduced nesting and more readable
public string GetPlayerStatus(Player player)
{
    if (!player.IsOnline)
        return "Player is offline";

    if (player.CurrentGame != null)
        return "Player is currently in a game";

    return (player.PendingInvitations.Count > 0) ? "Player has pending invitations" : "Player is online";
}
```