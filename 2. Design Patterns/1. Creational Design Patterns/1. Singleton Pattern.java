/*
    1. We often require classes that can only have one object eg Logger, Caches, etc.
    2. Creating multiple objects could lead to issue.
    3. So this pattern guarantees a class has one and only one instance and provide global point of access to it.
*/

/*************************** 1. Lazy Initialization ****************************/ 

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() { }

    public static LazySingleton getInstance() {
        if(instance == null) {
            return new LazySingleton();
        }
        return instance;
    }
}
/*
    1. Private Constructor -> To prevent other class from creating instance
    2. static instance variable -> Holds single instance of the class
    3. public static method -> To provide access to the instance
*/


/*************************** 2. Thread-Safe Singleton ****************************/ 
/*
    1. In multithreading environment above implementation may reasult in creating multiple instances, if 2 threads calls getInstance() simultaneously.
*/
class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() { }

    public static synchronized ThreadSafeSingleton getInstance() {
        if(instance == null) {
            return new ThreadSafeSingleton();
        }
        return instance;
    }
}
/*
    1. The synchronized keyword ensure that only one thread can perform the action, and for that time other threads are blocked.
    2. But using synchronized can descrease performance.
*/


/*************************** 3. Eager Initialization Singleton ****************************/ 
/*
    1. In this we rely on JVM to create the single instance when class is loaded.
    2. JVM guarantes that the instance will be created before any thread access the instance variable.
*/
class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() { }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
/*
    1. Use this when we always need a Singleton instance.
    2. It could potentilly waste resource is instance is never used.
*/


/*************************** 4. Enum Singleton ****************************/ 
/*
    1. Singleton is declared as an Enum instead of class.
    2. Java ensures only one instance of enum is created even in multithreaded environment.
*/
enum EnumSinleton {
    INSTANCE;

    public void doSomething() {
        // logic
    }
}


/*************************** Lazy vs Enum ****************************/ 
/*
    1. Lazy should be used when you are not sure if instance can be used or not
    2. Enum should be used when we instance at the startup eg ConfigurationManager.
*/