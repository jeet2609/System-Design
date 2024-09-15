/*
    1. This design pattern is a structural pattern that allows you to compose objects into tree like structure to represent part-whole hierarchies.
    2. It enables to treat individual objects and composition of objects uniformaly.
*/

// Consider example of File system, where we have files and directories

// 1. Component - An abstract class or interface that declares the commom operation that can be performed on both composite and leaf object.
import java.util.ArrayList;
import java.util.List;

interface FileSystemComponet {
    void showDetails();
}

// 2. Leaf - Represents individual objects in the composition, Thase objects do not have children
class File implements FileSystemComponet {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File : " + name);
    }
}

// 3. Composite - Represents complex objects that may contain child objects (leaf and composite both)
class Directory implements FileSystemComponet {
    private String name;
    private List<FileSystemComponet> children = new ArrayList<>();
    
    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystemComponet componet) {
        children.add(componet);
    }

    public void remove(FileSystemComponet componet) {
        children.remove(componet);
    }

    @Override
    public void showDetails() {
        System.out.println("Directory : " + name);
        for(FileSystemComponet componet : children) {
            componet.showDetails();
        }
    }
}

// 4. Client - Interact with objects of component interface without needing to know weather they are leaf or composite
class Main {
    public static void main(String[] args) {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        Directory director1 = new Directory("Directory1");
        director1.add(file1);
        director1.add(file2);

        File file3 = new File("file3.txt");
        Directory director2 = new Directory("Directory2");
        director2.add(file3);

        Directory rootDirector = new Directory("Root Directory");
        rootDirector.add(director1);
        rootDirector.add(director2);

        rootDirector.showDetails();
    }
}

/*
    1. Uniformity: Both composite and leaf objects are treated uniformly, simplifying client code.
	2. Extensibility: You can easily add new types of components (like new file types or container types) without modifying existing code.
	3. Simplifies Hierarchies: Provides an elegant way to represent complex hierarchies of objects.
*/