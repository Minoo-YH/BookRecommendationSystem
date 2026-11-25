package org.example;
import java.util.ArrayList;
import java.util.List;

public class Recommendation implements Cloneable {

    // Recommendation attributes
    private String targetAudience;
    private List<Book> books;

    // Constructor to create a new recommendation
    public Recommendation(String targetAudience) {
        this.targetAudience = targetAudience;
        this.books = new ArrayList<>();
    }

    // Getters
    public String getTargetAudience() {
        return targetAudience;
    }

    public List<Book> getBooks() {
        return books;
    }

    // Change the target audience
    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    // Add a book to the recommendation
    public void addBook(Book book) {
        books.add(book);
    }

    // Remove a book by its index
    public void removeBook(int index) {
        books.remove(index);
    }

    // Prototype pattern: create a deep copy
    @Override
    public Recommendation clone() {
        try {
            // Create a shallow copy first
            Recommendation cloned =
                    (Recommendation) super.clone();

            // Create a new independent list
            cloned.books = new ArrayList<>();

            // Clone each book to achieve deep copying
            for (Book book : books) {
                cloned.books.add(book.clone());
            }

            return cloned;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    // Display recommendation information
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Target audience: ")
                .append(targetAudience)
                .append("\n");

        for (int i = 0; i < books.size(); i++) {
            result.append(i + 1)
                    .append(". ")
                    .append(books.get(i))
                    .append("\n");
        }

        return result.toString();
    }
}