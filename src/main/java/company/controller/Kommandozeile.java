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
        while(true) {
            String userInput = "";
            boolean userInputOK = false;


            while (!userInputOK) {
                userInput = getUserInput().replaceAll("\\s+","");

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
                    System.out.println(bookDataBase.getBookDataBase().size() - 3 + " books have been imported " +
                            "successfully");
                    break;
                case 2:
                    String csvFilePathBookCopies = readCSVFilePath();
                    bookCopyDataBase.importBookCopies(csvFilePathBookCopies);
                    // da steht size()-3, weil die Liste schon 3 DummyObjects enthält und die werden nicht importiert
                    System.out.println(bookCopyDataBase.getBookCopyDataBase().size() - 3 + " book copies have been " +
                            "imported successfully");
                    break;
                case 3:
                    String csvFilePathCustomers = readCSVFilePath();
                    customerDataBase.importCustomers(csvFilePathCustomers);
                    // da steht size()-3, weil die Liste schon 3 DummyObjects enthält und die werden nicht importiert
                    System.out.println(customerDataBase.getCustomerDataBase().size() - 3 + " customers have been imported" +
                            " successfully");
                    break;
                case 4:
                    String bookCopyID = readBookCopyID();
                    if(searchBookCopy(bookCopyID)!=null){
                        System.out.println(searchBookCopy(bookCopyID));
                    }else{
                        System.out.println("BookCopy could not be found!");
                    }
                    break;
                case 5:
                    String bookCopyID2 = readBookCopyID();
                    borrowBookCopy(bookCopyID2, readClientID());
                    break;
                case 6:
                    String bookCopyID3 = readBookCopyID();
                    returnBookCopy(bookCopyID3);
                    break;
                case 7:
                    String customerID = readClientID();
                    deleteCustomerFromDatabase(customerID);
                    break;
                case 8:
                    String bookISBN = readBookISBN();
                    deleteBookFromDataBase(bookISBN);
                    break;
                case 9:
                    String bookCopyID1 = readBookCopyID();
                    deleteBookCopyFromDataBase(bookCopyID1);
                    break;
                case 10:
                    printAllCustomers();
                    break;
                case 11:
                    printAllBooks();
                    break;
                case 12:
                    printAllNotBorrowedBookCopies();
                    break;
                case 13:
                    printAllBorrowedBookCopies();
                    break;
                case 14:
                    printAllBorrowedBookCopiesOfCustomer(readClientID());
                    break;


            }
        }
    }

    /**
     * Prints all books from the database
     * <p>
     * No test for the method possible, only manual test
     */
    private static void printAllBooks() {

        for (Book book : bookDataBase.getBookDataBase()) {
            System.out.println(book.toString());
        }
    }

    /**
     * Prints all customers from the database
     * <p>
     * No test for the methode possible, only manual test
     */
    private static void printAllCustomers() {
        for (Customer customer : customerDataBase.getCustomerDataBase()) {
            System.out.println(customer.toString());
        }
    }

    /**
     * Prints all not borrowed bookCopies from database
     */
    private static void printAllNotBorrowedBookCopies() {
        for (BookCopy bookCopy : bookCopyDataBase.getBookCopyDataBase()) {
            if (!bookCopy.getLoanStatus())
                System.out.println(bookCopy);
        }
    }

    /**
     * Prints all borrowed bookCopies from database
     */
    private static void printAllBorrowedBookCopies() {
        for (BookCopy bookCopy : bookCopyDataBase.getBookCopyDataBase()) {
            if (bookCopy.getLoanStatus())
                System.out.println(bookCopy);
        }
    }

    /**
     * Prints all borrowed bookCopies of a specific customer
     */
    private static void printAllBorrowedBookCopiesOfCustomer(String customerID) {
        Customer customer = searchCustomer(customerID);
        if(customer != null){
            for (int k = 0; k < customer.getBooksOnLoan().size(); k++) {
                System.out.println(customer.getBooksOnLoan().get(k));
            }
        }else{
            System.out.println("Customer could not be found!");
        }

    }

    /**
     * Simulates User Input for Testing purposes
     * <p>
     * Doesnt start user input so that the test doesnt freeze
     */
    public static void startTestingEnviroment() {
        initialize();
    }


    /**
     * This method searches for a book copy in our database.
     * If the book exists the location will be printed.
     *
     * @param bookCopyID
     */
    public static BookCopy searchBookCopy(String bookCopyID) {
        for (BookCopy bookCopy : bookCopyDataBase.getBookCopyDataBase()) {
            if (bookCopy.getId().equals(bookCopyID)) {
                return bookCopy;
            }
        }
        return null;
    }

    /**
     * This method searches for a book  in our database.
     * If the book exists the location will be printed.
     *
     * @param bookISBN
     */
    public static Book searchBook(String bookISBN) {
        for (Book book : bookDataBase.getBookDataBase()) {
            if (book.getISBN().equals(bookISBN)) {
                return book;
            }
        }
        System.out.println("Book could not be found!");
        return null;
    }

    /**
     * This method let a customer borrow a book.
     * If the conditions of the customer aren´t fulfilled
     * it´s not possible to borrow a book.
     *
     * @param bookCopyID
     * @param customerID
     */

    public static boolean borrowBookCopy(String bookCopyID, String customerID) {
        Customer customer = searchCustomer(customerID);

        if (customer.getPaymentStatus() && !customer.hasOverdraftFeeStatus() && customer.getBooksOnLoan().size() < 5) {
            BookCopy bookCopy = searchBookCopy(bookCopyID);
            if (bookCopy != null) {
                bookCopy.setCurrentBorrower(customer);
                customer.getBooksOnLoan().add(bookCopy);
                System.out.println("Book borrowed successfully!");
                return true;

            } else {
                System.out.println("Book could not be borrowed");
                return false;
            }
        } else {
            if (!customer.getPaymentStatus()) {
                System.out.println("Your payment status isn´t ok! You can´t borrow a book!");
                return false;
            } else if (customer.hasOverdraftFeeStatus()) {
                System.out.println("You have an overdraft fee staus! You can´t borrow a book!");
                return false;
            } else {
                System.out.println("You can´t borrow more than 5 books. You must return an other book to borrow this " +
                        "book!");
                return false;
            }

        }
    }


    /**
     * Methode returns book to the library
     *
     * It changes the LoanDate and LoanStatus, removes the book from the borrower and adds overdraftfees to the
     * borrower if he brings the bookcopy to late back
     *
     * @return It returns an Integer for testing purposes - 0 not found, 1 found but to late, 2 found and in time
     */


    public static Integer returnBookCopy(String bookCopyID) {

        if(searchBookCopy(bookCopyID)!=null){
            Date currentDate = new Date();
            BookCopy bookCopy = searchBookCopy(bookCopyID);

            if(currentDate.after(bookCopy.getLoanDate())){
                bookCopy.getCurrentBorrower().setOverdraftFeeStatus(true);
                bookCopy.getCurrentBorrower().getBooksOnLoan().remove(bookCopy);
                bookCopy.setCurrentBorrower(null);
                bookCopy.setLoanStatus(false);
                bookCopy.setLoanDate(null);
                System.out.println("Bookcopy was returned to late, there are overdraftfees to be payed!");
                return 1;
            }else {

                bookCopy.getCurrentBorrower().getBooksOnLoan().remove(bookCopy);
                bookCopy.setCurrentBorrower(null);
                bookCopy.setLoanStatus(false);
                bookCopy.setLoanDate(null);
                System.out.println("Bookcopy was returned successfully!");
                return 2;
            }
        }
        System.out.println("Bookcopy could not be found!");
        return 0;
    }

    /**
     * Deletes Book Copy From DataBase
     */
    public static void deleteBookCopyFromDataBase(String bookCopyID) {
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
    public static void deleteBookFromDataBase(final String bookISBN) {
        final Book bookToDelete = searchBook(bookISBN);
        if (bookToDelete != null) {
            if (bookDataBase.deleteBook(bookToDelete)) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book could not be deleted!");
            }
        } else {
            System.out.println("Book could not be found");
        }
    }

    /**
     * Deletes Customer from Database
     */
    public static void deleteCustomerFromDatabase(String customerID) {
        Customer customerToDelete = searchCustomer(customerID);
        if (customerToDelete != null) {
            if (customerDataBase.deleteCustomer(customerToDelete)) {
                System.out.println("Customer deleted succesfully!");
            } else {
                System.out.println("Customer could not be deleted!");
            }
        } else {
            System.out.println("Customer does not exist");
        }
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
     * Searches for a Customer
     */
    public static Customer
    searchCustomer(String clientID) {
        for (Customer customer : customerDataBase.getCustomerDataBase()) {
            if (customer.getClientId().equals(clientID)) {
                return customer;
            }
        }
        return null;
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
        String input = "";
        boolean userInputCorrect = false;

        while(!userInputCorrect){
            System.out.println("Please insert the book ID:");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if(!input.isEmpty()){
                userInputCorrect = true;
            }else{
                System.out.println("Please insert the book ID:");
            }
        }

        return input;
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

    /**
     * Creates three Databases when the programm is starting
     */
    private static void initialize() {
        customerDataBase = new CustomerDataBase();
        bookDataBase = new BookDataBase();
        bookCopyDataBase = new BookCopyDataBase();

        bookDataBase.setBookCopyDataBase(bookCopyDataBase.getBookCopyDataBase());
        bookCopyDataBase.setBookDataBase(bookDataBase.getBookDataBase());


        fillDatabasesWithDummyObjects();

    }

    /**
     * Creates Dummy Objects in every database so manual testing is possible
     *
     */
    public static void fillDatabasesWithDummyObjects() {
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Goethe");


        Customer customer1 = new Customer("Testing", "Tester", "123456", "Home", "1234", "Stuttgart");
        Customer customer2 = new Customer("Max", "Mustermann", "123457", "Sesame Street", "70763", "Mannheim");
        Customer customer3 = new Customer("Bob", "Rob", "123458", "Brodway", "223233", "Hollywood");

        Book Book1 = new Book("Test", authors, "2020", "123", "Stuttgart", "TestingTesterWriter", 1);
        Book Book2 = new Book("Faust", authors, "1999", "124", "Stuttgart", "UNISTUTTGART", 5);
        Book Book3 = new Book("Java für Dummies", authors, "2020", "125", "Stuttgart", "TestingTesterWriter",
                1);
        BookCopy BookCopy1 = new BookCopy(Book1, "111", "Regal 1 Fach 2", new Date());
        BookCopy BookCopy2 = new BookCopy(Book2, "110", "Regal 1 Fach 2", new Date());
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

    /**
     * Returns the BookCopyDataBase created for the program
     *
     * @return
     */
    public static BookCopyDataBase getBookCopyDataBase() {
        return bookCopyDataBase;
    }

    /**
     * Returns the BookDataBase created for the program
     *
     * @return
     */
    public static BookDataBase getBookDataBase() {
        return bookDataBase;
    }

    /**
     * Returns the CustomerDataBase created for the program
     *
     * @return
     */

    public static CustomerDataBase getCustomerDataBase() {
        return customerDataBase;
    }


}
