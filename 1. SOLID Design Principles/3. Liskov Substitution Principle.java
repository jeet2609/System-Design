/*
    1. Objects o a superclass should be replaceable with objects of it's subclass without affecting the correctness of program.
*/

class Vehicle_ {
    public void startEngine() {
        System.out.println("Starting engine");
    }
}

class Car_ extends Vehicle_ {
    public void startEngine() {
        System.out.println("Starting engine of car");
    }
}

class Bicycle_ extends Vehicle_ {
    public void startEngine() {
        // this dosen't make sense for bicycle
    }
}

/*
    1. Bicyle class violates LSP bacause it provides an implementation for startEngine() which dosen't make sense for Bicycle
    2. If we try to substitute Bicycle instance where a Vehicle instace is expected, it might lead to unexpected behaviour
    3. So the correct code should be
*/

class Vehicle {
    public void start() {
        System.out.println("Starting the vehicle");
    }
}

class Car extends Vehicle {
    public void start() {
        System.out.println("Starting the car engine");
    }
}

class Bicycle extends Vehicle {
    public void start() {
        System.out.println("Pedaling the bicycle");
    }
}

/*
    1. Now instance of Car or Bicycle can be safely substituted for instace of Vehicle without any unexpencted behaviour or errors.
*/