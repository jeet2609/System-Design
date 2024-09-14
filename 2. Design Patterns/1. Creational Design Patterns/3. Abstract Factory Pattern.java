/*
    1. It is an extension of Factory method pattern and is used when we have multiple families of related or dependent objects.
    2. It defines an interface for creating a family of objects
*/

// Example
/*
    1. Consider in previous example with Notification we want NotificationLogger aslo.
    2. In old code we would need to create another factory to handle logging.
    3. This leads to ,ore factories and increased complexity.
    4. This will make harder to maintain.
*/

// 1. Define Notifcation and NotificationLogger Abstract Class
interface Notifcation {
    void sendNotification();
}

interface NotifcationLogger {
    void logNotification();
}

// 2. Implement Concrete Notification Products
class EmailNotification implements Notifcation {
    @Override
    public void sendNotification() {
        System.out.println("Sending Email Notification");
    }
}

class EmailNotificationLogger implements NotifcationLogger {
    @Override
    public void logNotification() {
        System.out.println("Logging Email Notification");
    }
}

class SMSNotification implements Notifcation {
    @Override
    public void sendNotification() {
        System.out.println("Sending SMS Notification");
    }
}

class SMSNotificationLogger implements NotifcationLogger {
    @Override
    public void logNotification() {
        System.out.println("Logging SMS Notification");
    }
}

// 3. Define Abstract Factory
interface NotificationFactory {
    Notifcation createNotifcation();
    NotifcationLogger createNotifcationLogger();
}

// 4. Implement Concreate Factories
class EmailNotificationFactory implements NotificationFactory {
    @Override
    public Notifcation createNotifcation() {
        return new EmailNotification();
    }

    @Override
    public NotifcationLogger createNotifcationLogger() {
        return new EmailNotificationLogger();
    }
}

class SMSNotificationFactory implements NotificationFactory {
    @Override
    public Notifcation createNotifcation() {
        return new SMSNotification();
    }

    @Override
    public NotifcationLogger createNotifcationLogger() {
        return new SMSNotificationLogger();
    }
}

// 5. Use Abstract Factory in Client Code
class NotificationService {
    private NotificationFactory notificationFactory;

    public NotificationService(NotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    public void sendAndLogNotification() {
        Notifcation notifcation = notificationFactory.createNotifcation();
        NotifcationLogger notifcationLogger = notificationFactory.createNotifcationLogger();

        notifcation.sendNotification();
        notifcationLogger.logNotification();
    }
}

class Main {
    public static void main(String[] args) {
        NotificationService emailNotificationService = new NotificationService(new EmailNotificationFactory());
        emailNotificationService.sendAndLogNotification();

        NotificationService smsNotificationService = new NotificationService(new SMSNotificationFactory());
        smsNotificationService.sendAndLogNotification();
    }
}