/*
    1. It allows behaviour to be added to individual object either statically or dynamically without affection the behaviour of other object from same class.
    2. This pattern is used to extend the functionalities of object by warapping them in decorator classes.
    3. The key advantage is it adheres the open-closed principle.
*/

// 1. Component - This defines basic operation that can be altered by decorators
interface Coffee {
    String getDescription();
    double getCost();
}

// 2. Concrete Component - The original object that needs additional functionalities
class PlainCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Plain Coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}

// 3. Decorator - This contains a reference to the component object and is used as base class for all concrete decorators
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// 4. Concreate Decorators - This classes inherits from the abstract decorator and add new behaviours to the component
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.2;
    }
}

// Usage
class Main {
    public static void main(String[] args) {
        Coffee plainCoffee = new PlainCoffee();
        System.out.println("Description : " + plainCoffee.getDescription());
        System.out.println("Cost : " + plainCoffee.getCost());

        Coffee milkCoffee = new MilkDecorator(plainCoffee);
        System.out.println("Description : " + milkCoffee.getDescription());
        System.out.println("Cost : " + milkCoffee.getCost());

        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Description : " + sugarMilkCoffee.getDescription());
        System.out.println("Cost : " + sugarMilkCoffee.getCost());
    }
}

//  Why Decorator is abstract and not interface
/*
    1. Code Reusability: The abstract class can hold common functionality (like delegating to the decorated object), which can be reused by all concrete decorators. This avoids code duplication.
	2. Partial Implementation: Abstract classes allow for partial implementation of methods, meaning concrete decorators can inherit base functionality while adding their own.
	3. State Encapsulation: The base abstract class can manage shared state (e.g., a reference to the wrapped component), simplifying access for subclasses.
*/