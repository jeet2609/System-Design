/*
    1. This design patten allows cloning of objects by creating new instance using prototype of existing object.
    2. This pattern is particularly useful when the cost of creating a newobject is high and you want to avoid redundant operations by copying existing objects.
*/

// 1. Prototype interface
interface GameCharacter {
    GameCharacter clone();
}

// 2. Concrete Prototype
class Warrior implements GameCharacter {
    private String weapon;

    public Warrior(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public Warrior clone() {
        return new Warrior(this.weapon);
    }
}

class Archer implements GameCharacter {
    private String weapon;

    public Archer(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public Archer clone() {
        return new Archer(this.weapon);
    }
}

// 3. Client
class Main {
    public static void main(String[] args) {
        GameCharacter warriorPrototype = new Warrior("Sword");
        GameCharacter archerPrototype = new Archer("Sword");

        GameCharacter clonnedWarrior = warriorPrototype.clone();
        GameCharacter clonnedArcher = archerPrototype.clone();

        System.out.println(clonnedWarrior);
        System.out.println(clonnedArcher);
    }
}


/*************************** Prototype with Builder ****************************/

interface Robot extends Cloneable {
    Robot clone();
}

// Builder Pattern used inside Prototype
class DefenseRobot implements Robot {
    private String cpu;
    private String sensors;
    private String movementMechanism;
    private String weaponSystem;

    // Private constructor to be used by the builder
    private DefenseRobot(DefenseRobotBuilder builder) {
        this.cpu = builder.cpu;
        this.sensors = builder.sensors;
        this.movementMechanism = builder.movementMechanism;
        this.weaponSystem = builder.weaponSystem;
    }

    // Builder static inner class
    public static class DefenseRobotBuilder {
        private String cpu;
        private String sensors;
        private String movementMechanism;
        private String weaponSystem;

        public DefenseRobotBuilder setCPU(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public DefenseRobotBuilder setSensors(String sensors) {
            this.sensors = sensors;
            return this;
        }

        public DefenseRobotBuilder setMovementMechanism(String movementMechanism) {
            this.movementMechanism = movementMechanism;
            return this;
        }

        public DefenseRobotBuilder setWeaponSystem(String weaponSystem) {
            this.weaponSystem = weaponSystem;
            return this;
        }

        // Build method to create a DefenseRobot object
        public DefenseRobot build() {
            return new DefenseRobot(this);
        }
    }

    // Cloning method for the Prototype pattern
    @Override
    public DefenseRobot clone() {
        return new DefenseRobotBuilder()
            .setCPU(this.cpu)
            .setSensors(this.sensors)
            .setMovementMechanism(this.movementMechanism)
            .setWeaponSystem(this.weaponSystem)
            .build();
    }

    @Override
    public String toString() {
        return "DefenseRobot: CPU=" + cpu + ", Sensors=" + sensors + 
               ", Movement=" + movementMechanism + ", Weapon=" + weaponSystem;
    }
}

// Client
class RobotFactory {
    public static void main(String[] args) {
        // Create an initial prototype using the builder
        DefenseRobot prototype = new DefenseRobot.DefenseRobotBuilder()
            .setCPU("Intel i7")
            .setSensors("Infrared")
            .setMovementMechanism("Wheels")
            .setWeaponSystem("Laser Cannons")
            .build();

        System.out.println("Original Prototype: " + prototype);

        // Clone the prototype to create new robots
        DefenseRobot clonedRobot1 = prototype.clone();
        System.out.println("Cloned Robot 1: " + clonedRobot1);

        // Modify clone for another variation
        DefenseRobot clonedRobot2 = new DefenseRobot.DefenseRobotBuilder()
            .setCPU("Intel i9") // Different CPU
            .setSensors("Radar")
            .setMovementMechanism("Legs")
            .setWeaponSystem("Missiles")
            .build();
        System.out.println("Cloned Robot 2: " + clonedRobot2);
    }
}