# Designing a Pub-Sub System

A Pub-Sub (Publisher-Subscriber) system is a messaging framework where publishers send messages to specific topics, and subscribers receive these messages if they’re subscribed to those topics. Here’s how it works in simpler terms:

	1.	Publishers create and send out messages to a named topic (like a channel or category).
	2.	Subscribers who are interested in that topic get notified and receive the messages in real time.
	3.	Multiple publishers and subscribers can exist, and each subscriber receives all messages published to the topics they’re subscribed to.

In this system, concurrent access (when multiple publishers or subscribers are active at once) is handled securely to ensure data consistency. The system is also designed to be scalable, so more publishers or subscribers can be added without slowing things down.

In short, this setup allows efficient message delivery from publishers to subscribers in a way that is both real-time and adaptable, making it useful for things like news feeds, notifications, and data streaming.

## Requirements
1. The Pub-Sub system should allow publishers to publish messages to specific topics.
2. Subscribers should be able to subscribe to topics of interest and receive messages published to those topics.
3. The system should support multiple publishers and subscribers.
4. Messages should be delivered to all subscribers of a topic in real-time.
5. The system should handle concurrent access and ensure thread safety.
6. The Pub-Sub system should be scalable and efficient in terms of message delivery.

## For my reference - Classes, Interfaces and Enumerations
1. The **`Message`** class represents a message that can be published and received by subscribers. It contains the message content.
2. The **`Topic`** class represents a topic to which messages can be published. It maintains a set of subscribers and provides methods to add and remove subscribers, as well as publish messages to all subscribers.
3. The **`Subscriber`** interface defines the contract for subscribers. It declares the onMessage method that is invoked when a subscriber receives a message.
4. The **`PrintSubscriber`** and **`Logging`** classes are concrete implementation of the Subscriber interface.
5. The **`Publisher`** class represents a publisher that publishes messages to a specific topic.
6. The **`PubSubSystem`** class is the main class that manages topics, subscribers, and message publishing. It uses a ConcurrentHashMap to store topics.
7. The **`PubSubSystemDemo`** class demonstrates the usage of the Pub-Sub system by creating topics, subscribers, and publishers, and publishing messages.