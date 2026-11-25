package org.example;

public class Book implements Cloneable {

    // Book attributes
    private String author;
    private String title;
    private String genre;
    private int publicationYear;

    // Constructor to create a new book
    public Book(String author, String title, String genre,
                int publicationYear) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    // Getters to access book attributes
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    // Setters to modify book attributes
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    // Prototype pattern: create a copy of the book
    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    // Return book information as a readable string
    @Override
    public String toString() {
        return title + " by " + author
                + " (" + publicationYear + ")"
                + " - " + genre;
    }
}