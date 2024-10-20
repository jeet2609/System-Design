package CoffeeVendingMachine;

import java.util.Map;

public class Coffee {
    private final CoffeeName name;
    private final double price;
    private final Map<Ingredient, Integer> recipe;

    public Coffee(CoffeeName name, double price, Map<Ingredient, Integer> recipe) {
        this.name = name;
        this.price = price;
        this.recipe = recipe;
    }

    // Getters
    public CoffeeName getName() { return name; }
    public double getPrice() { return price; }
    public Map<Ingredient, Integer> getReceipe() { return recipe; }

    @Override
    public String toString() {
        return getName().getCoffeeName() + " - $" + getPrice();
    }
}
