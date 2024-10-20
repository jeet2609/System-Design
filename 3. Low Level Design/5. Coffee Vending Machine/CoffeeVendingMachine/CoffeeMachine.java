package CoffeeVendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {
    private static final CoffeeMachine instance = new CoffeeMachine();
    private final Map<CoffeeName, Coffee> coffeeMenu;
    private final Map<IngredientName, Ingredient> ingredients;     // Track available ingredients

    private CoffeeMachine() {
        coffeeMenu = new HashMap<>();
        ingredients = new HashMap<>();
        initialiseIngredients();
        initialiseCoffeeMenu();
    }

    public static CoffeeMachine getInstance() {
        return instance;
    }

    private void initialiseIngredients() {
        ingredients.put(IngredientName.COFFEE, new Ingredient(IngredientName.COFFEE, 10));
        ingredients.put(IngredientName.WATER, new Ingredient(IngredientName.WATER, 10));
        ingredients.put(IngredientName.MILK, new Ingredient(IngredientName.MILK, 10));
    }

    private void initialiseCoffeeMenu() {
        Map<Ingredient, Integer> espressoRecipe = new HashMap<>();
        espressoRecipe.put(ingredients.get(IngredientName.COFFEE), 1);
        espressoRecipe.put(ingredients.get(IngredientName.WATER), 1);
        coffeeMenu.put(CoffeeName.ESPRESSO, new Coffee(CoffeeName.ESPRESSO, 2.5, espressoRecipe));

        Map<Ingredient, Integer> cappuccinoRecipe = new HashMap<>();
        cappuccinoRecipe.put(ingredients.get(IngredientName.COFFEE), 1);
        cappuccinoRecipe.put(ingredients.get(IngredientName.WATER), 1);
        cappuccinoRecipe.put(ingredients.get(IngredientName.MILK), 1);
        coffeeMenu.put(CoffeeName.CAPPUCCINO, new Coffee(CoffeeName.CAPPUCCINO, 3.5, cappuccinoRecipe));

        Map<Ingredient, Integer> latteRecipe = new HashMap<>();
        latteRecipe.put(ingredients.get(IngredientName.COFFEE), 1);
        latteRecipe.put(ingredients.get(IngredientName.WATER), 1);
        latteRecipe.put(ingredients.get(IngredientName.MILK), 2);
        coffeeMenu.put(CoffeeName.LATTE, new Coffee(CoffeeName.LATTE, 4, latteRecipe));
    }

    public void displayMenu() {
        for(Map.Entry<CoffeeName, Coffee> entry : coffeeMenu.entrySet()) {
            Coffee coffee = entry.getValue();
            System.out.println(coffee);
        }
    }

    public synchronized Coffee selectCoffee(CoffeeName coffeeName) {
        return coffeeMenu.getOrDefault(coffeeName, null);
    }

    public synchronized void dispenseCoffee(Coffee coffee, Payment payment) {
        if(payment.isPaymentSufficient(coffee.getPrice())) {
            if(hasEnoughIngredients(coffee)) {
                updateIngredients(coffee);
                System.out.println("Dispensing " + coffee.getName() + " ...");

                double change = payment.getChange(coffee.getPrice());
                if(change > 0) {
                    System.out.println("Please collect your chage : $" + change);
                }
            } else {
                System.out.println("Insufficient ingredients to make " + coffee.getName());
            }
        } else {
            System.out.println("Insufficient payment for " + coffee.getName());
        }
    }

    private boolean hasEnoughIngredients(Coffee coffee) {
        Map<Ingredient, Integer> recipe = coffee.getReceipe();

        for(Map.Entry<Ingredient, Integer> entry : recipe.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();

            if(!ingredient.hasEnoughIngredient(requiredQuantity)) {
                return false;
            }
        }

        return true;
    }

    private void updateIngredients(Coffee coffee) {
        for(Map.Entry<Ingredient, Integer> entry : coffee.getReceipe().entrySet()) {
            Ingredient ingredient = entry.getKey();
            int usedQuantity = entry.getValue();
            ingredient.updateQuantity(-usedQuantity);

            if(ingredient.getQuantity() < 3) {
                System.out.println("Low inventory alert: " + ingredient.getName());
            }
        }
    }
}
