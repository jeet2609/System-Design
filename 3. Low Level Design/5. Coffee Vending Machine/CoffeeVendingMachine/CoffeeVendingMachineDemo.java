package CoffeeVendingMachine;

public class CoffeeVendingMachineDemo {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = CoffeeMachine.getInstance();

        // Display coffee menu
        coffeeMachine.displayMenu();
        System.out.println();

        // Select and dispense coffe
        Coffee espresso = coffeeMachine.selectCoffee(CoffeeName.ESPRESSO);
        coffeeMachine.dispenseCoffee(espresso, new Payment(3.0));

        Coffee cappuccino = coffeeMachine.selectCoffee(CoffeeName.CAPPUCCINO);
        coffeeMachine.dispenseCoffee(cappuccino, new Payment(3.4));

        Coffee latte = coffeeMachine.selectCoffee(CoffeeName.LATTE);
        coffeeMachine.dispenseCoffee(latte, new Payment(4.0));
    }
}
