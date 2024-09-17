/*
    1. This design pattern provides a way to access elements of a collection sequentially without exposing it's underlying implementations.
    2. The pattern is useful for decoupling the iteration logic from the collection itself, thus ensuring that the collection remains flexible to changes in its internal structure.
*/

// Implementation - Book collection

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getTitle() {
        return name;
    }
}

// 1. Iterator Inteface - This defines methods required for traversing a collection. Usually contains hasNext(), next(), remove().
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// 2. Concrete Iterator - Implements iterator interface and provides logic
class BookIterator implements Iterator<Book> {
    private List<Book> books;
    private int position;

    public BookIterator(List<Book> books) {
        this.books = books;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < books.size();
    }

    @Override
    public Book next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        return books.get(position++);
    }
}

// 3. Aggregate - This defines the method for creating an uterator for the collection. It’s a single method like createIterator() that returns an instance of the Iterator interface.
interface BookCollection {
    Iterator<Book> createIterator();
}

// 4. Concrete Aggregate - It implements aggregate interface
class Library implements BookCollection {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }
    
    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public Iterator<Book> createIterator() {
        return new BookIterator(books);
    }
}

// 5. Client
class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("Design Patterns"));
        library.addBook(new Book("Clean Code"));
        library.addBook(new Book("Low level design"));

        Iterator<Book> iterator = library.createIterator();
        while(iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book.getTitle());
        }
    }
}

// Advantages of Iterator Pattern:
/*
	1. Single Responsibility Principle: It decouples the collection from the iteration logic.
	2. Open/Closed Principle: New types of iterators can be added without modifying existing code.
	3. Simplifies Client Code: Clients don’t need to manage complex traversal logic. The iterator handles all the details.
	4. Uniform Interface for Traversal: Different types of collections (array, list, tree, etc.) can provide iterators that implement the same interface, enabling polymorphism in traversal.
*/