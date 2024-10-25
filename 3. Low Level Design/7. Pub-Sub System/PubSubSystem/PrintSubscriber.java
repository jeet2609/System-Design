package PubSubSystem;

public class PrintSubscriber implements Subscriber {
    private final String name;

    public PrintSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("[Print] - " + name + " received: " + message.getContent());
    }
}
