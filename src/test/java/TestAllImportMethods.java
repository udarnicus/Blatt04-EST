import company.databases.BookCopyDataBase;
import company.databases.BookDataBase;
import company.databases.CustomerDataBase;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the method importBooks from the class
 * {@link BookDataBase} that contains testing methods when
 * an attempt is made to import books from one csv File
 * and the method importBookCopies from the class
 * {@link BookCopyDataBase} that contains testing methods when
 * an attempt is made to import book copies from one csv File
 *
 * @author Mohamed
 * @version 27.05.2020
 */

public class TestAllImportMethods {


    /**
     * The books will be imported and then it will be checked, if
     * all books have been imported. Then many attributes will be
     * compared to see whether the books have been correctly
     * imported.
     */
    @Test
    public void importBooksAndBookCopies() {
        /* Preparing Test
         * initialise and connect the data bases with each other
         */
        final BookDataBase bookDataBase = new BookDataBase();
        final BookCopyDataBase bookCopyDataBase = new BookCopyDataBase();
        bookCopyDataBase.setBookDataBase(bookDataBase.getBookDataBase());
        bookDataBase.setBookCopyDataBase(bookCopyDataBase.getBookCopyDataBase());


        //check data base empty before importing the books
        assertEquals(bookDataBase.getBookDataBase().size(), 0);

        bookDataBase.importBooks("src\\test\\java\\ressources\\csvFileToTestBooks.csv");

        //check the number of imported books
        assertEquals(bookDataBase.getBookDataBase().size(), 5);

        // check three titles of the first books which have been added.
        assertTrue(bookDataBase.getBookDataBase().get(0).getTitle().equals("Theoretische Informatik"));
        assertTrue(bookDataBase.getBookDataBase().get(1).getTitle().equals("Softwaretechnik"));
        assertTrue(bookDataBase.getBookDataBase().get(2).getTitle().equals("Mathematik"));

        // check the number of authors in 3 different books
        assertEquals(bookDataBase.getBookDataBase().get(0).getAuthors().size(), 2);
        assertEquals(bookDataBase.getBookDataBase().get(2).getAuthors().size(), 1);
        assertEquals(bookDataBase.getBookDataBase().get(3).getAuthors().size(), 1);

        // check that the publisher the same are for two books and not the same for two other books
        assertEquals(bookDataBase.getBookDataBase().get(0).getPublisher(), bookDataBase.getBookDataBase().get(1).getPublisher());
        assertNotEquals(bookDataBase.getBookDataBase().get(0).getPublisher(), bookDataBase.getBookDataBase().get(4).getPublisher());

        //Ausgabe aller Titel
        bookDataBase.getBookDataBase().stream().map(book -> book.getTitle()).forEach(System.out::println);

        // Ausgabe aller Städten, wo die Bücher erstellt wurden
        bookDataBase.getBookDataBase().stream().map(book -> book.getCity()).forEach(System.out::println);

        assertEquals(bookDataBase.getBookDataBase().size(), 5);

        // Zweiter Teil ; ImportBookCopy

        // Check that the book copy data base is empty before the import
        assertEquals(bookCopyDataBase.getBookCopyDataBase().size(), 0);

        bookCopyDataBase.importBookCopies("src\\test\\java\\ressources\\csvFileToTestBookCopies");

        // check the number of imported books
        assertEquals(bookCopyDataBase.getBookCopyDataBase().size(), 8);

        // check that all book copies, whose books belong to the data base, have been imported.

        assertEquals(bookCopyDataBase.getBookCopyDataBase().stream().filter(bookCopy ->
                bookCopy.getBook().getTitle().equals("Mathematik")).collect(Collectors.toList()).size(), 4);
        assertEquals(bookCopyDataBase.getBookCopyDataBase().stream().filter(bookCopy ->
                bookCopy.getBook().getTitle().equals("Theoretische Informatik")).collect(Collectors.toList()).size(), 3);
        assertEquals(bookCopyDataBase.getBookCopyDataBase().stream().filter(bookCopy ->
                bookCopy.getBook().getTitle().equals("I hate school")).collect(Collectors.toList()).size(), 1);
    }

    @Test
    public void importCustomer() {
        final CustomerDataBase customerDataBase = new CustomerDataBase();

        //check data base empty before importing the books
        assertEquals(customerDataBase.getCustomerDataBase().size(), 0);

        customerDataBase.importCustomers("src\\test\\java\\ressources\\csvFileToTestCustomers");

        //check the number of imported books
        assertEquals(customerDataBase.getCustomerDataBase().size(), 6);

        // check four first names of the first customers which have been added.
        assertTrue(customerDataBase.getCustomerDataBase().get(0).getFirstName().equals("Mohamed"));
        assertTrue(customerDataBase.getCustomerDataBase().get(1).getFirstName().equals("Radu"));
        assertTrue(customerDataBase.getCustomerDataBase().get(2).getFirstName().equals("Lena"));
        assertTrue(customerDataBase.getCustomerDataBase().get(3).getFirstName().equals("Anni"));

// Ausgabe aller Kunden
        customerDataBase.getCustomerDataBase().stream().forEach(System.out::println);

    }

}