/*
    1. High level modules should not depend on low-level modules, both should depend on abstraction
    2. Class should not depend on another class, but on abstraction of this class.
*/

class LightBulb_ {
    public void turnOn() {
        System.out.println("LightBulb on");
    }

    public void turnOff() {
        System.out.println("LightBulb off");
    }
}

class Switch_ {
    private LightBulb_ lightBulb;

    public Switch_(LightBulb_ lightBulb) {
        this.lightBulb = lightBulb;
    }

    public void switchOn() {
        lightBulb.turnOn();
    }

    public void switchOff() {
        lightBulb.turnOff();
    }
}

/*
    1. Here Switch class is directly depends on LightBulb class
    2. The code should be
*/

interface Switchable {
    void turnOn();
    void turnOff();
}

class LightBulb implements Switchable {
    @Override
    public void turnOn() {
        System.out.println("LightBulb on");
    }

    @Override
    public void turnOff() {
        System.out.println("LightBulb off");
    }
}

class Switch {
    private Switchable device;

    public Switch(Switchable device) {
        this.device = device;
    }

    public void switchOn() {
        device.turnOn();
    }

    public void switchOff() {
        device.turnOff();
    }
}

/*
    1. Now Switch class and LightBulb class both depends on abstraction Switchable
    2. We can easily replace the LightBuld with any other Switchable device (eg fan)
*/

class Fan implements Switchable {
    @Override
    public void turnOn() {
        System.out.println("LightBulb on");
    }

    @Override
    public void turnOff() {
        System.out.println("LightBulb off");
    }
}

/*
    1. Now we can pass Fan object to Switch class also
*/