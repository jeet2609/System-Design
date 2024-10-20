package CoffeeVendingMachine;

public class Ingredient {
    private final IngredientName name;
    private int quantity;
    
    public Ingredient(IngredientName name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    void updateQuantity(int amount) {
        quantity += amount;
    }

    boolean hasEnoughIngredient(int requiredQuantity) {
        return quantity >= requiredQuantity;
    }

    // Getters
    public IngredientName getName() { return name; }
    public int getQuantity() { return quantity; }
}