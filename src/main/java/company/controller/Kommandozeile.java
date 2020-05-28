package company.controller;

import company.databases.BookCopyDataBase;
import company.databases.BookDataBase;
import company.databases.CustomerDataBase;
import company.objects.Book;
import company.objects.BookCopy;
import company.objects.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Kommandozeile {
    private static BookCopyDataBase bookCopyDataBase;
    private static BookDataBase bookDataBase;
    private static CustomerDataBase customerDataBase;

    public static void main(String[] args) {
        initialize();
        scanForInput();
    }

    /**
     * Scans and verifies user input
     */

    // test

    // test 2
    private static void scanForInput() {
        String userInput = "";
        boolean userInputOK = false;


        while (!userInputOK) {
            userInput = getUserInput();

            try {
                Integer.parseInt(userInput);
                if (Integer.parseInt(userInput) < 15 && Integer.parseInt(userInput) > 0) {
                    userInputOK = true;
                } else {
                    System.out.println("Wrong input, please insert number from 1-14");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Wrong input, please insert number from 1-14");

            }
        }


        switch (Integer.parseInt(userInput)) {
            case 1:
                String csvFilePathBooks = readCSVFilePath();
                bookDataBase.importBooks(csvFilePathBooks);
                // da steht size()-3, weil die Liste schon 3 DummyObjects enthält und die werden nicht importiert
                System.out.println(bookDataBase.getBookDataBase().size() - 3 + " books have been imported successfully");
                break;
            case 2:
                String csvFilePathBookCopies = readCSVFilePath();
                bookCopyDataBase.importBookCopies(csvFilePathBookCopies);
                // da steht size()-3, weil die Liste schon 3 DummyObjects enthält und die werden nicht importiert
                System.out.println(bookCopyDataBase.getBookCopyDataBase().size() - 3 + " book copies have been imported successfully");
                break;
            case 3:
                String csvFilePathCustomers = readCSVFilePath();
                customerDataBase.importCustomers(csvFilePathCustomers);
                // da steht size()-3, weil die Liste schon 3 DummyObjects enthält und die werden nicht importiert
                System.out.println(customerDataBase.getCustomerDataBase().size() - 3 + " customers have been imported successfully");
                break;
            case 4:
                System.out.println("Action executed succesfully!");

                break;
            case 5:
                readClientID();
                readBookISBN();
                System.out.println("Book borrowed succesfully!");
                break;
            case 6:
                returnBookCopy();
                break;
            case 7:
                deleteCustomerFromDatabase();
                break;
            case 8:
                deleteBookFromDataBase();
                break;
            case 9:
                deleteBookCopyFromDatabase();
                break;
            case 10:
                System.out.println("Action executed succesfully!");
                break;
            case 11:
                System.out.println("Action executed succesfully!");
                break;
            case 12:
                System.out.println("Action executed succesfully!");
                break;
            case 13:
                System.out.println("Action executed succesfully!");
                break;
            case 14:
                System.out.println("Action executed succesfully!");
                break;


        }
    }

    /**
     * Returns inportant information about the Book Copy
     * <p>
     * User has to input the Book ISBN and the ID of the book Copy
     */
    private static void returnBookCopy() {
        String bookISBN = readBookISBN();
        String bookCopyID = readBookCopyID();
        for (BookCopy bookCopy : bookCopyDataBase.getBookCopyDataBase()) {
            if (bookCopy.getBook().getIsbn().equals(bookISBN) && bookCopy.getId().equals(bookCopyID)) {
                System.out.println("Book Copy Title: " + bookCopy.getBook().getTitle());
                System.out.println("Book Copy ID: " + bookCopy.getId());
                System.out.println("Book Copy ISBN: " + bookCopy.getBook().getIsbn());
                System.out.println("Book Copy Loan Status: " + bookCopy.getLoanStatus());
                System.out.println("Book Copy Location: " + bookCopy.getLocation());
                System.out.println("Book Copy was added to the library on: " + bookCopy.getAddedToLibrary());
                return;
            }
        }
        System.out.println("Book Copy could not be found!");
    }

    /**
     * Delete Book Copy From DataBase
     */
    private static void deleteBookCopyFromDatabase() {
        String bookCopyID = readBookCopyID();
        for (BookCopy bookCopy : bookCopyDataBase.getBookCopyDataBase()) {
            if (bookCopy.getId().equals(bookCopyID)) {
                if (bookCopyDataBase.deleteBookCopy(bookCopy)) {
                    System.out.println("Book copy deleted succesfully!");
                    return;
                } else {
                    System.out.println("Book copy could not be deleted!");
                    return;
                }
            }

        }
        System.out.println("Book copy could not be found");
    }

    /**
     * Deletes Book From Database
     */
    private static void deleteBookFromDataBase() {
        String bookISBN = readBookISBN();
        for (Book book : bookDataBase.getBookDataBase()) {
            if (book.getIsbn().equals(bookISBN)) {
                if (bookDataBase.deleteBook(book)) {
                    System.out.println("Book deleted succesfully!");
                    return;
                } else {
                    System.out.println("Book could not be deleted!");
                    return;
                }
            }

        }
        System.out.println("Book could not be found");
    }

    /**
     * Deletes Customer from Database
     */
    private static void deleteCustomerFromDatabase() {
        String customerID = readClientID();
        for (Customer customer : customerDataBase.getCustomerDataBase()) {
            if (customer.getClientId().equals(customerID)) {
                if (customerDataBase.deleteCustomer(customer)) {
                    System.out.println("Customer deleted succesfully!");
                    return;
                } else {
                    System.out.println("Customer could not be deleted!");
                    return;
                }
            }
        }
        System.out.println("Customer could not be found");
    }

    /**
     * Reads Client ID
     */
    private static String readClientID() {
        System.out.println("Please insert the customer ID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Reads CSV file path
     */
    private static String readCSVFilePath() {
        System.out.println("Please insert the csv file path to import the books:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Reads Book ISBN
     */
    private static String readBookISBN() {
        System.out.println("Please insert the book ID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Reads Book Copy ID
     */
    private static String readBookCopyID() {
        System.out.println("Please insert the book copy ID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints menu and returns user input
     *
     * @return
     */
    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);


        showFunctions();


        System.out.println("");
        System.out.println("Please choose action:");
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Shows the menu
     */
    private static void showFunctions() {
        System.out.println("1. Import CSV File with books ");
        System.out.println("2. Import CSV File with customers");
        System.out.println("3. Import CSV File with book copies");
        System.out.println("4. Search book copy");
        System.out.println("5. Borrow book copy");
        System.out.println("6. Return book copy");
        System.out.println("7. Delete customer   (Implemented)");
        System.out.println("8. Delete book   (Implemented)");
        System.out.println("9. Delete book copy   (Implemented)");
        System.out.println("10. Print all customers");
        System.out.println("11. Print all books");
        System.out.println("12. Print all not borrowed book copies");
        System.out.println("13. Print all borrowed book copies");
        System.out.println("14. Print all borrowed book copies of customer");
    }

    private static void initialize() {
        customerDataBase = new CustomerDataBase();
        bookDataBase = new BookDataBase();
        bookCopyDataBase = new BookCopyDataBase();

        bookDataBase.setBookCopyDataBase(bookCopyDataBase.getBookCopyDataBase());
        bookCopyDataBase.setBookDataBase(bookDataBase.getBookDataBase());


        fillDatabasesWithDummyObjects();

    }

    private static void fillDatabasesWithDummyObjects() {
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Goethe");
        Customer customer1 = new Customer("Testing", "Tester", "123456", "Home", "1234", "Stuttgart");
        Book Book1 = new Book("Test", authors, "2020", "123", "Stuttgart", "TestingTesterWriter", 1);
        BookCopy BookCopy1 = new BookCopy(Book1, "111", "Regal 1 Fach 2", new Date());

        Customer customer2 = new Customer("Max", "Mustermann", "123457", "Sesame Street", "70763", "Mannheim");
        Book Book2 = new Book("Faust", authors, "1999", "124", "Stuttgart", "UNISTUTTGART", 5);
        BookCopy BookCopy2 = new BookCopy(Book2, "110", "Regal 1 Fach 2", new Date());

        Customer customer3 = new Customer("Bob", "Rob", "123458", "Brodway", "223233", "Hollywood");
        Book Book3 = new Book("Java für Dummies", authors, "2020", "125", "Stuttgart", "TestingTesterWriter",
                1);
        BookCopy BookCopy3 = new BookCopy(Book3, "2", "Regal 1 Fach 2", new Date());

        customerDataBase.addCustomer(customer1);
        customerDataBase.addCustomer(customer2);
        customerDataBase.addCustomer(customer3);

        bookDataBase.addBook(Book1);
        bookDataBase.addBook(Book2);
        bookDataBase.addBook(Book3);

        bookCopyDataBase.addBookCopy(BookCopy1);
        bookCopyDataBase.addBookCopy(BookCopy2);
        bookCopyDataBase.addBookCopy(BookCopy3);
    }

    public static BookCopyDataBase getBookCopyDataBase() {
        return bookCopyDataBase;
    }

    public static BookDataBase getBookDataBase() {
        return bookDataBase;
    }

    public static CustomerDataBase getCustomerDataBase() {
        return customerDataBase;
    }


}
