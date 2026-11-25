package org.example;

public class PrototypeTest {

    public static void main(String[] args) {

        // Create the original recommendation
        Recommendation original = new Recommendation("Adults");

        original.addBook(new Book(
                "George Orwell", "1984", "Dystopian", 1949));

        // Create a deep copy
        Recommendation cloned = original.clone();

        // Modify the cloned recommendation
        cloned.setTargetAudience("Students");
        cloned.getBooks().get(0).setTitle("New Title");

        // Add a book to the cloned list
        cloned.addBook(new Book(
                "J.R.R. Tolkien", "The Hobbit", "Fantasy", 1937));

        // Check that the original remains unchanged
        assert original.getTargetAudience().equals("Adults");
        assert original.getBooks().get(0).getTitle().equals("1984");
        assert original.getBooks().size() == 1;
        assert cloned.getBooks().size() == 2;

        // Check that all references are independent
        assert original != cloned;
        assert original.getBooks() != cloned.getBooks();
        assert original.getBooks().get(0) != cloned.getBooks().get(0);

        System.out.println("All Prototype tests passed!");
    }
}
