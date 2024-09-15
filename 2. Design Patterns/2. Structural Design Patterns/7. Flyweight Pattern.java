/*
    1. This pattern is used to minimise memory usage by sharing as much data as possible with similar objects.
    2. Most beneficial when an application creats a large number of similar objects and reducing memory consumption becomes critical.
*/

// Key Concepts
/*
    1. Intrinsic State - This part of the object state is shared betwen all instances, It is stored in flyweight object itself.
    2. Extrinsic State - This part is unique to each object instance ans is passed to the flyweight method of the client.
*/

// Implementation
/*
    1. Imagine we are developing text editor.
    2. Each character has size, weight, etc.
    3. Without Flyweight each character will be seperate object, which could use huge memory.
*/

// 1. Flyweight Interface - Declares methods for flyweight object. These methods typically accept extrinsic states to operate on

import java.util.HashMap;
import java.util.Map;

interface CharacterFlyweight {
    void display(String font, int fontSize, String color);
}

// 2. Concrete Flyweight - Implements the flyweight interface and stores intrinsic data that can be shared accross many instances.
class ConcreateCharacterFlyweight implements CharacterFlyweight {
    private final char symbol;

    public ConcreateCharacterFlyweight(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void display(String font, int fontSize, String color) {
        System.out.println("Character: " + symbol + " Font: " + font + " FontSize: " + fontSize + " Color: " + color);
    }
}

// 3. Flyweight Factory - Manage creation and reuse of flyweight objects. It ensures the objects are shared when possible rather that creating new ones.
class FlyweightFactory {
    private Map<Character, CharacterFlyweight> flyweightCache = new HashMap<>();
    
    public CharacterFlyweight getCharacterFlyweight(char symbol) {
        return flyweightCache.getOrDefault(flyweightCache.get(symbol), new ConcreateCharacterFlyweight(symbol));
    }
}

// 4. Client
class Main {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();

        CharacterFlyweight charA = factory.getCharacterFlyweight('A');
        charA.display("Arial", 12, "Black");

        CharacterFlyweight charB = factory.getCharacterFlyweight('B');
        charB.display("Times New Roman", 14, "Blue");

        // Reuse flyweight for 'A'
        CharacterFlyweight charA2 = factory.getCharacterFlyweight('A');
        charA2.display("Verdana", 10, "Red");
    }
}

// Advantages
/*
    1. Reduced Memory Usage: Instead of creating an individual object for each character or graphic element, common objects are reused.
	2. Performance Optimization: Reducing memory load leads to more optimized performance in applications that deal with large numbers of similar objects (e.g., graphics editors, text editors, GIS systems).
	3. Scalability: The Flyweight pattern scales well in scenarios where you have repetitive objects and each needs slight customization.
*/