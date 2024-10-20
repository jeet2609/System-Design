package CoffeeVendingMachine;

public enum IngredientName {
    COFFEE("Coffee"),
    WATER("Water"),
    MILK("Milk");

    private String ingredientName;

    IngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }
}
