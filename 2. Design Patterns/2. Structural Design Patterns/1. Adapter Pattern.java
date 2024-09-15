/*
    1. This pattern allows incompatible interfaces to work together
    2. It acts as a bridge between two incompatible classes by converting the interface of a class into another interface that client expects
*/

// 1. Adaptee - Old code
interface LegacyPaymentInterface {
    void processTransaction(double amount);
}

class LegacyPaymentSystem implements LegacyPaymentInterface {
    @Override
    public void processTransaction(double amount) {
        System.out.println("Processing transaction of " + amount + "cents");
    }
}

// 2. Target interface - Expected by client
interface PaymentGateway {
    void makePayment(double amount);
}

// 3. Adapter class - Implement target interface and internally use Adaptee class
class PaymentAdapter implements PaymentGateway {
    private LegacyPaymentInterface legacyPaymentSystem;

    public PaymentAdapter(LegacyPaymentInterface legacyPaymentSystem) {
        this.legacyPaymentSystem = legacyPaymentSystem;
    }

    @Override
    public void makePayment(double amount) {
        double amountInCents = amount * 100;
        legacyPaymentSystem.processTransaction(amountInCents);
    }
}

// 4. Client
class PaymentApplication {
    public static void main(String[] args) {
        LegacyPaymentInterface legacyPaymentSystem = new LegacyPaymentSystem();
        PaymentGateway paymentAdapter = new PaymentAdapter(legacyPaymentSystem);
        paymentAdapter.makePayment(100.0);
    }
}