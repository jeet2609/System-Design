package PubSubSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PubSubSystem {
    private final Map<String, Topic> topics;

    public PubSubSystem() {
        this.topics = new ConcurrentHashMap<>();
    }

    public void subscribe(Topic topic, Subscriber subscriber) {
        String topicName = topic.getTopicName();
        topics.putIfAbsent(topicName, topic);
        topics.get(topicName).addSubscriber(subscriber);
    }

    public void unSubscribe(Topic topic, Subscriber subscriber) {
        topic.removeSubscriber(subscriber);
    }

    public void publish(Topic topic, Message message) {
        topic.publish(message);
    }
}
