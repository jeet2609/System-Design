/*
    1. To provides greater control over the object creation process.
    2. Especillay used when object has numerous parameters, some o which are optional.
    3. It provides clear sepeartion between object construction and it's representation
*/

/*************************** 1. Static inner method ****************************/
class House {
    private String foundation;
    private String structure;
    private String roof;
    private boolean hasGarden;
    private boolean hasSwimmingPool;
    private boolean hasGarage;

    // Private constructor so that the object can only be created through the builder
    private House(HouseBuilder builder) {
        this.foundation = builder.foundation;
        this.structure = builder.structure;
        this.roof = builder.roof;
        this.hasSwimmingPool = builder.hasSwimmingPool;
        this.hasGarden = builder.hasGarden;
        this.hasGarage = builder.hasGarage;
    }

    // Static inner builder class
    public static class HouseBuilder {
        private String foundation;
        private String structure;
        private String roof;
        private boolean hasGarage;
        private boolean hasGarden;
        private boolean hasSwimmingPool;

        // Mandatory parameters (set through constructor)
        public HouseBuilder(String foundation, String structure) {
            this.foundation = foundation;
            this.structure = structure;
        }

        // Optional parameters set through method chaining
        public HouseBuilder setRoof(String roof) {
            this.roof = roof;
            return this;
        }

        public HouseBuilder setSwimmingPool(boolean hasSwimmingPool) {
            this.hasSwimmingPool = hasSwimmingPool;
            return this;
        }

        public HouseBuilder setGarden(boolean hasGarden) {
            this.hasGarden = hasGarden;
            return this;
        }

        public HouseBuilder setGarage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        // Build method that returns the final House object
        public House build() {
            return new House(this);
        }
    }

    @Override
    public String toString() {
        return "House with foundation: " + foundation + ", structure: " + structure + ", roof: " + roof + ", swimming pool: " + hasSwimmingPool + ", garden: " + hasGarden + ", garage: " + hasGarage;
    }
}

class Main {
    public static void main(String[] args) {
        House house = new House.HouseBuilder("Concrete", "Wood")
                        .setRoof("Shingles")
                        .setSwimmingPool(true)
                        .setGarden(true)
                        .setGarage(false)
                        .build();

        System.out.println(house);
    }
}


/*************************** 2. Traditional Builder Pattern with Director ****************************/

// 1. Builder Interface/Abstract Class
interface HouseBuilder2 {
    void buildFoundation();
    void buildStructure();
    void buildRoof();
    void buildGarage();
    void buildSwimmingPool();
    void buildGarden();
    House2 getHouse();  // This returns the final product (House)
}

// 2. Concrete Builder
class ConcreteHouseBuilder2 implements HouseBuilder2 {
    private House2 house;

    public ConcreteHouseBuilder2() {
        this.house = new House2();  // Initializes an empty House object
    }

    @Override
    public void buildFoundation() {
        house.setFoundation("Concrete Foundation");
    }

    @Override
    public void buildStructure() {
        house.setStructure("Wooden Structure");
    }

    @Override
    public void buildRoof() {
        house.setRoof("Shingle Roof");
    }

    @Override
    public void buildGarage() {
        house.setGarage(true);  // Adding garage
    }

    @Override
    public void buildSwimmingPool() {
        house.setSwimmingPool(false);  // No swimming pool
    }

    @Override
    public void buildGarden() {
        house.setGarden(true);  // Adding garden
    }

    @Override
    public House2 getHouse() {
        return this.house;  // Return the completed house
    }
}

// 3. Product
class House2 {
    private String foundation;
    private String structure;
    private String roof;
    private boolean hasGarage;
    private boolean hasSwimmingPool;
    private boolean hasGarden;

    // Getters and setters
    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public void setSwimmingPool(boolean hasSwimmingPool) {
        this.hasSwimmingPool = hasSwimmingPool;
    }

    public void setGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    @Override
    public String toString() {
        return "House built with foundation: " + foundation + ", structure: " + structure + ", roof: " + roof + ", has garage: " + hasGarage + ", has swimming pool: " + hasSwimmingPool + ", has garden: " + hasGarden;
    }
}

// 4. Director
class HouseDirector2 {
    private HouseBuilder2 houseBuilder;

    public HouseDirector2(HouseBuilder2 houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    // Direct the builder to construct a house in a particular order
    public void constructHouse() {
        houseBuilder.buildFoundation();
        houseBuilder.buildStructure();
        houseBuilder.buildRoof();
        houseBuilder.buildGarage();
        houseBuilder.buildSwimmingPool();
        houseBuilder.buildGarden();
    }

    public House2 getHouse() {
        return houseBuilder.getHouse();  // Return the final built house
    }
}

// Client Code
class Main2 {
    public static void main(String[] args) {
        // Create a concrete house builder
        HouseBuilder2 builder = new ConcreteHouseBuilder2();
        
        // Create the director and pass the builder to it
        HouseDirector2 director = new HouseDirector2(builder);
        
        // Director constructs the house step by step
        director.constructHouse();
        
        // Get the final product (house)
        House2 house = director.getHouse();
        
        // Print the house details
        System.out.println(house);
    }
}