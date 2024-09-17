/*
    1. The Strategy Design Pattern it helps define a famil of algorithms, encapsulates each algorithm, and makes them interchangeable.
    2. This pattern allows the algorithm to vary independently from clients that use it.
*/

// Implementation
/*
    1. Let's consider a payment system that supports multiple payment methods like credit card, PayPal and Google Pay.
    2. The system need to choose the correct payment method dynamically without hardcoding it into the system.
*/

// 1. Strategy Interface - This defines algorithm's structure that all concreate strategies must implement.
interface PaymentStrategy {
    void pay(int amount);
}

// 2. Concrete Strategies - These are the different implementations of the Strategy interface, each defining a specific algorithm.
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class GooglePayPayment implements PaymentStrategy {
    private String phoneNumber;

    public GooglePayPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Google Pay.");
    }
}

// 3. Context - Context class is the object that requires an algorithm to solve a problem.
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(int amount) {
        paymentStrategy.pay(amount);
    }
}

// 4. Client
class Main {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext(new CreditCardPayment("1234-5678-9876-5432"));
        paymentContext.executePayment(100);

        paymentContext.setPaymentStrategy(new PayPalPayment("jeet@paypal.com"));
        paymentContext.executePayment(200);

        paymentContext.setPaymentStrategy(new GooglePayPayment("1234567890"));
        paymentContext.executePayment(300);
    }
}

// Advantages of Strategy Pattern:
/*
	1. Encapsulation of Algorithms: Each algorithm is encapsulated within its own class, promoting separation of concerns.
	2. Open/Closed Principle: New strategies can be introduced without altering existing code.
	3. Interchangeability: Strategies can be swapped at runtime depending on the user’s choice or external conditions.
	4. Flexibility: The pattern provides a flexible way to switch between different behaviors.
*/