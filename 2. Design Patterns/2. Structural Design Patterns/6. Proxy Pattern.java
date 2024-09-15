/*
    1. This design pattern provides a placeholder or surrogate for another object to control access to it.
    2. This pattern allows you to add additional functionalities to an object without modifying core logic.
*/

// 1. Subject - This is an interface or abstract class defining the common operations between the real target and proxy
interface Image {
    void display();
}

// 2. Real subject - The actual object which the proxy represents
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    public void loadFromDisk(String fileName) {
        System.out.println("Loading : " + fileName + " from disk");
    }

    @Override
    public void display() {
        System.out.println("Displaying : " + fileName);
    }
}

// 3. Proxy - It holds reference to real object and controls access to it. It forwards request to RealObject and can add additional behaviour
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// 4. Client
class Main {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("Image-1");
        Image image2 = new ProxyImage("Image-2");

        image1.display();
        image2.display();
        image1.display();
    }
}

/*
    1. First time image1.display() will create a new object of RealImage.
    2. Second time image1.display() will use old object (proxy).
*/

// Proxy vs Adapter
/*
    1. Proxy - It controls access to an object, often adding functionality or after accessing the real object.
    2. Adapter - It transforms the interface of an object to match what a client expects, allowing incompatible interface to work together.
*/