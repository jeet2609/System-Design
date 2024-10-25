package PubSubSystem;

public class Publisher {
    private final PubSubSystem pubSubSystem;

    public Publisher(PubSubSystem pubSubSystem) {
        this.pubSubSystem = pubSubSystem;
    }

    public void publishMessage(Topic topic, Message message) {
        pubSubSystem.publish(topic, message);
    }
}
