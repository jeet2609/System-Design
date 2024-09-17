/*
    1. This pattern defines a skeleton of an algorithm in a method, callled the template method, but allows subclass to override certain steps of the algorithm without changing it's overall structure.
    2. This promotes code resuse ans esures that the high level algorithm remains conistent while individual steps can b customized by subclass. 
*/

// Implementation - Preperaing beverages
/*
    1. PreThe process of making a beverage generally follows the same steps: boling water, brewing the beverage, pouring into a cup, and adding condiments.
    2. Different beverages, however, may have different brewing methods and condiments.
*/

// 1. Abstract class - Define templete method which includes the outline of the algorithm.
abstract class Beverage {
    private void boilWater() {
        System.out.println("Boiling water...");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }

    protected abstract void brew();
    protected abstract void addCondiments();

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
}

// 2. Concrete classes - Implement abstract methods. Each subclass provides specific behaviour for the steps that need customization.
class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Steeping the tea...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon...");
    }
}

class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Dripping coffee through filter...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk...");
    }
}

// 3. Client
class Main {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        tea.prepareRecipe();

        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}

// Advantages
/*
    1. Promotes Code Reuse: The template method ensures that subclasses can reuse common logic, avoiding duplicate code across the system.
	2. Enforces an Algorithm Structure: The algorithm’s skeleton is preserved, so all subclasses follow the same steps in the same order.
	3. Ease of Extension: The pattern supports the Open/Closed Principle, as adding new behaviors (via subclasses) does not modify the existing code.
*/