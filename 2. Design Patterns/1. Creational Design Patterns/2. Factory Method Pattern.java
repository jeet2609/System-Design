/*
    1. It's primary purpose is to define an interface for creating objects in superclss, while allowing subclass to alter the type of objects that will be created.
    2. This pattern promotes loose coupling by reducing the need of client to knoe the specific class of objects it needs to instantiate.
    3. Usually used when class cannot anticipate the type of object it need to create.
*/


class EmailSender_ {
    public void send(String message) {
        System.out.println("Sending email : " + message);
    }
}

class SMSSender_ {
    public void send(String message) {
        System.out.println("Sending SMS : " + message);
    }
}

class NotificationService_ {
    public void sendNotification(String type, String message) {
        if(type.equals("EMAIL")) {
            EmailSender_ emailSender = new EmailSender_();
            emailSender.send(message);
        } else if(type.equals("SMS")) {
            SMSSender_ smsSender = new SMSSender_();
            smsSender.send(message);
        }
    }
}

/*
    1. Tight Coupling - NotificationService knows too much about specific types of semders
    2. Hard to Extend - If we need to add new Notification type, like WhatsAppSender, we would have to modify NotificationService class
    3. Violation of Open/Closed Principle - Everytime new type of sender is added, the sendNotification method must be changed
*/


// 1. Product - Define Notification Interface
interface Notification {
    void send(String message);
}

// 2. Concrete Product - Implement Concrete Notification Senders
class EmailSender implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Email : " + message);
    }
}

class SMSSender implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS : " + message);
    }
}

// 3. Creator - Create NotificationFactory
abstract class NotificationFactory {
    public abstract Notification createNotification();

    public void notify(String message) {
        Notification notification = createNotification();
        notification.send(message);
    }
}

// 4. Concrete Creators - Implement Concrete Factories
class EmailNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new EmailSender();
    }
}

class SMSNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new SMSSender();
    }
}

// 5. Usage - Use factory in client code
class NotificationService {
    public static void main(String[] args) {
        NotificationFactory emailFactory = new EmailNotificationFactory();
        emailFactory.notify("Hello via Email");

        NotificationFactory smsFactory = new SMSNotificationFactory();
        smsFactory.notify("Hello via SMS");
    }
}

// We can also do
class NotificationServiceNew {
    public static void main(String[] args) {
        Notification emailSender = new EmailSender();
        emailSender.send("Hello via Email");

        Notification smsSender = new SMSSender();
        smsSender.send("Hello via SMS");
    }
}

// So why factory classes
/*
    1. In factory method we encapsulate the object creation logic in seprate factory classes.
    2. If process of creating notification becomes more sophisticated, then we can do all that inside factory classes, otherwise we have to do that in client.
*/