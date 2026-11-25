package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    // Store all recommendations
    private static final List<Recommendation> recommendations =
            new ArrayList<>();

    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Create an initial recommendation
        Recommendation original =
                new Recommendation("Young Adults");

        original.addBook(new Book(
                "George Orwell", "1984", "Dystopian", 1949));

        original.addBook(new Book(
                "J.R.R. Tolkien", "The Hobbit", "Fantasy", 1937));

        recommendations.add(original);

        // Run the main menu
        while (true) {
            System.out.println("\n===== Book Recommendation System =====");
            System.out.println("1. View recommendations");
            System.out.println("2. Create recommendation");
            System.out.println("3. Clone recommendation");
            System.out.println("4. Modify recommendation");
            System.out.println("5. Exit");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    viewRecommendations();
                    break;

                case 2:
                    createRecommendation();
                    break;

                case 3:
                    cloneRecommendation();
                    break;

                case 4:
                    modifyRecommendation();
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Display all saved recommendations
    private static void viewRecommendations() {

        if (recommendations.isEmpty()) {
            System.out.println("No recommendations available.");
            return;
        }

        for (int i = 0; i < recommendations.size(); i++) {
            System.out.println("\nRecommendation " + (i + 1));
            System.out.println(recommendations.get(i));
        }
    }

    // Create a recommendation from scratch
    private static void createRecommendation() {

        System.out.print("Enter target audience: ");
        String audience = scanner.nextLine();

        Recommendation recommendation =
                new Recommendation(audience);

        // Add the first book
        recommendation.addBook(readBook());

        // Save the new recommendation
        recommendations.add(recommendation);

        System.out.println("Recommendation created successfully.");
    }

    // Clone an existing recommendation using Prototype
    private static void cloneRecommendation() {

        Recommendation original = selectRecommendation();

        if (original == null) {
            return;
        }

        // Create a deep copy of the original recommendation
        Recommendation cloned = original.clone();

        System.out.print("Enter new target audience: ");
        cloned.setTargetAudience(scanner.nextLine());

        // Save the cloned recommendation
        recommendations.add(cloned);

        System.out.println("Recommendation cloned successfully.");
    }

    // Modify an existing recommendation
    private static void modifyRecommendation() {

        Recommendation recommendation = selectRecommendation();

        if (recommendation == null) {
            return;
        }

        System.out.println("1. Change target audience");
        System.out.println("2. Add book");
        System.out.println("3. Remove book");
        System.out.println("4. Edit book");

        int choice = readInt("Enter your choice: ");

        switch (choice) {
            case 1:
                System.out.print("Enter new target audience: ");
                recommendation.setTargetAudience(scanner.nextLine());
                break;

            case 2:
                recommendation.addBook(readBook());
                break;

            case 3:
                int removeIndex = selectBook(recommendation);

                if (removeIndex == -1) {
                    return;
                }

                recommendation.removeBook(removeIndex);
                break;

            case 4:
                int editIndex = selectBook(recommendation);

                if (editIndex == -1) {
                    return;
                }

                // Modify an existing book
                Book book = recommendation.getBooks().get(editIndex);

                System.out.print("Enter new title: ");
                book.setTitle(scanner.nextLine());

                System.out.print("Enter new author: ");
                book.setAuthor(scanner.nextLine());

                System.out.print("Enter new genre: ");
                book.setGenre(scanner.nextLine());

                book.setPublicationYear(
                        readInt("Enter publication year: "));
                break;

            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Recommendation updated successfully.");
    }

    // Allow the user to select a recommendation
    private static Recommendation selectRecommendation() {

        viewRecommendations();

        if (recommendations.isEmpty()) {
            return null;
        }

        int index = readInt("Select recommendation: ") - 1;

        if (index >= 0 && index < recommendations.size()) {
            return recommendations.get(index);
        }

        System.out.println("Invalid recommendation number.");
        return null;
    }

    // Allow the user to select a book
    private static int selectBook(Recommendation recommendation) {

        System.out.println(recommendation);

        if (recommendation.getBooks().isEmpty()) {
            System.out.println("No books available.");
            return -1;
        }

        int index = readInt("Select book number: ") - 1;

        if (index >= 0 && index < recommendation.getBooks().size()) {
            return index;
        }

        System.out.println("Invalid book number.");
        return -1;
    }

    // Read book information from the user
    private static Book readBook() {

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        int year = readInt("Enter publication year: ");

        return new Book(author, title, genre, year);
    }

    // Read an integer and handle invalid input
    private static int readInt(String message) {

        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}