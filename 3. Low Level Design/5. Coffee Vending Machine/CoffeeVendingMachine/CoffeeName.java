package CoffeeVendingMachine;

public enum CoffeeName {
    ESPRESSO("Espresso"),
    CAPPUCCINO("Cappuccino"),
    LATTE("Latte");

    private String coffeeName;
    
    CoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getCoffeeName() {
        return coffeeName;
    }
}
