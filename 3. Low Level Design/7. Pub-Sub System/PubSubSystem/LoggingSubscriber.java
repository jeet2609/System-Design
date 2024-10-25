package PubSubSystem;

public class LoggingSubscriber implements Subscriber {
    private final String name;

    public LoggingSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("[LOG] - " + name + " logged message: " + message.getContent());
    }
}
