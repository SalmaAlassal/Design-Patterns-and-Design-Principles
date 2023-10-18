package creational.code;

// Product interface
interface Notification {
    void send();
}


// Concrete product classes that implement the Notification interface
class EmailNotification implements Notification {
    private String recipient;
    private String message;

    public EmailNotification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public void send() {
        // Implementation for sending an email notification to the recipient.
        System.out.println("Sending an email to " + recipient + ": " + message);
    }
}

class SMSNotification implements Notification {
    private String phoneNumber;
    private String message;

    public SMSNotification(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    @Override
    public void send() {
        // Implementation for sending an SMS notification to the phone number.
        System.out.println("Sending an SMS to " + phoneNumber + ": " + message);
    }
}


// Creator (factory) class that contains the factory method
interface NotificationFactory {
    abstract Notification createNotification(String recipient, String message);
}

// Concrete factory classes that implement the NotificationFactory interface
class EmailNotificationFactory implements NotificationFactory {
	@Override
	public Notification createNotification(String recipient, String message) {
		return new EmailNotification(recipient, message);
	}
}

class SMSNotificationFactory implements NotificationFactory {
	@Override
	public Notification createNotification(String phoneNumber, String message) {
		return new SMSNotification(phoneNumber, message);
	}
}

// Client class that uses the factory method
class NotificationClient {
	public static void main(String[] args) {
		NotificationFactory factory = getNotificationFactory("email");
		Notification notification = factory.createNotification("example@example", "Hello from email!");
		notification.send();

		// NotificationFactory emailFactory = new EmailNotificationFactory();
		// Notification emailNotification = emailFactory.createNotification("example@example.com", "Hello from email!");
		// emailNotification.send();

		// NotificationFactory smsFactory = new SMSNotificationFactory();
		// Notification smsNotification = smsFactory.createNotification("+1234567890", "Hello from SMS!");
		// smsNotification.send();
	}
	
	static NotificationFactory getNotificationFactory(String channel) {
		if (channel.equals("email")) {
			return new EmailNotificationFactory();
		} else if (channel.equals("sms")) {
			return new SMSNotificationFactory();
		}
		return null;
	}
}