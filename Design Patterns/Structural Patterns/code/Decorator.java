// The component interface defines operations that can be
// altered by decorators.
interface Notifier {
    void send(String message);
}

// Concrete components provide default implementations for the
// operations.
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

class NotificationDecorator implements Notifier {
    protected Notifier wrappee;

    public NotificationDecorator(Notifier notifier) {
        wrappee = notifier;
    }

    // The base decorator simply delegates all work to the
    // wrapped component. Extra behaviors can be added in
    // concrete decorators.
    @Override
    public void send(String message) {
        wrappee.send(message);
    }
}

class SlackDecorator extends NotificationDecorator {
    public SlackDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
       super.send(message);
       sendToSlack(message);
    }

   private void sendToSlack(String message) {
        System.out.println("Sending slack: " + message);
   }
}

class SMSDecorator extends NotificationDecorator {
      public SMSDecorator(Notifier notifier) {
         super(notifier);
      }
   
      @Override
      public void send(String message) {
         super.send(message);
         sendToSMS(message);
      }
   
      private void sendToSMS(String message) {
            System.out.println("Sending SMS: " + message);
      }
}

class Decorator {
    public static void main(String[] args) {
        Notifier notifier = new NotificationDecorator(new EmailNotifier());
        notifier = new SlackDecorator(notifier);
        notifier = new SMSDecorator(notifier);
        notifier.send("Hello World");
        // The function call will be executed in the following order:
        // 1. SMSDecorator.send() : Will call the super.send() which is delegates the call to the next decorator in the chain i.e SlackDecorator.send()
        // 2. SlackDecorator.send() : Will call the super.send() which is delegates the call to the next decorator in the chain i.e EmailNotifier.send()
        // 3. EmailNotifier.send() : Will print the message "Sending email: Hello World"
        // 4. SlackDecorator.send() : Will print the message "Sending slack: Hello World"
        // 5. SMSDecorator.send() : Will print the message "Sending SMS: Hello World"
    }
}