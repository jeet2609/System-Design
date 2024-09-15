/*
    1. It helps to decouple an abstraction from it's implementation allowing the two to vary independently.
    2. This is especially useful when a system has multiple dimensions of changes, such as different kinds of objects and different ways to operate on them.
*/

/*
                                +---------+
                                | Vehicle |
                                +---------+
                                    ^  ^
                                    |  |
                    +---------------+  +---------------------+
                    |                                       |
                +---------+                             +---------+
                |   Bus   |                             |   Bike  |
                +---------+                             +---------+
                    ^  ^                                    ^ ^
                    |  |                                    | |
            +-------+  +-------+                    +-------+ +-------+
            |                  |                    |                 |
    +------------+      +-------------+      +------------+    +-------------+
    | ProduceBus |      | AssembleBus |      | ProduceBus |    | AssembleBus |
    +------------+      +-------------+      +------------+    +-------------+

    1. IF we want to change bus class, then we may end up changing ProduceBus and AssembleBus as well, or vise versa


                        +---------+                             +----------+
                        | Vehicle |                             | Workshop |
                        +---------+                             +----------+
                            ^ ^                                     ^  ^
                            | |                                     |  |
                    +-------+ +-------+                     +-------+  +-------+
                    |                 |                     |                  |
                +---------+      +---------+          +------------+     +-------------+
                |   Bus   |      |   Bike  |          | ProduceBus |     | AssembleBus |
                +---------+      +---------+          +------------+     +-------------+

    1. It decouple an abstraction from it's implemantation so that two can vary indepenently.
*/



// 1. Implementor Interface - The workshop interface defines the workshops that work on Vehicles
interface Workshop {
    void work();
}

// 2. Concrete Implementor - These classes provides specific implementations of the workshop
class Produce implements Workshop {
    @Override
    public void work() {
        System.out.println("Producing vehicle components");
    }
}

class Assemble implements Workshop {
    @Override
    public void work() {
        System.out.println("Assembling vehicle");
    }
}

// 3. Abstraction class - This represents abstract concept of a Vehicle
abstract class Vehicle {
    protected Workshop produceWorkshop;
    protected Workshop assembleWorkshop;

    public Vehicle(Workshop produceWorkshop, Workshop assembleWorkshop) {
        this.produceWorkshop = produceWorkshop;
        this.assembleWorkshop = assembleWorkshop;
    }

    abstract void manufacture();
}

// 4. Redifined Abstractions - This represents the specific types of vehicles
class Bus extends Vehicle {
    public Bus(Workshop produceWorkshop, Workshop assembleWorkshop) {
        super(produceWorkshop, assembleWorkshop);
    }

    @Override
    void manufacture() {
        System.out.println("Bus ");
        produceWorkshop.work();
        assembleWorkshop.work();
    }
}

class Bike extends Vehicle {
    public Bike(Workshop produceWorkshop, Workshop assembleWorkshop) {
        super(produceWorkshop, assembleWorkshop);
    }

    @Override
    void manufacture() {
        System.out.println("Bus ");
        produceWorkshop.work();
        assembleWorkshop.work();
    }
}

// Client
class Main {
    public static void main(String[] args) {
        Workshop produceWorkshop = new Produce();
        Workshop assembleWorkshop = new Assemble();
        
        Vehicle bus = new Bus(produceWorkshop, assembleWorkshop);
        bus.manufacture();

        Vehicle bike = new Bus(produceWorkshop, assembleWorkshop);
        bike.manufacture();
    }
}