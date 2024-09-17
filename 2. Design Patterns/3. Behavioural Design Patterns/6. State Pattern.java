/*
    1. This design pattern allows an object to alter its behavior when its internal state changes.
    2. This pattern is used when an object’s behavior depends on its state, and it must change its behavior at runtime based on that state.
    3. It promotes the idea of representing each state as a separate class, which adheres to the Open/Closed Principle by allowing new states to be added without altering existing code.
*/

// Implementation - Vending Machine
/*
    1. Consider a vending machine where its behavior changes based on its state (Idle, HasMoney, DispensesItem, OutOfStock).
*/

// 1. State Interface - Defines common interface for all concraete states
interface VendingMachineState {
    void insertMoney();
    void dispenseItem();
}

// 2. Concrete State - These classes implements the behaviour associated with a specific state of the context
class IdleState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        System.err.println("Money inserted. Ready to dispense item.");
        vendingMachine.setState(vendingMachine.getHasMoneyState());
    }

    @Override
    public void dispenseItem() {
        System.out.println("Insert money first");
    }
}

class HasMoneyState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public HasMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        System.err.println("Already have money. Please select an item.");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Dispensing item...");
        vendingMachine.setState(vendingMachine.getIdleState());
    }
}

class OutOfStockState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public OutOfStockState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Machine is out of stock.");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Machine is out of stock.");
    }
}


class VendingMachine {
    private VendingMachineState idleState;
    private VendingMachineState hasMoneyState;
    private VendingMachineState outOfStockState;
    private VendingMachineState currentState;

    public VendingMachine() {
        this.idleState = new IdleState(this);
        this.hasMoneyState = new HasMoneyState(this);
        this.outOfStockState = new OutOfStockState(this);

        this.currentState = idleState;
    }

    public void setState(VendingMachineState newState) {
        currentState = newState;
    }

    public void insertMoney() {
        currentState.insertMoney();
    }

    public void despenseItem() {
        currentState.dispenseItem();
    }

    public VendingMachineState getIdleState() {
        return idleState;
    }

    public VendingMachineState getHasMoneyState() {
        return hasMoneyState;
    }

    public VendingMachineState getOutOfStockState() {
        return outOfStockState;
    }
}

// 4. Client
class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.insertMoney();
        vendingMachine.despenseItem();

        vendingMachine.despenseItem();

        vendingMachine.setState(vendingMachine.getOutOfStockState());
        vendingMachine.insertMoney();
        vendingMachine.despenseItem();
    }
}

// Advantages of State Pattern:
/*
	1. Single Responsibility Principle: The logic for different states is encapsulated within individual classes. This makes the system more modular and easier to maintain.
	2. Open/Closed Principle: You can add new states without modifying the existing state classes or the context. This is especially useful when the state machine grows in complexity.
	3. Easier Transitions: The context can switch between states dynamically, and behavior can change accordingly.
*/