package CoffeeVendingMachine;

public class Payment {
    private final double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    // Getters
    public double getAmount() { return amount; }

    public boolean isPaymentSufficient(double coffeePrice) {
        return amount >= coffeePrice;
    }

    double getChange(double coffeePrice) {
        return amount - coffeePrice;
    }
}
