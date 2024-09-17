/*
    1. This design Pattern is used to turn requests or simple operations into objects.
    2. This allows for the parameterization of methods with different request, delays or queue of a request's execution, and providing support for undoable operations.
*/

// Implementation - Remote Control for Home Appliances
/*
    1. A remote control that can turn on and off different home appliances such as a light or fan.
    2. The remote control is the Invoker, the appliances are the Receivers, and the different operations (turning on/off) are encapsulated as Commands.
*/

// 1. Command Interface - It declares the execute() method that all command object must implement
interface Command {
    void execute();
}

// 2. Concrete Command - Implements execute() method by invoking the corresponding actions on the receiver
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// 3. Receiver - Contains the actual logic to perform the action.
class Light {
    public void turnOn() {
        System.out.println("The light is ON");
    }

    public void turnOff() {
        System.out.println("The light is OFF");
    }
}

// 4. Invoker - Calls the command to execute its request. It doesn’t know the specifics of the command or the action, only that it can call execute ().
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// 5. Client
class Main {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();

        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(lightOn);
        remoteControl.pressButton();

        remoteControl.setCommand(lightOff);
        remoteControl.pressButton();
    }
}

// Advantages
/*
    1. Decoupling: The invoker and receiver are decoupled. The invoker knows how to execute the command, but it doesn’t need to know the details of the request.
	2. Easier Extension: New commands can be added easily by simply implementing the Command interface, without modifying existing code.
	3. Supports Undo/Redo: Command pattern makes it easy to implement undoable operations by storing the history of commands.
	4. Flexibility: It provides a way to parameterize objects with different requests, queue operations, and log changes.
*/