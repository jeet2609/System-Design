/*
    1. It defines one of many relationship between objects.
    2. When one object (known as the Subject) changes its state, all its dependents (known as Observers) are notified and updated automatically.
    3. This pattern is commonly used to implement distributed event-handling systems, such as a notification system, where multiple objects need to be updated based on the changes in a single object.
*/

// Implementation - Stock price Notification system

import java.util.List;
import java.util.ArrayList;

// 1. Observer Interface - Defines an update method to react to the state changes in the subject.
interface Investor {
    void update(double price);
}

// 2. Concrete Observe - Implements the subject’s behavior and manages the state that the observers are interested in.
class IndividualInvestor implements Investor {
    private String name;

    public IndividualInvestor(String name) {
        this.name = name;
    }

    public void update(double price) {
        System.out.println(name + " is notified. New stock price: " + price);
    }
}

class InstitutionalInvestor implements Investor {
    private String organization;

    public InstitutionalInvestor(String organization) {
        this.organization = organization;
    }

    public void update(double price) {
        System.out.println(organization + " is notified. New stock price: " + price);
    }
}

// 3. Subject - Maintains the list of observers and notifies them when state changes.
class Stock {
    private List<Investor> investors;
    private double price;

    public Stock() {
        investors = new ArrayList<>();
    }

    public void attach(Investor investor) {
        investors.add(investor);
    }

    public void detach(Investor investor) {
        investors.remove(investor);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyInvestors();
    }

    private void notifyInvestors() {
        for(Investor investor : investors) {
            investor.update(price);
        }
    }
}

// 4. Client
class Main {
    public static void main(String[] args) {
        Stock appleStock = new Stock();

        Investor individualInvestor = new IndividualInvestor("Jeet");
        Investor institutionalInvestor = new InstitutionalInvestor("Patil");

        appleStock.attach(individualInvestor);
        appleStock.attach(institutionalInvestor);

        appleStock.setPrice(120.10);
        appleStock.setPrice(150.15);
    }
}

// Advantages of Observer Pattern:
/*
	1. Loose Coupling: The subject (observable) and observers are loosely coupled. The subject only knows the observers implement the observer interface, and it doesn’t need to know specific details about them.
	2. Easy Scalability: New observers can be added at any time without modifying the subject. This promotes the Open/Closed Principle from SOLID, making the system open to extensions but closed for modifications.
	3. Dynamic Relationships: Observers can be added or removed at runtime, providing flexibility.
	4. Centralized Event Management: It simplifies event-driven systems where one change should propagate to multiple modules.
*/