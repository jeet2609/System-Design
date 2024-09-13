/*
    1. No client should be forced to depend on interface that they don't use
*/

interface Worker_ {
    void work();
    void eat();
}

class HumanWorker_ implements Worker_ {
    @Override
    public void work() {
        System.out.println("Humam is working");
    }

    @Override
    public void eat() {
        System.out.println("Humam is eating");
    }
}

class RobotWorker_ implements Worker_ {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }

    @Override
    public void eat() {
        // Not applicable for Robot
    }
}

/*
    1. eat() method is not applicable for robot, because robot can't eat
    2. Correct interface should be
*/

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class HumanWorker implements Workable, Eatable {
    @Override
    public void work() {
        System.out.println("Humam is working");
    }

    @Override
    public void eat() {
        System.out.println("Humam is eating");
    }
}

class RobotWorker implements Workable {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }
}

/*
    1. Here RobotWorker can only implement Workable interface and need to implement only work() method
    2. Robot dosen't need to implement eat()
*/