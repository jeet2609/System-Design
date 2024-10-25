package PubSubSystem;

public class PubSubSystemDemo {
    public static void main(String[] args) {
        PubSubSystem pubSubSystem = new PubSubSystem();

        // Create topics
        Topic techTopic = new Topic("Technology");
        Topic sportsTopic = new Topic("Sports");

        // Create subscribers
        Subscriber printSubscriber = new PrintSubscriber("PrintSubscriber");
        Subscriber loggingSubscriber = new LoggingSubscriber("LoggingSubscriber");

        // Subscribe to topics
        pubSubSystem.subscribe(techTopic, printSubscriber);
        pubSubSystem.subscribe(sportsTopic, printSubscriber);
        pubSubSystem.subscribe(sportsTopic, loggingSubscriber);

        // Create publishers
        Publisher techPublisher = new Publisher(pubSubSystem);
        Publisher sportsPublisher = new Publisher(pubSubSystem);

        // Create message
        Message techMessage = new Message("New AI breakthrough!");
        Message sportMessage = new Message("Big game coming up!");

        // Publish message
        techPublisher.publishMessage(techTopic, techMessage);
        sportsPublisher.publishMessage(sportsTopic, sportMessage);
    }
}
